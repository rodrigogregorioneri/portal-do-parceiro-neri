package security.dao;

import javax.persistence.*;
import security.entity.*;
import java.util.*;
import java.io.Serializable;

/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * @generated
 */
public class UserRoleDAO extends BasicDAO<String, UserRole> implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = -201887997l;

  /**
   * Guarda uma cópia da EntityManager na instância
   * 
   * @param entitymanager
   *          Tabela do banco
   * @generated
   */
  public UserRoleDAO(EntityManager entitymanager) {
    super(entitymanager);
  }



  /**
   * Remove a instância de UserRole utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */  
  public int deleteById(java.lang.String id){
      Query query = this.entityManager.createQuery("DELETE FROM UserRole entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return query.executeUpdate();	
  }
  
  /**
   * Obtém a instância de UserRole utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */  
  public UserRole findById(java.lang.String id){
      TypedQuery<UserRole> query = this.entityManager.createQuery("SELECT entity FROM UserRole entity WHERE entity.id = :id", UserRole.class);
      query.setParameter("id", id);
           
      return query.getSingleResult();	
  }



  /**
   * NamedQuery list
   * @generated
   */
  public List<UserRole> list(int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleList", UserRole.class).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  /**
   * NamedQuery findByUser
   * @generated
   */
  public List<UserRole> findByUser(User user, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByUser", UserRole.class).setParameter("user", user).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  /**
   * NamedQuery findByEmail
   * @generated
   */
  public List<UserRole> findByEmail(java.lang.String email, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByEmail", UserRole.class).setParameter("email", email).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  /**
   * NamedQuery findByLogin
   * @generated
   */
  public List<UserRole> findByLogin(java.lang.String login, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByLogin", UserRole.class).setParameter("login", login).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  /**
   * NamedQuery findByRole
   * @generated
   */
  public List<UserRole> findByRole(java.lang.String roleid, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByRole", UserRole.class).setParameter("roleid", roleid).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
}