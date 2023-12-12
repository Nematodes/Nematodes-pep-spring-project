package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    /**
     * Creates a SocialMediaController that uses a specific AccountService and MessageService
     * 
     * @param accountService the AccountService to use
     * @param messageService the MessageService to use
     */
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping(value = "/register")
    /**
     * Attempts to add an account to the application's account database
     * 
     * If successful, sets the HTTP status to 200
     * 
     * Fails and sets the HTTP status to 409 if an account with an identical username already
     * exists in the account database
     * 
     * Fails and sets the HTTP status to 400 under the following conditions:
     * The account's username has a length of 0 characters
     * The account's password has a length of less than 4 characters
     * 
     * @param inputAccount the Account to store, without its account_id set
     * @return the same Account with its account_id set to an automatically-generated value
     */
    public Account addAccount(@RequestBody Account inputAccount) {
        return accountService.addAccount(inputAccount);
    }

    @PostMapping(value = "/login")
    /**
     * Returns an Account with matching credentials (username and password) if it exists in the
     * application's account database
     * 
     * If successful, sets the HTTP status to 200
     * 
     * Fails and sets the HTTP status to 401 if an account with matching credentials is not found
     * 
     * @param inputAccount the Account to check for the matching credentials of
     * @return the Account with matching credentials
     */
    public Account loginToAccount(@RequestBody Account inputAccount) {
        return accountService.loginToAccount(inputAccount);
    }

    @PostMapping(value = "/messages")
    /**
     * Attempts to add a message to the application's message database
     * 
     * If successful, sets the HTTP status to 200
     * 
     * Fails and sets the HTTP status to 400 under the following conditions:
     * The message is not between 1 and 255 characters (inclusive) long
     * The account ID associated with the message does not correspond to an existing account
     * 
     * @param inputMessage the Message to store, without its message_id set
     * @return the same Message with its message_id set to an automatically-generated value
     */
    public Message addMessage(@RequestBody Message inputMessage) {
        return messageService.addMessage(inputMessage);
    }

    @GetMapping(value = "/messages")
    /**
     * Gets all messages from the application's message database
     * 
     * If there are no messages in the database, then an empty list is returned.
     * 
     * Always sets the HTTP status to 200
     * 
     * @return a list containing every Message in the message database
     */
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping(value = "/messages/{message_id}")
    /**
     * Gets a message with a matching message_id from the application's message database
     * 
     * If there is no message with a matching message_id, then an empty response is returned.
     * 
     * Always sets the HTTP status to 200
     * 
     * @param message_id the message_id to search for
     * @return the Message with a matching message_id, or null if a matching message is not found
     */
    public Message getMessageById(@PathVariable("message_id") int message_id) {
        return messageService.getMessageById(message_id);
    }

    @DeleteMapping(value = "/messages/{message_id}")
    /**
     * Attempts to delete a message with a matching message_id from the application's message database
     * 
     * If a message is found and successfully deleted, then the number of rows updated (1) is returned.
     * Otherwise, an empty response is returned.
     * 
     * Always sets the HTTP status to 200
     * 
     * @param message_id the message_id of the message to delete
     * @return 1 if the message is found and deleted, or null if no message is found and deleted
     */
    public Integer deleteMessageById(@PathVariable("message_id") int message_id) {
        return messageService.deleteMessageById(message_id);
    }
}
