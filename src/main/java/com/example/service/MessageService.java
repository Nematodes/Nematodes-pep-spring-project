package com.example.service;

import com.example.entity.*;
import com.example.exception.*;
import com.example.repository.*;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class MessageService {
    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    @Autowired // Autowiring is necessary since there are multiple repositories to set
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired // Autowiring is necessary since there are multiple repositories to set
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

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
        if (!potentialAccount.isPresent())
        {
            throw new NonexistentMessageSenderException();
        }

        return messageRepository.save(message);
    }
}
