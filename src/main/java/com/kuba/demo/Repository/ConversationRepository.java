package com.kuba.demo.Repository;

import com.kuba.demo.Model.Comment;
import com.kuba.demo.Model.Conversation;
import com.kuba.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long>
{
    @Query("select c from Conversation c where c.user1=?1 or c.user2=?1")
    List<Conversation> getIfInvolvesUser(User user);

    @Query("select c from Conversation c where c.user1=?1 and c.user2=?2 or c.user1=?2 and c.user2=?1")
    Conversation getIfInvolvesTwoUsers(User user1, User user2);
}