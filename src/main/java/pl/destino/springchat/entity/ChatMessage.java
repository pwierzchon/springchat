package pl.destino.springchat.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "CHAT_MESSAGE")
public class ChatMessage implements Serializable {

    @Id
    @Column(name = "MESSAGE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "MESSAGE_TEXT", nullable = false)
    private String messageText;

    @Column(name = "MESSAGE_DATE")
    private String messageDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private ChatUser user;

    public ChatMessage() {

    }

    public ChatMessage(Long messageId, String messageText, String messageDate, ChatUser user) {
        super();
        this.messageId = messageId;
        this.messageText = messageText;
        this.messageDate = messageDate;
        this.user = user;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }

}
