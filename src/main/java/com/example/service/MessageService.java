package com.example.service;

import com.example.entity.*;
import com.example.exception.*;
import com.example.repository.*;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService {
    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    /**
     * Creates a MessageService that uses a specific MessageRepository and AccountRepository
     * 
     * @param messageRepository the MessageRepository to use
     * @param accountRepository the AccountRepository to use
     */
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Adds a message to the application's message database
     * 
     * This will fail if the message length is not between 1 and 255 characters (inclusive)
     * or if the account ID associated with the post does not correspond to an existing account.
     * 
     * @param message the Message to store, without its message_id set
     * @return the same Message with its message_id set to an automatically-generated value
     */
    public Message addMessage(Message message) {
        int Message_textLength = message.getMessage_text().length();

        if (Message_textLength == 0) {
            throw new InvalidMessageLengthException("Messages cannot be empty!");
        }

        if (Message_textLength > 255) {
            throw new InvalidMessageLengthException("Messages cannot contain more than 255 characters!");
        }

        Optional<Account> potentialAccount = accountRepository.findById(message.getPosted_by());

        // If the message was not posted by an existing account
        if (!potentialAccount.isPresent()) {
            throw new NonexistentMessageSenderException();
        }

        return messageRepository.save(message);
    }

    /**
     * Attempts to delete a message with a matching message_id from the application's message database
     * 
     * If a message is found and successfully deleted, then the number of rows updated (1) is returned.
     * Otherwise, null is returned.
     * 
     * @param message_id the message_id of the message to delete
     * @return 1 if the message is found and deleted, or null if no message is found and deleted
     */
    public Integer deleteMessageById(int message_id) {
        if (messageRepository.findMessageByMessage_id(message_id) != null) {
            messageRepository.deleteById(message_id);
            return 1;
        }
        else {
            return null;
        }
    }

    /**
     * Gets all messages from the application's message database
     * 
     * If there are no messages in the database, then an empty list is returned.
     * 
     * @return a list containing every Message in the message database
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Gets all messages written by a user with a matching account_id from the
     * application's message database
     * 
     * If the user has written no messages, then an empty list is returned.
     * 
     * If a user with a matching account_id does not exist, then undefined
     * behavior occurs.
     * 
     * @param account_id The account_id of the account to get all messages from
     * @return a list containing every Message written by the user with a matching account_id
     */
    public List<Message> getAllMessagesByUser(int account_id) {
        return messageRepository.findAllMessagesByAccount_id(account_id);
    }

    /**
     * Gets a message with a matching message_id from the application's message database
     * 
     * If there is no message with a matching message_id, then an empty response is returned.
     * 
     * @param message_id the message_id to search for
     * @return the Message with a matching message_id, or null if a matching message is not found
     */
    public Message getMessageById(int message_id) {
        return messageRepository.findMessageByMessage_id(message_id);
    }

    /**
     * Sets the MessageRepository for this MessageService
     * 
     * @param accountRepository the MessageRepository to set
     */
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Sets the AccountRepository for this MessageService
     * 
     * @param accountRepository the AccountRepository to set
     */
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Attempts to update the text of a message with a matching message_id in the application's
     * message database
     * 
     * If successful, returns the number of rows updated (1)
     * 
     * Fails under the following conditions:
     * A message with a matching message_id is not found
     * The supplied updated text is not between 1 and 255 (inclusive) characters long
     * 
     * @param message_id the message_id of the message to update
     * @param newMessage_text the text to update the message with
     * @return 1 if the message is found and updated, or null if this fails for any reason
     */
    public Integer updateMessageById(int message_id, String newMessage_text) {
        int newMessage_textLength = newMessage_text.length();

        if (newMessage_textLength == 0) {
            throw new InvalidMessageLengthException("Messages cannot be empty!");
        }

        if (newMessage_textLength > 255) {
            throw new InvalidMessageLengthException("Messages cannot contain more than 255 characters!");
        }

        if (messageRepository.findMessageByMessage_id(message_id) == null) {
            throw new NonexistentMessageException();
        }

        messageRepository.updateMessageByMessage_id(message_id, newMessage_text);

        return 1;
    }
}
