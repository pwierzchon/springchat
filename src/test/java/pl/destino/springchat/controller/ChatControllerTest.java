/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.springchat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.destino.springchat.entity.ChatMessage;
import pl.destino.springchat.entity.ChatUser;
import pl.destino.springchat.service.IMessageService;
import pl.destino.springchat.service.IUserService;
import static org.hamcrest.Matchers.containsString;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 *
 * @author Destino
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ChatController.class)
@ContextConfiguration
@WebAppConfiguration
public class ChatControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    IMessageService msgService;

    @MockBean
    IUserService usrService;

    @Test
    public void contextLoads() {
    }

    @Test
    @WithMockUser
    public void requestTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void findallTest() throws Exception {
        ChatMessage msg = new ChatMessage(1L, "Test message", "21-07-2017", new ChatUser("testUser", "passwd"));
        List<ChatMessage> msgs = new ArrayList<>();
        msgs.add(msg);
        when(msgService.getMessages()).thenReturn(msgs);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/findall")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Test message")));

    }

    @Test
    @WithMockUser
    public void roleTest() throws Exception {
        List<String> msgs = new ArrayList<>();
        msgs.add("USER");
        when(usrService.getRole()).thenReturn(msgs);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/role")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("USER")));

    }



}
