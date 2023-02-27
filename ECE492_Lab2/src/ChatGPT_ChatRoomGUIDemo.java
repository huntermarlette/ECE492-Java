

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatGPT_ChatRoomGUIDemo extends JFrame implements ActionListener {

    private JTextArea chatBox;
    private JTextField messageField;
    private JButton sendButton;

    public ChatGPT_ChatRoomGUIDemo() {
        setTitle("Chat Room");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatBox);
        messageField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);

        // Add components to layout
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(chatScrollPane, BorderLayout.CENTER);
        JPanel inputPane = new JPanel();
        inputPane.setLayout(new BorderLayout());
        inputPane.add(messageField, BorderLayout.CENTER);
        inputPane.add(sendButton, BorderLayout.EAST);
        contentPane.add(inputPane, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                chatBox.append("You: " + message + "\n");
                messageField.setText("");
            }
        }
    }

    public static void main(String[] args) {
    	ChatGPT_ChatRoomGUIDemo chatRoomGUI = new ChatGPT_ChatRoomGUIDemo();
    }
}