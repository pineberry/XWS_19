package megatravel.agentservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
    "id",
    "typeOfUser",
    "firstName",
    "lastName",
    "username",
    "password",
    "address",
    "pib",
    "reservations"
})
@Entity
@Table
@Data
public class User {

	@Id
	@GeneratedValue
    private long id;
    @XmlElement(name = "type_of_user", required = true)
    private String typeOfUser;
    @XmlElement(name = "first_name", required = true)
    private String firstName;
    @XmlElement(name = "last_name", required = true)
    private String lastName;
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String password;
    //required since these are Agent fields
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private Long pib;
    
    @OneToMany(targetEntity = Reservation.class)
    private List<Reservation> reservations;
	
    public User() {
	
	}

	public User(long id, String typeOfUser, String firstName, String lastName, String username, String password,
			String address, Long pib, List<Reservation> reservations) {
		super();
		this.id = id;
		this.typeOfUser = typeOfUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.address = address;
		this.pib = pib;
		this.reservations = reservations;
	}
	
}
