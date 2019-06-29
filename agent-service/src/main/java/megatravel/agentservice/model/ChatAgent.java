package megatravel.agentservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Chat", propOrder = {
    "id",
    "messages"
})
@Entity
@Data
public class ChatAgent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @XmlElement(required = true)
    @OneToMany(targetEntity = MessageAgent.class)
    private List<MessageAgent> messages;

}
