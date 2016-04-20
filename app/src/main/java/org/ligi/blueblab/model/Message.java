package org.ligi.blueblab.model;

public class Message {

    public final String userID;
    public final String message;

    public Message(final String userID, final String message) {
        this.userID = userID;
        this.message = message;
    }
}
