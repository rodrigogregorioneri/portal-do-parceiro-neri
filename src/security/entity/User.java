package security.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

/**
 * Classe que representa a tabela USER
 * @generated
 */
@Entity
@Table(name = "\"USER\"")
@NamedQueries({
        @NamedQuery(name = "userList", query = "select u from User u"),
        @NamedQuery(name = "userFindByRole", query = "select u.user from UserRole u where u.role.id = :roleid"),
        @NamedQuery(name = "userFindByLogin", query = "select u from User u where u.login = :login")
})
@XmlRootElement
public class User implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 2648042l;
	
	/**
	 * @generated
	 */
	@Column(name = "email", nullable = true, unique = false)
	private java.lang.String email;
	
	/**
	 * @generated
	 */
	@Column(name = "name", nullable = false, unique = false)
	private java.lang.String name;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id")
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@Column(name = "login", nullable = false, unique = true)
	private java.lang.String login;
	
	/**
	 * @generated
	 */
	@Column(name = "picture", nullable = true, unique = false)
	private java.lang.String picture;
	
	/**
	 * @generated
	 */
	@Column(name = "password", nullable = false, unique = false)
	private java.lang.String password;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public User(){
	}

	
	/**
	 * Obtém email
	 * @param email email
	 * return email
	 * @generated
	 */
	public java.lang.String getEmail(){
		return this.email;
	}
	
	/**
	 * Define email
	 * @param email email
	 * @generated
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	
	/**
	 * Obtém name
	 * @param name name
	 * return name
	 * @generated
	 */
	public java.lang.String getName(){
		return this.name;
	}
	
	/**
	 * Define name
	 * @param name name
	 * @generated
	 */
	public void setName(java.lang.String name){
		this.name = name;
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
	 * Obtém login
	 * @param login login
	 * return login
	 * @generated
	 */
	public java.lang.String getLogin(){
		return this.login;
	}
	
	/**
	 * Define login
	 * @param login login
	 * @generated
	 */
	public void setLogin(java.lang.String login){
		this.login = login;
	}
	
	/**
	 * Obtém picture
	 * @param picture picture
	 * return picture
	 * @generated
	 */
	public java.lang.String getPicture(){
		return this.picture;
	}
	
	/**
	 * Define picture
	 * @param picture picture
	 * @generated
	 */
	public void setPicture(java.lang.String picture){
		this.picture = picture;
	}
	
	/**
	 * Obtém password
	 * @param password password
	 * return password
	 * @generated
	 */
	public java.lang.String getPassword(){
		return this.password;
	}
	
	/**
	 * Define password
	 * @param password password
	 * @generated
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
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
	    
	    if(!(obj instanceof User))
	      return false;
	    
	    User other = (User)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
	
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final byte[] KEY = util.Hash.md5(String.valueOf(serialVersionUID)).substring(0, 16).getBytes();
	private static final String MAGIC_STRING = "*!&$%#@";

	private String decodePassword(String password) {
		if (password != null) {
			if (password.startsWith(MAGIC_STRING)) {
				Key key = new SecretKeySpec(KEY, "AES");
				String value = password.substring(MAGIC_STRING.length());
				try {
					Cipher c = Cipher.getInstance(ALGORITHM);
					c.init(Cipher.DECRYPT_MODE, key);
					return new String(c.doFinal(Base64.getDecoder().decode(value.getBytes())));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else {
				return util.Hash.md5(password);
			}
		}

		return null;
	}

	private String encodePassword(String password) {
		if (password != null) {
			if (!password.startsWith(MAGIC_STRING)) {
				Key key = new SecretKeySpec(KEY, "AES");
				try {
					Cipher c = Cipher.getInstance(ALGORITHM);
					c.init(Cipher.ENCRYPT_MODE, key);
					return MAGIC_STRING + Base64.getEncoder().encodeToString(c.doFinal(password.getBytes()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

		return password;
	}

	@PrePersist
	@PreUpdate
	public void unmaskPassword() {
		this.password = this.decodePassword(this.password);
	}

	@PostLoad
	public void maskPassword() {
		this.password = this.encodePassword(this.password);
	}

	public String getHashedPassword() {
		return this.decodePassword(this.password);
	}
}