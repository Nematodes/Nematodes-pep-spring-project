package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    /*
     * A @Query is necessary because Spring cannot automatically parse queries that
     * use attributes with names containing underscores (Message_id in this case).
     * 
     * This method is necessary because MessageRepository.getById(Integer) generates
     * HTTP status 500 and returns empty (all null value) Message objects for unknown
     * reasons.
     */
    @Query("FROM Message WHERE message_id = :messageIdVar")
    /**
     * Finds and returns the Message with a matching message_id (if it exists)
     * 
     * @param message_id the message_id of the Message to find
     * @return the Message with a matching Message_id (if it exists)
     */
    public Message findMessageByMessage_id(@Param("messageIdVar") int message_id);

    @Modifying
    @Query("UPDATE Message SET message_text = :messageTextVar WHERE message_id = :messageIdVar")
    /**
     * Updates a message the Message with a matching message_id (if it exists)
     * with new text supplied by newMessage_text
     * 
     * @param message_id the message_id of the Message to update
     * @param newMessage_text the text to update the message with
     */
    public void updateMessageByMessage_id(@Param("messageIdVar") int message_id, @Param("messageTextVar") String newMessage_text);
}
