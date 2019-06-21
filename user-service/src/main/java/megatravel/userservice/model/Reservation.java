package megatravel.userservice.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    "guestId",
    "checkInDate",
    "checkOutDate",
    "totalPrice",
    "chatId"
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
    @OneToOne
    @JoinColumn(name = "guestId", nullable = false)
    private User user; //user that booked a accommodation
    
	@XmlElement(name = "check_in_date", required = true)
    @XmlSchemaType(name = "date")
    private Date checkInDate;
    
	@XmlElement(name = "check_out_date", required = true)
    @XmlSchemaType(name = "date")
    private Date checkOutDate;
    
	@XmlElement(name = "total_price", required = true)
    private BigDecimal totalPrice;
    
	@XmlElement(required = true)
    @OneToOne
    @JoinColumn(name = "chatId", nullable = true)
    private Chat chat;

}
