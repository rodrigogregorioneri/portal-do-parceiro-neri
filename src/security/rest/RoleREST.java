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
@Path("/Role")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class RoleREST implements RESTService<Role> {
  /**
   * @generated
   */
  private SessionManager session;
  /**
   * @generated
   */  
  private RoleBusiness business;
  /**
   * @generated
   */
  private UserBusiness userBusiness;
  /**
   * @generated
   */
  private UserRoleBusiness userRoleBusiness;
  /**
   * @generated
   */
  private PermissionBusiness permissionBusiness;
  /**
   * @generated
   */  
  @Context 
  private HttpServletRequest request;

  /**
   * @generated
   */
  public RoleREST() {
    this.session = SessionManager.getInstance();
    this.session.getEntityManager().clear();
    this.business = new RoleBusiness(session);
    this.userBusiness = new UserBusiness(session);
    this.userRoleBusiness = new UserRoleBusiness(session);
    this.permissionBusiness = new PermissionBusiness(session);
  }
  
  /**
   * @generated
   */  
  @POST
  public Response post(Role entity) {
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
  public Response put(Role entity) {
    try {
	    session.begin();
	    Role updatedEntity = business.update(entity);
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
  public Response putWithId(Role entity) {
    try {
	    session.begin();
	    Role updatedEntity = business.update(entity);
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
  public Response delete(Role entity) {  
		try {
			session.begin();
			Role updatedEntity = business.update(entity);
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
			Role role = this.business.findById(instanceId);
			entity.setRole(role);
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
   * OneToMany Relationship GET
   * @generated
   */
  @GET
  @Path("/{instanceId}/Permission")
  public GenericEntity<List<Permission>> findPermission(@PathParam("instanceId") java.lang.String instanceId, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return new GenericEntity<List<Permission>>(this.business.findPermission(instanceId, limit, offset)){};
  }
  
  /**
   * OneToMany Relationship DELETE 
   * @generated
   */  
  @DELETE
  @Path("/{instanceId}/Permission/{relationId}")
  public Response deletePermission(@PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			if (this.permissionBusiness.deleteById(relationId) > 0) {
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
  @Path("/{instanceId}/Permission/{relationId}")
  public Response putPermission(Permission entity, @PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			Permission updatedEntity = this.permissionBusiness.update(entity);
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
  @Path("/{instanceId}/Permission")
  public Response postPermission(Permission entity, @PathParam("instanceId") java.lang.String instanceId) {
		try {
			session.begin();
			Role role = this.business.findById(instanceId);
			entity.setRole(role);
			this.permissionBusiness.save(entity);
			session.commit();
			this.permissionBusiness.refresh(entity);
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
  @Path("/{instanceId}/User")
  public GenericEntity<List<User>> listUser(@PathParam("instanceId") java.lang.String instanceId, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return new GenericEntity<List<User>>(this.business.listUser(instanceId, limit, offset)){};
  }
  
  /**
   * ManyToMany Relationship POST
   * @generated
   */  
  @POST
  @Path("/{instanceId}/User")
  public Response postUser(User entity, @PathParam("instanceId") java.lang.String instanceId) {
		try {
			session.begin();
			UserRole newUserRole = new UserRole();

			Role instance = this.business.findById(instanceId);


			newUserRole.setUser(entity);
			newUserRole.setRole(instance);
			
			this.userRoleBusiness.save(newUserRole);
			session.commit();
			this.userRoleBusiness.refresh(newUserRole);
			return Response.ok(newUserRole.getRole()).build();
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
  @Path("/{instanceId}/User/{relationId}")
  public Response deleteUser(@PathParam("instanceId") java.lang.String instanceId, @PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			if (this.business.deleteUser(instanceId, relationId) > 0) {
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
  	
  public GenericEntity<List<Role>> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<Role>>(business.list(limit, offset)){};

  }
	
}
