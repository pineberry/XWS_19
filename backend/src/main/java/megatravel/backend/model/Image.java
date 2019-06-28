package megatravel.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Image", propOrder = {
    "id",
    "src"
})
@Entity
@Data
public class Image {

	@Id
	@GeneratedValue
    protected long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String src;

    public Image(String src) {
		super();
		this.src = src;
	}

}

