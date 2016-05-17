package security.permission;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.jersey.api.client.WebResource;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import security.dao.SessionManager;
import security.dao.UserDAO;
import security.entity.User;
import security.oauth2.authcode.OAuth2CodeSettings;
import security.oauth2.authcode.RevokeServlet;
import security.rest.exceptions.CustomWebApplicationException;


import security.business.UserBusiness;
import security.dao.SessionManager;
import security.dao.UserDAO;
import security.dao.UserRoleDAO;
import security.entity.User;
import security.entity.UserRole;
import security.oauth2.authcode.OAuth2CodeSettings;
import security.oauth2.authcode.RevokeServlet;
import security.oauth2.flow.OAuth2Client;
import security.oauth2.flow.OAuth2Settings;
import security.rest.exceptions.CustomWebApplicationException;
import util.Hash;

@SuppressWarnings("unused")

@WebServlet(value = { "/auth", "/logout", "/session" }, name = "auth-servlet")
public class AuthenticationServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = Logger.getLogger(AuthenticationServlet.class.getName());

  private static final String USERNAME = "admin";
  private static final String PASSWORD = "admin";
  




	private static final String URL_TEST = "http://janus.integ.aws1.techne.com.br";

	private static final String URL_PRODUCAO = "https://janus.techne.com.br";

	private static final String _JANUS_URL = URL_PRODUCAO;

	private String janusToken;

  @Override
  public void init() throws ServletException {
    // init
  }
  
  

  @Override
  public void destroy() {
    // destroy
  }
  

	
		private final boolean authenticateJanusAPI(String username, String password) {

		final String janusRestLogin = _JANUS_URL + "/webservices/rest/login";

		try {
			Client client = this.getClientForJanus();

			WebResource webResource = client.resource(janusRestLogin);

			String inputHttp = "usuario=" + username + "&senha=" + password;

			ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, inputHttp);
			if (response.getStatus() != 200)
				throw new RuntimeException("Failed: HTTP error code: "
						+ response.getStatus());

			this.janusToken = response.getHeaders().get("Authenticate").get(0)
					.toString();

			String output = response.getEntity(String.class);

			return "Usuário autenticado com sucesso".equals(output) ? true
					: false;

		} catch (Exception e) {
			throw new CustomWebApplicationException(e);
		}
	}
	
	  	private boolean login(String username, String password) {
		logger.log(Level.INFO, "login");
		return authenticateJanusAPI(username, password);
	}
	
	
		private Client getClientForJanus() {
		if (_JANUS_URL.equals(URL_TEST))
			return Client.create();
		return this.createClientWithSSL();
	}
	
	
		private User getUserByName(String username) {
		UserDAO userDao = new UserDAO(SessionManager.getInstance()
				.getEntityManager());
		List<User> users = userDao.findByAttribute("login", username);
		if (!users.isEmpty())
			return users.get(0);
		return null;
	}
	
	

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String uri = req.getRequestURI().substring(req.getContextPath().length());

    if("/auth".equals(uri)) {
      try {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean login = this.login(username, password);

        if(login) {
          req.getSession().setAttribute("username", username);

          UserRoleDAO dao = new UserRoleDAO(SessionManager.getInstance().getEntityManager());

          List<UserRole> userRoles = dao.findByLogin(username, Integer.MAX_VALUE, 0);

          String rolesID = AuthorizationFilter.EVERYONE_ID;

          for(UserRole userRole : userRoles) {
            rolesID += "," + userRole.getRole().getId();
          }

          req.getSession().setAttribute("roles", rolesID);

          User user = this.getUserByName(username);

          if(user != null) {
            req.getSession().setAttribute("user", user);
            Gson gson = new Gson();
            JsonElement json = gson.toJsonTree(user);
            json.getAsJsonObject().addProperty("roles", rolesID);
            json.getAsJsonObject().addProperty("root", rolesID.contains(AuthorizationFilter.ADMIN_ID));
            json.getAsJsonObject().remove("password");
            resp.setHeader("Content-Type", "application/json");
            resp.getOutputStream().print(json.toString());
          }
          else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
          }
        }
        else {
          resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
      }
      catch(Exception e) {
        throw new CustomWebApplicationException(e);
      }
    }
    else if("/changePassword".equals(uri)) {
      this.changePassword(req, resp);
    }
    else {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    try {
      String uri = req.getRequestURI().substring(req.getContextPath().length());
      if("/logout".equals(uri)) {
        this.logout(req, resp);
      }
      else {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      }
    }
    catch(Exception e) {
      throw new CustomWebApplicationException(e);
    }
  }

  // private boolean login(String username, String password) {
  //   logger.log(Level.INFO, "login");
  //   // return authenticateLocal(username, password);
  //   return this.authenticateDataBase(username, password);
  //   // return authenticateOAuth2(username, password);
  // }

  /**
   * Validação a operação de login pelas informações locais.
   * Metodo mais simples e menos seguro.
   *
   * @param username
   * @param password
   * @return
   */
  private boolean authenticateLocal(String username, String password) {
    boolean result = USERNAME.equals(username) && PASSWORD.equals(password);
    if(result) {
      AuthenticationServlet.createUserIfNotExists(username, password, null);
    }
    return result;
  }

  /**
   * Busca o usuário pelo servidor de autenticação da Techne utilizando o padrão OAuth2.
   *
   * @param username
   * @param password
   * @return
   */
  private boolean authenticateOAuth2(String username, String password) {
    OAuth2Client client = new OAuth2Client(OAuth2Settings.TOKEN_URI, OAuth2Settings.REVOKE_URI,
            OAuth2Settings.CLIENT_ID, OAuth2Settings.CLIENT_SECRET);
    String token = null;
    try {
      token = client.authenticate(username, password);
      createUserIfNotExists(username, username, null);
      logger.log(Level.INFO, token);
    }
    catch(Exception e) {
      e.printStackTrace();
      logger.log(Level.SEVERE, e.getMessage());
    }
    return (token != null);
  }

  public static void createUserIfNotExists(String name, String username, String pictureURL) {
    SessionManager session = SessionManager.getInstance();
    UserDAO userDao = new UserDAO(session.getEntityManager());
    List<User> users = userDao.findByAttribute("login", username);
    if(users.isEmpty()) {
      session.begin();
      logger.log(Level.INFO, "Creating user: " + username);
      User userEntity = new User();
      userEntity.setLogin(username);
      userEntity.setName(name);
      userEntity.setPicture(pictureURL);
      userDao.save(userEntity);
      session.commit();
    }
  }
  
  
  
  

  /**
   * Busca o usuário no banco de dados da aplicação.
   *
   * @param username
   * @param password
   * @return
   */
  private boolean authenticateDataBase(String username, String password) {
    UserBusiness business = new UserBusiness(SessionManager.getInstance());
    List<User> result = business.findByLogin(username, 1, 0);
    return (result.size() > 0) && Hash.md5(password).equals(result.get(0).getHashedPassword());
  }
  
  	private ClientConfig configureClientWithSSL() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };

		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, certs, new SecureRandom());
		} catch (java.security.GeneralSecurityException ex) {
			// NoCommand
		}

		HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
		ClientConfig config = new DefaultClientConfig();
		try {
			config.getProperties().put(
					HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
					new HTTPSProperties(new HostnameVerifier() {
						@Override
						public boolean verify(String hostname,
								SSLSession session) {
							return true;
						}
					}, ctx));
		} catch (Exception e) {
			// NoCommand
		}

		return config;
	}

	private Client createClientWithSSL() {
		return Client.create(configureClientWithSSL());
	}

  private void logout(HttpServletRequest request, HttpServletResponse response) {
    Object accessToken = request.getSession().getAttribute("accessToken");
    request.getSession().invalidate();
    if(accessToken != null) {
      try {
        OAuth2CodeSettings settings = (OAuth2CodeSettings)request.getSession().getAttribute("settings");
        RevokeServlet.revoke(settings, accessToken.toString());
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void changePassword(HttpServletRequest req, HttpServletResponse resp) {
    Object username = req.getSession().getAttribute("username");
    if(username != null) {
      User user = this.getUserByName(username.toString());
      if(user != null) {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String newPasswordConfirmation = req.getParameter("newPasswordConfirmation");

        if(user.getHashedPassword().equals(Hash.md5(oldPassword)) && newPassword.equals(newPasswordConfirmation) &&
                !newPassword.isEmpty()) {
          user.setPassword(newPassword);
          SessionManager session = SessionManager.getInstance();
          UserBusiness business = new UserBusiness(session);
          session.begin();
          business.update(user);
          session.commit();
        }
        else {
          resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
      }
    }
  }

  // private User getUserByName(String username) {
  //   UserDAO userDao = new UserDAO(SessionManager.getInstance().getEntityManager());
  //   List<User> users = userDao.findByAttribute("login", username.toString());
  //   if(!users.isEmpty()) {
  //     return users.get(0);
  //   }
  //   return null;
  // }

}
