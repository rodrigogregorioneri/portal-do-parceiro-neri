package security.business;

import security.dao.*;
import security.entity.*;

import java.util.*;

/**
 * Classe que representa a camada de negócios de User
 * @generated
 **/
public class UserBusiness {

  /**
   * Instância da classe UserDAO que faz o acesso ao banco de dados
   * @generated
   */
  private UserDAO dao;
  
  /**
   * Singleton de sessão usado para abrir e fechar conexão com o banco de dados
   * @generated
   */
  protected SessionManager sessionManager;
	
  /**
   * Copia referência da sessão e instancia DAO relacionada à essa classe para manipular o banco de dados
   * 
   * @param sessionmanager
   *          Singleton de sessão
   * @generated modifiable
   */
  public UserBusiness(final SessionManager sessionmanager) {
    // begin-user-code
    // end-user-code
    this.sessionManager = sessionmanager;
    this.dao = new UserDAO(sessionmanager.getEntityManager());
    // begin-user-code
    // end-user-code
  }
  
  /**
   * Construtor padrão, inicializa singleton de sessão
   * @generated modifiable   
   */
  public UserBusiness() {
    // begin-user-code
    // end-user-code  
    this(SessionManager.getInstance());
    // begin-user-code
    // end-user-code    
  }	

  /**
   * Grava valor no banco
   * 
   * @param entity Linha da tabela a ser persistida no banco de dados
   * @generated modifiable   
   */
  public void save(final User entity) {
    // begin-user-code
    // end-user-code  
    dao.save(entity);
    // begin-user-code
    // end-user-code    
  }
  
  /**
   * Dá um refresh na entidade com valores valor no banco
   * 
   * @param entity Entidade
   * @generated modifiable
   */
  public void refresh(final User entity) {
    // begin-user-code
    // end-user-code  
    dao.refresh(entity);
    // begin-user-code
    // end-user-code  
  }  
  
  /**
   * Atualiza valor do banco
   * 
   * @param entity Linha da tabela a ser atualizada
   * @generated modifiable   
   */
  public User update(final User entity) {
    // begin-user-code
    // end-user-code  
	User updatedEntity = dao.update(entity);
    // begin-user-code
    // end-user-code	
    return updatedEntity;
  }
  
  /**
   * Apaga valor do banco
   * 
   * @param entity Linha da tabela a ser excluída
   * @generated modifiable   
   */
  public void delete(final User entity){
    // begin-user-code
    // end-user-code    
	dao.delete(entity);
    // begin-user-code
    // end-user-code  	
  }
  
  /**
   * Remove a instância de User utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated modifiable   
   */  
  public int deleteById(java.lang.String id){
    // begin-user-code
    // end-user-code  
    int result = dao.deleteById(id);
    // begin-user-code
    // end-user-code    
    return result;    
  }  
  
  /**
   * Obtém a instância de User utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated modifiable
   */  
  public User findById(java.lang.String id){
    // begin-user-code
    // end-user-code  
    User entity = dao.findById(id);
    // begin-user-code
    // end-user-code      
    return entity;  
  }   
  
  /**
   * @generated modifiable
   */  
  public List<UserRole> findUserRole(java.lang.String id, int limit, int offset) {
      // begin-user-code
      // end-user-code  
      List<UserRole> result = dao.findUserRole(id, limit, offset);
      // begin-user-code  
      // end-user-code        
      return result;	  
  }


  /**
   * @generated modifiable
   */  
  public List<Role> listRole(java.lang.String id, int limit, int offset) {
      // begin-user-code
      // end-user-code  
      List<Role> result = dao.listRole(id, limit, offset);
      // begin-user-code
      // end-user-code
      return result;        	  
  }
  
  /**
   * @generated modifiable
   */    
  public int deleteRole(java.lang.String instanceId, java.lang.String relationId) {
      // begin-user-code
      // end-user-code  
      int result = dao.deleteRole(instanceId, relationId);
      // begin-user-code
      // end-user-code  
      return result;  
  }
    
  
  /**
   * @generated modifiable
   */  	
  public List<User> list(int limit, int offset){
      // begin-user-code
      // end-user-code  
      List<User> result = dao.list(limit, offset);
      // begin-user-code
      // end-user-code        
      return result;	
  }  
  /**
   * @generated modifiable
   */  	
  public List<User> findByRole(java.lang.String roleid, int limit, int offset){
      // begin-user-code
      // end-user-code  
      List<User> result = dao.findByRole(roleid, limit, offset);
      // begin-user-code
      // end-user-code        
      return result;	
  }  
  /**
   * @generated modifiable
   */  	
  public List<User> findByLogin(java.lang.String login, int limit, int offset){
      // begin-user-code
      // end-user-code  
      List<User> result = dao.findByLogin(login, limit, offset);
      // begin-user-code
      // end-user-code        
      return result;	
  }  
}
