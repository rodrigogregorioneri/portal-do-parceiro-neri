package security.dao;

import javax.persistence.*;
import security.entity.*;
import java.util.*;
import java.io.Serializable;

/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * @generated
 */
public class PermissionDAO extends BasicDAO<String, Permission> implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 1475848671l;

  /**
   * Guarda uma cópia da EntityManager na instância
   * 
   * @param entitymanager
   *          Tabela do banco
   * @generated
   */
  public PermissionDAO(EntityManager entitymanager) {
    super(entitymanager);
  }



  /**
   * Remove a instância de Permission utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */  
  public int deleteById(java.lang.String id){
      Query query = this.entityManager.createQuery("DELETE FROM Permission entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return query.executeUpdate();	
  }
  
  /**
   * Obtém a instância de Permission utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */  
  public Permission findById(java.lang.String id){
      TypedQuery<Permission> query = this.entityManager.createQuery("SELECT entity FROM Permission entity WHERE entity.id = :id", Permission.class);
      query.setParameter("id", id);
           
      return query.getSingleResult();	
  }



  /**
   * NamedQuery list
   * @generated
   */
  public List<Permission> list(int limit, int offset){
      return this.entityManager.createNamedQuery("permissionList", Permission.class).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
}