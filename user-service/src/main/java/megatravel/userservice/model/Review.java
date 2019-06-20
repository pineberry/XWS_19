package megatravel.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Review", propOrder = {
    "id",
    "reviewContent",
    "mark"
})
@Entity
@Data
public class Review {

	@Id
	@GeneratedValue
    protected long id;
    @XmlElement(name = "review_content", required = true)
    protected String reviewContent;
    protected long mark;

}
