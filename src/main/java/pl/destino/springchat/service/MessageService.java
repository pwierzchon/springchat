/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.springchat.service;

import pl.destino.springchat.entity.ChatMessage;
import pl.destino.springchat.repo.MessageRepository;
import pl.destino.springchat.util.ChatException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Destino
 */
@Component
public class MessageService implements IMessageService {

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageRepository msgRepo;

    @Override
    public Iterable<ChatMessage> getMessages() {
        return msgRepo.findAll();
    }

    @Override
    public ChatMessage addMessage(Map<String, Object> book) throws ChatException {
        ChatMessage libBook = new ChatMessage();
        libBook.setMessageText((String) book.get("message"));
        libBook.setMessageDate((String) book.get("date"));
        libBook.setUser(userService.getCurrentUser());

        libBook = msgRepo.save(libBook);

        return libBook;
    }

}
