/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.springchat.service;

import pl.destino.springchat.entity.ChatUser;
import pl.destino.springchat.repo.UserRepository;
import pl.destino.springchat.util.ChatException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Destino
 */
@Component
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public ChatUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String prin = (String) auth.getPrincipal();
        ChatUser userByUsername = userRepo.findTopByUserName(prin);

        return userByUsername;

    }
    
    public ChatUser getUserByUsername(String userName) throws ChatException {
        ChatUser user = userRepo.findTopByUserName(userName);
        return user;
    }

    @Override
    public List<String> getRole() throws ChatException {
        Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator<? extends GrantedAuthority> it = roles.iterator();
        List<String> strRoles = new ArrayList<String>();

        while (it.hasNext()) {
            strRoles.add(it.next().getAuthority());
        }

        return strRoles;
    }

}
