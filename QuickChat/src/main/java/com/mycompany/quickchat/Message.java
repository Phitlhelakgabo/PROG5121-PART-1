package com.mycompany.quickchat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Message class handles creation, validation, hashing, and storage
 * of a single chat message.
 */
public class Message {

    // ---------- Instance variables ----------
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // ---------- Static counters and stores ----------
    private static int totalMessages = 0;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();

    // ---------- Constructor ----------
    public Message(String recipient, String messageText, int messageNumber) {
        this.messageID     = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient     = recipient;
        this.messageText   = messageText;
        this.messageHash   = createMessageHash();
    }

    // ---------- Getters ----------
    public String getMessageID()     { return messageID; }
    public int    getMessageNumber() { return messageNumber; }
    public String getRecipient()     { return recipient; }
    public String getMessageText()   { return messageText; }
    public String getMessageHash()   { return messageHash; }

    // ---------- Static getters for lists ----------
    public static ArrayList<Message> getSentMessages()        { return sentMessages; }
    public static ArrayList<Message> getDisregardedMessages() { return disregardedMessages; }
    public static ArrayList<Message> getStoredMessages()      { return storedMessages; }

    // ============================================================
    // METHOD 1 — checkMessageID
    // ============================================================
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // ============================================================
    // METHOD 2 — checkRecipientCell
    // ============================================================
    public String checkRecipientCell() {
        if (recipient == null || recipient.length() > 12) {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "an international code. Please correct the number and try again.";
        }
        if (!recipient.startsWith("+")) {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "an international code. Please correct the number and try again.";
        }
        return "Cell phone number successfully captured.";
    }

    // ============================================================
    // METHOD 3 — createMessageHash
    // ============================================================
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord  = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // ============================================================
    // METHOD 4 — generateMessageID (helper, 10-digit random number)
    // ============================================================
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1_000_000_000L + (long)(rand.nextDouble() * 9_000_000_000L);
        return String.valueOf(id);
    }

    // ============================================================
    // METHOD 5 — SentMessage
    // ============================================================
    public String SentMessage(String action) {
        switch (action) {
            case "1":
                sentMessages.add(this);
                totalMessages++;
                return "Message successfully sent.";
            case "2":
                storedMessages.add(this);
                storeMessage();  // write to JSON
                return "Message successfully stored.";
            case "3":
                disregardedMessages.add(this);
                return "Message successfully disregarded.";
            default:
                return "Invalid action. Please choose 1, 2, or 3.";
        }
    }

    // ============================================================
    // METHOD 6 — printMessages
    // ============================================================
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append("Message ID:  ").append(m.getMessageID()).append("\n");
            sb.append("Hash:        ").append(m.getMessageHash()).append("\n");
            sb.append("Recipient:   ").append(m.getRecipient()).append("\n");
            sb.append("Message:     ").append(m.getMessageText()).append("\n");
            sb.append("-------------------------------------\n");
        }
        return sb.toString();
    }

    // ============================================================
    // METHOD 7 — returnTotalMessages
    // ============================================================
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // ============================================================
    // METHOD 8 — storeMessage (Writes to messages.json)
    // ============================================================
    public void storeMessage() {
        JSONArray jsonArray = new JSONArray();
        for (Message m : storedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("messageID",   m.getMessageID());
            obj.put("messageHash", m.getMessageHash());
            obj.put("recipient",   m.getRecipient());
            obj.put("message",     m.getMessageText());
            jsonArray.put(obj);
        }
        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(jsonArray.toString(2));
            file.flush();
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }
}
