package com.example.quips.chat.repository;

import com.example.quips.chat.domain.model.Conversation;
import com.example.quips.authentication.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findByUser1AndUser2OrUser1AndUser2(User user1, User user2, User user2Alt, User user1Alt);

}
