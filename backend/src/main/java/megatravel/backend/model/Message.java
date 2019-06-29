package megatravel.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@XmlElement(name = "chat_id", required = true)
	private long chatId;
    @XmlElement(name = "message_content", required = true)
    private String messageContent;
    private Date date;
    @XmlElement(required = true)
    @OneToOne
    @JoinColumn(name = "sender", nullable = false)
    private User sender;
    @XmlElement(required = true)
    @OneToOne
    @JoinColumn(name = "reciever", nullable = false)
    private User reciever;
}
