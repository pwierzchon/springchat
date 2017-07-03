package pl.destino.springchat.repo;

import java.util.List;

import org.springframework.data.repository.Repository;

import pl.destino.springchat.entity.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface MessageRepository extends CrudRepository<ChatMessage, Long> {

    @Override
    ChatMessage save(ChatMessage libraryBook);
}
