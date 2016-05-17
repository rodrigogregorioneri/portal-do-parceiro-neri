package security.rest;


import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.persistence.*;

import security.rest.util.*;

import security.dao.*;
import security.entity.*;
import security.business.*;
import javax.servlet.http.HttpServletRequest;

import security.rest.exceptions.*;


/**
 * Publicando metodos de negocio via REST
 * @generated
 **/
@Path("/UserRole")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserRoleREST implements RESTService<UserRole> {
  /**
   * @generated
   */
  private SessionManager session;
  /**
   * @generated
   */  
  private UserRoleBusiness business;
  /**
   * @generated
   */  
  @Context 
  private HttpServletRequest request;

  /**
   * @generated
   */
  public UserRoleREST() {
    this.session = SessionManager.getInstance();
    this.session.getEntityManager().clear();
    this.business = new UserRoleBusiness(session);
  }
  
  /**
   * @generated
   */  
  @POST
  public Response post(UserRole entity) {
    try {
	    session.begin();
	    business.save(entity);
	    session.commit();
	    business.refresh(entity);
	    return Response.ok(entity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }
  }

  /**
   * @generated
   */
  @PUT
  public Response put(UserRole entity) {
    try {
	    session.begin();
	    UserRole updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  /**
   * @generated
   */  
  @PUT
  @Path("/{id}")
  public Response putWithId(UserRole entity) {
    try {
	    session.begin();
	    UserRole updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  /**
   * @generated
   */  
  @DELETE
  public Response delete(UserRole entity) {  
		try {
			session.begin();
			UserRole updatedEntity = business.update(entity);
			business.delete(updatedEntity);
			session.commit();
			return Response.ok().build();
		}

		catch (Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);
		}    
  } 
   
  /**
   * @generated
   */    
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") java.lang.String id) {  
		try {
			session.begin();
			if (business.deleteById(id) > 0) {
				session.commit();
				return Response.ok().build();
			} else {
				return Response.status(404).build();
			}
		}

		catch (Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);
		}    
  }
  
  
  


  
  /**
   * NamedQuery list
   * @generated
   */
  @GET
  	
  public GenericEntity<List<UserRole>> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.list(limit, offset)){};

  }
  /**
   * NamedQuery findByUser
   * @generated
   */
  @GET
  @Path("/findByUser/{user}")	
  public GenericEntity<List<UserRole>> findByUser(@PathParam("user")User user, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByUser(user, limit, offset)){};

  }
  /**
   * NamedQuery findByEmail
   * @generated
   */
  @GET
  @Path("/findByEmail/{email}")	
  public GenericEntity<List<UserRole>> findByEmail(@PathParam("email")java.lang.String email, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByEmail(email, limit, offset)){};

  }
  /**
   * NamedQuery findByLogin
   * @generated
   */
  @GET
  @Path("/findByLogin/{login}")	
  public GenericEntity<List<UserRole>> findByLogin(@PathParam("login")java.lang.String login, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByLogin(login, limit, offset)){};

  }
  /**
   * NamedQuery findByRole
   * @generated
   */
  @GET
  @Path("/findByRole/{roleid}")	
  public GenericEntity<List<UserRole>> findByRole(@PathParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByRole(roleid, limit, offset)){};

  }
	
  /**
   * NamedQuery findByUser
   * @generated
   */
  @GET
  @Path("/findByUser")	
  public GenericEntity<List<UserRole>> findByUserParams(@QueryParam("user")User user, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByUser(user, limit, offset)){};	
  }
  /**
   * NamedQuery findByEmail
   * @generated
   */
  @GET
  @Path("/findByEmail")	
  public GenericEntity<List<UserRole>> findByEmailParams(@QueryParam("email")java.lang.String email, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByEmail(email, limit, offset)){};	
  }
  /**
   * NamedQuery findByLogin
   * @generated
   */
  @GET
  @Path("/findByLogin")	
  public GenericEntity<List<UserRole>> findByLoginParams(@QueryParam("login")java.lang.String login, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByLogin(login, limit, offset)){};	
  }
  /**
   * NamedQuery findByRole
   * @generated
   */
  @GET
  @Path("/findByRole")	
  public GenericEntity<List<UserRole>> findByRoleParams(@QueryParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<UserRole>>(business.findByRole(roleid, limit, offset)){};	
  }
}
