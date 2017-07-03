/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.springchat.service;

import pl.destino.springchat.entity.ChatMessage;
import pl.destino.springchat.util.ChatException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Destino
 */
public interface IMessageService {

    public Iterable<ChatMessage> getMessages();

    public ChatMessage addMessage(Map<String, Object> message) throws ChatException;
}
