/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.springchat.util;

import pl.destino.springchat.entity.ChatMessage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Destino
 */
public class MessageUtils {

    public static Map<String, Object> mapMessage(ChatMessage msg) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        if (msg != null) {
            retMap.put("status", "success");
            retMap.put("user", msg.getUser().getUserName());
            retMap.put("date", msg.getMessageDate());
            retMap.put("message", msg.getMessageText());
        }
        return retMap;
    }
}
