package megatravel.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
	@XmlElement(required = true)
    private long accommodationUnitId;
    
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
	private String status;
	
	@XmlElement(required = true)
	private boolean confirmed;
	
	public Reservation() {
		
	}

	public Reservation(long id, long accommodationUnitId, long guestId, Date checkInDate, Date checkOutDate,
			double totalPrice, String status, boolean confirmed) {
		super();
		this.id = id;
		this.accommodationUnitId = accommodationUnitId;
		this.guestId = guestId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
		this.status = status;
		this.confirmed = confirmed;
	}

	public Reservation(long accommodationUnitId, long guestId, Date checkInDate, Date checkOutDate, double totalPrice, String status, boolean confirmed) {
		super();
		this.accommodationUnitId = accommodationUnitId;
		this.guestId = guestId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
		this.status = status;
		this.confirmed = confirmed;
	}
	
	
}
