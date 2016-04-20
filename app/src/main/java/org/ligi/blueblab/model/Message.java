package org.ligi.blueblab.model;

public class Message {

    public final User user;
    public final String message;

    public Message(final User user, final String message) {
        this.user = user;
        this.message = message;
    }
}
