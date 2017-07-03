package pl.destino.springchat.repo;

import java.util.List;

import org.springframework.data.repository.*;

import pl.destino.springchat.entity.ChatUser;

public interface UserRepository extends Repository<ChatUser, Long> {
	
	List<ChatUser> findAllByUserName(String userName);
	
	ChatUser findTopByUserName(String userName);
        
        ChatUser findByUserId(Long userId);

}
