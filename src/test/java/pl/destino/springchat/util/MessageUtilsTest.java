/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.springchat.util;

import java.util.Map;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import pl.destino.springchat.entity.ChatMessage;
import pl.destino.springchat.entity.ChatUser;
import pl.destino.springchat.util.MessageUtils;

/**
 *
 * @author Destino
 */
public class MessageUtilsTest {

    @Test
    public void mapMessageTest() {
        ChatMessage msg = new ChatMessage(1L, "Test message", "21-07-2017", new ChatUser("testUser", "passwd"));
        Map<String, Object> result = MessageUtils.mapMessage(msg);
        assertNotNull(result);
        Assert.assertEquals(msg.getUser().getUserName(), result.get("user"));
        Assert.assertEquals(msg.getMessageText(), result.get("message"));
        Assert.assertEquals(msg.getMessageDate(), result.get("date"));
    }

}
