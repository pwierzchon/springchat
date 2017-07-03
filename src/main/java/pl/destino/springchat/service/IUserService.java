/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.springchat.service;

import pl.destino.springchat.entity.ChatUser;
import pl.destino.springchat.util.ChatException;
import java.util.List;

/**
 *
 * @author Destino
 */
public interface IUserService {

    public ChatUser getCurrentUser();

    public List<String> getRole() throws ChatException;

    public ChatUser getUserByUsername(String userName) throws ChatException;
}
