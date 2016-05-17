package security.dao;

import javax.persistence.*;
import security.entity.*;
import java.util.*;
import java.io.Serializable;

/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * @generated
 */
public class RoleDAO extends BasicDAO<String, Role> implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 2555048l;

  /**
   * Guarda uma cópia da EntityManager na instância
   * 
   * @param entitymanager
   *          Tabela do banco
   * @generated
   */
  public RoleDAO(EntityManager entitymanager) {
    super(entitymanager);
  }



  /**
   * Remove a instância de Role utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */  
  public int deleteById(java.lang.String id){
      Query query = this.entityManager.createQuery("DELETE FROM Role entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return query.executeUpdate();	
  }
  
  /**
   * Obtém a instância de Role utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */  
  public Role findById(java.lang.String id){
      TypedQuery<Role> query = this.entityManager.createQuery("SELECT entity FROM Role entity WHERE entity.id = :id", Role.class);
      query.setParameter("id", id);
           
      return query.getSingleResult();	
  }

  /**
   * OneToMany Relation
   * @generated
   */
  public List<UserRole> findUserRole(java.lang.String id, int limit, int offset) {
      TypedQuery<UserRole> query = this.entityManager.createQuery("SELECT entity FROM UserRole entity WHERE entity.role.id = :id", UserRole.class);
      query.setParameter("id", id);

      return query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }
  /**
   * OneToMany Relation
   * @generated
   */
  public List<Permission> findPermission(java.lang.String id, int limit, int offset) {
      TypedQuery<Permission> query = this.entityManager.createQuery("SELECT entity FROM Permission entity WHERE entity.role.id = :id", Permission.class);
      query.setParameter("id", id);

      return query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }

  /**
   * ManyToOne Relation
   * @generated
   */
  public List<User> listUser(java.lang.String id, int limit, int offset) {
      TypedQuery<User> query = this.entityManager.createQuery("SELECT entity.user FROM UserRole entity WHERE entity.role.id = :id", User.class);
      query.setParameter("id", id);

      return query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }
  
    /**
     * ManyToOne Relation Delete
     * @generated
     */
    public int deleteUser(java.lang.String instanceId, java.lang.String relationId) {
      Query query = this.entityManager.createQuery("DELETE FROM UserRole entity WHERE entity.role.id = :instanceId AND entity.user.id = :relationId");
      query.setParameter("instanceId", instanceId);
      query.setParameter("relationId", relationId);

      return query.executeUpdate();	  
  }
  

  /**
   * NamedQuery list
   * @generated
   */
  public List<Role> list(int limit, int offset){
      return this.entityManager.createNamedQuery("roleList", Role.class).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
}