package security.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * Classe que representa a tabela USERROLE
 * @generated
 */
@Entity
@Table(name = "\"USERROLE\"")
@NamedQueries({
        @NamedQuery(name = "userRoleList", query = "select u from UserRole u"),
        @NamedQuery(name = "userRoleFindByUser", query = "select u from UserRole u where u.user = :user "),
        @NamedQuery(name = "userRoleFindByEmail", query = "select u from UserRole u where u.user.email = :email"),
        @NamedQuery(name = "userRoleFindByLogin", query = "select u from UserRole u where u.user.login = :login"),
        @NamedQuery(name = "userRoleFindByRole", query = "select u from UserRole u where u.role.id = :roleid")
})
@XmlRootElement
public class UserRole implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = -201887997l;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id")
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@ManyToOne
	@JoinColumn(name="fk_user", referencedColumnName = "id")
	private User user;
	
	/**
	 * @generated
	 */
	@ManyToOne
	@JoinColumn(name="fk_role", referencedColumnName = "id")
	private Role role;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public UserRole(){
	}

	
	/**
	 * Obtém id
	 * @param id id
	 * return id
	 * @generated
	 */
	public java.lang.String getId(){
		return this.id;
	}
	
	/**
	 * Define id
	 * @param id id
	 * @generated
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	/**
	 * Obtém user
	 * @param user user
	 * return user
	 * @generated
	 */
	public User getUser(){
		return this.user;
	}
	
	/**
	 * Define user
	 * @param user user
	 * @generated
	 */
	public void setUser(User user){
		this.user = user;
	}
	
	/**
	 * Obtém role
	 * @param role role
	 * return role
	 * @generated
	 */
	public Role getRole(){
		return this.role;
	}
	
	/**
	 * Define role
	 * @param role role
	 * @generated
	 */
	public void setRole(Role role){
		this.role = role;
	}
	
	/**
	 * @generated
	 */
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }
	
	/**
	 * @generated
	 */	
	@Override
  	public boolean equals(Object obj) {
    
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof UserRole))
	      return false;
	    
	    UserRole other = (UserRole)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
}