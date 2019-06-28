package megatravel.agentservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = {
    "id",
    "chatId",
    "messageContent",
    "date",
    "sender",
    "reciever"
})
@Entity
@Data
public class MessageAgent {

	@Id
	@GeneratedValue
    private long id;
	@XmlElement(name = "chat_id", required = true)
	private long chatId;
    @XmlElement(name = "message_content", required = true)
    private String messageContent;
    private Date date;
    @XmlElement(required = true)
    private Long senderId;
    @XmlElement(required = true)
    private Long recieverId;
}
