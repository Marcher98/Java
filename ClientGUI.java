package networkFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    // Text label followed by the text field to hold username, and then to hold the user message
    private JLabel label;
    private JTextField tf;
    // Text fields for the IP and the port(localhost and 1500 as default)
    private JTextField tfServer, tfPort;
    // Buttons to login and assign a username, logout, and print all the users on the chat server
    private JButton login, logout, whoIsIn;
    // Text area for chat messages
    private JTextArea ta;
    private boolean connected;
    // the Client object
    private Client client;
    // the default port number
    private int defaultPort;
    private String defaultHost;

    // Constructor connection receiving a socket number
    ClientGUI(String host, int port)
    {
        super("Chat Client");
        defaultPort = port;
        defaultHost = host;

        // Top panel containing IP/Port bar, Username label and message field
        JPanel northPanel = new JPanel(new GridLayout(3,1));
        JPanel serverAndPort = new JPanel(new GridLayout(1,5, 1, 3));
        tfServer = new JTextField(host);
        tfPort = new JTextField("" + port);
        tfPort.setHorizontalAlignment(SwingConstants.RIGHT);

        serverAndPort.add(new JLabel("Server Address:  "));
        serverAndPort.add(tfServer);
        serverAndPort.add(new JLabel("Port Number:  "));
        serverAndPort.add(tfPort);
        serverAndPort.add(new JLabel(""));
        northPanel.add(serverAndPort);

        // Username label and default username(anon by default)
        label = new JLabel("Enter your username below", SwingConstants.CENTER);
        northPanel.add(label);
        tf = new JTextField("anon");
        tf.setBackground(Color.WHITE);
        northPanel.add(tf);
        add(northPanel, BorderLayout.NORTH);

        // The chat message container
        ta = new JTextArea("Welcome to the Chat room\n", 80, 80);
        JPanel centerPanel = new JPanel(new GridLayout(1,1));
        centerPanel.add(new JScrollPane(ta));
        ta.setEditable(false);
        add(centerPanel, BorderLayout.CENTER);

        // Login/Logout/Show Users buttons
        login = new JButton("Login");
        login.addActionListener(this);
        logout = new JButton("Logout");
        logout.addActionListener(this);
        logout.setEnabled(false);		// you have to login before being able to logout
        whoIsIn = new JButton("Who is in");
        whoIsIn.addActionListener(this);
        whoIsIn.setEnabled(false);		// you have to login before being able to Who is in

        JPanel southPanel = new JPanel();
        southPanel.add(login);
        southPanel.add(logout);
        southPanel.add(whoIsIn);
        add(southPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        tf.requestFocus();

    }

    // Add message to chat window
    void append(String str)
    {
        ta.append(str);
        ta.setCaretPosition(ta.getText().length() - 1);
    }
    // Failsafe for if connection fails, Resets values
    void connectionFailed()
    {
        login.setEnabled(true);
        logout.setEnabled(false);
        whoIsIn.setEnabled(false);
        label.setText("Enter your username below");
        tf.setText("Anonymous");
        // reset port number and host name as a construction time
        tfPort.setText("" + defaultPort);
        tfServer.setText(defaultHost);
        // let the user change them
        tfServer.setEditable(false);
        tfPort.setEditable(false);
        // don't react to a <CR> after the username
        tf.removeActionListener(this);
        connected = false;
    }

    //Action/Event handlers
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();
        if(o == logout)
        {
            client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
            return;
        }
        if(o == whoIsIn)
        {
            client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));
            return;
        }
        if(connected)
        {
            client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, tf.getText()));
            tf.setText("");
            return;
        }
        if(o == login)
        {
            String username = tf.getText().trim();
            if(username.length() == 0)
                return;
            String server = tfServer.getText().trim();
            if(server.length() == 0)
                return;
            String portNumber = tfPort.getText().trim();
            if(portNumber.length() == 0)
                return;
            int port = 0;
            try {
                port = Integer.parseInt(portNumber);
            }
            catch(Exception en)
            {
                return;
            }

            // Makes client object with GUI
            client = new Client(server, port, username, this);
            if(!client.start())
                return;
            tf.setText("");
            label.setText("Enter your message below");
            connected = true;

            login.setEnabled(false);

            logout.setEnabled(true);
            whoIsIn.setEnabled(true);

            tfServer.setEditable(false);
            tfPort.setEditable(false);

            tf.addActionListener(this);
        }

    }

    // Create Client GUI with default values
    public static void main(String[] args)
    {
        new ClientGUI("localhost", 1500);
    }

}

