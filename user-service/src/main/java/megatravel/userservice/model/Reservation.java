package megatravel.userservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation", propOrder = {
    "id",
    "accommodationUnit",
    "checkInDate",
    "checkOutDate",
    "totalPrice",
    "chatId",
    "guestId"
})
@Entity
@Table
@Data
public class Reservation {

	@Id
	@GeneratedValue
    private long id;
    
	@XmlElement(name = "accommodation_unit", required = true)
    @OneToOne
    @JoinColumn(name = "accommodation_unit", nullable = false)
    private AccommodationUnit accommodationUnit;
    
	@XmlElement(required = true)
    private long guestId; //user that booked a accommodation
    
	@XmlElement(name = "check_in_date", required = true)
    @XmlSchemaType(name = "date")
    private Date checkInDate;
    
	@XmlElement(name = "check_out_date", required = true)
    @XmlSchemaType(name = "date")
    private Date checkOutDate;
    
	@XmlElement(name = "total_price", required = true)
    private double totalPrice;
    
	@XmlElement(required = true)
    @OneToOne
    @JoinColumn(name = "chatId", nullable = true)
    private Chat chat;
	
	@XmlElement(required = true)
	private String status;
	
	@XmlElement(required = true)
	private boolean confirmed;

	public Reservation(AccommodationUnit accommodationUnit, long user, Date checkInDate, Date checkOutDate,
			double totalPrice, Chat chat, String status, boolean confirmed) {
		super();
		this.accommodationUnit = accommodationUnit;
		this.guestId = user;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
		this.chat = chat;
		this.status = status;
		this.confirmed = confirmed;
	}

	public Reservation() {
		
	}
	
}
