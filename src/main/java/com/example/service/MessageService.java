package com.example.service;

import com.example.entity.*;
import com.example.exception.*;
import com.example.repository.*;
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
}
