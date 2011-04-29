/* 
 * Project name: InteliMed Intermediate Core
 * Class name: User.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Marinho
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.model.entity;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <Insert_class_comment>
 * 
 * @author Marinho
 * 
 */
@Entity
@Table(name = "user")
public class User extends IntelimedEntity {

	private String name;
	private String cpf;
	private String password;
	
	// TODO: profile
	//private Profile profile;
	// TODO: enabled
	//private boolean enabled;
	
	/** {@inheritDoc} */
    @Override
	public boolean equals(Object obj) {
    	boolean result = super.equals(obj);
        if(!result) {
        	if(obj != null && (obj.getClass().equals(getClass()))) {
        		User u = (User) obj;
        		
        		// TODO: VERIFICACAO NAO EH POR NOME, EH PELO Q?
        		if(u.getName().equals(this.getName())){
        			if(u.getPassword().equals(this.getPassword())){
        				result = true;
        			}
        		}        		
        	}
        }
        return result;
	}
	
	/** {@inheritDoc} */
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @Override
    public Long getId() {
        return this.id;
    }
	
	/**
	 * Return the value of attribute name.
	 *
	 * @return The value of attribute name.
	 */
    @Column(name = "name")
	public String getName() {
		return name;
	}
	/**
	 * Set a value to attribute name.
	 *
	 * @param name The name to be set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Return the value of attribute cpf.
	 *
	 * @return The value of attribute cpf.
	 */
	@Column(name = "cpf")
	public String getCpf() {
		return cpf;
	}
	/**
	 * Set a value to attribute cpf.
	 *
	 * @param cpf The cpf to be set.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * Return the value of attribute password.
	 *
	 * @return The value of attribute password.
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	/**
	 * Set a value to attribute password.
	 *
	 * @param password The password to be set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
/*    /**
     * Return the value of attribute profile.
     * 
     * @return The value of attribute profile.
     *//*
    @ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "profile_id")
    public Profile getProfile() {
        return profile;
    }

    *//**
     * Set a value to attribute profile.
     * 
     * @param profile
     *            The profile to be set.
     *//*
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }*/
}
