package pl.destino.springchat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.destino.springchat.entity.ChatMessage;
import pl.destino.springchat.entity.ChatUser;
import pl.destino.springchat.service.IMessageService;
import pl.destino.springchat.service.IUserService;
import pl.destino.springchat.util.ChatException;
import pl.destino.springchat.util.MessageUtils;
import java.security.Principal;
import java.util.ArrayList;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class ChatController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;


    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBook(@RequestBody Map<String, Object> input) throws ChatException {

        ChatMessage msg = messageService.addMessage(input);

        Map<String, Object> retMap = MessageUtils.mapMessage(msg);

        ResponseEntity<Map<String, Object>> retValue = new ResponseEntity<Map<String, Object>>(retMap, HttpStatus.OK);
        return retValue;
    }

    @RequestMapping(path="/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        Iterable<ChatMessage> messages = messageService.getMessages();
        List<Map<String, Object>> result = new ArrayList<>();
        messages.forEach((t) -> {
            result.add(MessageUtils.mapMessage(t));
        });
        return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRole() throws ChatException {
        // dummy method
        Map<String, Object> retMap = new HashMap<String, Object>();

        retMap.put("role", userService.getRole());

        ResponseEntity<Map<String, Object>> retValue = new ResponseEntity<Map<String, Object>>(retMap, HttpStatus.OK);
        return retValue;
    }
}
