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
@Path("/User")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserREST implements RESTService<User> {
  /**
   * @generated
   */
  private SessionManager session;
  /**
   * @generated
   */  
  private UserBusiness business;
  /**
   * @generated
   */
  private RoleBusiness roleBusiness;
  /**
   * @generated
   */
  private UserRoleBusiness userRoleBusiness;
  /**
   * @generated
   */  
  @Context 
  private HttpServletRequest request;

  /**
   * @generated
   */
  public UserREST() {
    this.session = SessionManager.getInstance();
    this.session.getEntityManager().clear();
    this.business = new UserBusiness(session);
    this.roleBusiness = new RoleBusiness(session);
    this.userRoleBusiness = new UserRoleBusiness(session);
  }
  
  /**
   * @generated
   */  
  @POST
  public Response post(User entity) {
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
  public Response put(User entity) {
    try {
	    session.begin();
	    User updatedEntity = business.update(entity);
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
  public Response putWithId(User entity) {
    try {
	    session.begin();
	    User updatedEntity = business.update(entity);
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
  public Response delete(User entity) {  
		try {
			session.begin();
			User updatedEntity = business.update(entity);
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
   * OneToMany Relationship GET
   * @generated
   */
  @GET
  @Path("/{instanceId}/UserRole")
  public GenericEntity<List<UserRole>> findUserRole(@PathParam("instanceId") java.lang.String instanceId, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return new GenericEntity<List<UserRole>>(this.business.findUserRole(instanceId, limit, offset)){};
  }
  
  /**
   * OneToMany Relationship DELETE 
   * @generated
   */  
  @DELETE
  @Path("/{instanceId}/UserRole/{relationId}")
  public Response deleteUserRole(@PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			if (this.userRoleBusiness.deleteById(relationId) > 0) {
				session.commit();
				return Response.ok().build();
			} else {
				session.rollBack();
				return Response.status(404).build();
			}
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }
  
  /**
   * OneToMany Relationship PUT
   * @generated
   */  
  @PUT
  @Path("/{instanceId}/UserRole/{relationId}")
  public Response putUserRole(UserRole entity, @PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			UserRole updatedEntity = this.userRoleBusiness.update(entity);
			session.commit();
			return Response.ok(updatedEntity).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }  
  
  /**
   * OneToMany Relationship POST
   * @generated
   */  
  @POST
  @Path("/{instanceId}/UserRole")
  public Response postUserRole(UserRole entity, @PathParam("instanceId") java.lang.String instanceId) {
		try {
			session.begin();
			User user = this.business.findById(instanceId);
			entity.setUser(user);
			this.userRoleBusiness.save(entity);
			session.commit();
			this.userRoleBusiness.refresh(entity);
			return Response.ok(entity).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }   
  


  /**
   * ManyToMany Relationship GET
   * @generated
   */
  @GET
  @Path("/{instanceId}/Role")
  public GenericEntity<List<Role>> listRole(@PathParam("instanceId") java.lang.String instanceId, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return new GenericEntity<List<Role>>(this.business.listRole(instanceId, limit, offset)){};
  }
  
  /**
   * ManyToMany Relationship POST
   * @generated
   */  
  @POST
  @Path("/{instanceId}/Role")
  public Response postRole(Role entity, @PathParam("instanceId") java.lang.String instanceId) {
		try {
			session.begin();
			UserRole newUserRole = new UserRole();

			User instance = this.business.findById(instanceId);


			newUserRole.setRole(entity);
			newUserRole.setUser(instance);
			
			this.userRoleBusiness.save(newUserRole);
			session.commit();
			this.userRoleBusiness.refresh(newUserRole);
			return Response.ok(newUserRole.getUser()).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }   
  
  /**
   * ManyToMany Relationship DELETE
   * @generated
   */  
  @DELETE
  @Path("/{instanceId}/Role/{relationId}")
  public Response deleteRole(@PathParam("instanceId") java.lang.String instanceId, @PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			if (this.business.deleteRole(instanceId, relationId) > 0) {
				session.commit();
				return Response.ok().build();
			} else {
				session.rollBack();
				return Response.status(404).build();
			}
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }  
  
  
  /**
   * NamedQuery list
   * @generated
   */
  @GET
  	
  public GenericEntity<List<User>> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<User>>(business.list(limit, offset)){};

  }
  /**
   * NamedQuery findByRole
   * @generated
   */
  @GET
  @Path("/findByRole/{roleid}")	
  public GenericEntity<List<User>> findByRole(@PathParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<User>>(business.findByRole(roleid, limit, offset)){};

  }
  /**
   * NamedQuery findByLogin
   * @generated
   */
  @GET
  @Path("/findByLogin/{login}")	
  public GenericEntity<List<User>> findByLogin(@PathParam("login")java.lang.String login, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<User>>(business.findByLogin(login, limit, offset)){};

  }
	
  /**
   * NamedQuery findByRole
   * @generated
   */
  @GET
  @Path("/findByRole")	
  public GenericEntity<List<User>> findByRoleParams(@QueryParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<User>>(business.findByRole(roleid, limit, offset)){};	
  }
  /**
   * NamedQuery findByLogin
   * @generated
   */
  @GET
  @Path("/findByLogin")	
  public GenericEntity<List<User>> findByLoginParams(@QueryParam("login")java.lang.String login, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<User>>(business.findByLogin(login, limit, offset)){};	
  }
}
