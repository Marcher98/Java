package networkFinal;

import java.io.*;
// Defines the types of messages that the server will have to handle coming from the client
// These messages include 1- Listing Users in channel 2- Normal message handling and 3- Logging out from server
public class ChatMessage implements Serializable
{
    protected static final long serialVersionUID = 1112122200L;
    // WHOISIN - List Users in Channel
    // MESSAGE - Append Message to TextArea on Server/Client GUI/Console
    // LOGOUT - Leave Server and Remove Used Username
    static final int WHOISIN = 0, MESSAGE = 1, LOGOUT = 2;
    private int type;
    private String message;

    ChatMessage(int type, String message)
    {
        this.type = type;
        this.message = message;
    }

    int getType()
    {
        return type;
    }

    String getMessage()
    {
        return message;
    }
}

