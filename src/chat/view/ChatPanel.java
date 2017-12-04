package chat.view;

import chat.controller.ChatbotController;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
//Need import for ScrollPane
import javax.swing.JScrollPane;

/**
 * The JPanel subclass for the chatbot project.
 * @author Dane Heaps
 * @version 21/11/17 1.2
 */
public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JTextArea responseArea;
	private JTextField input;
	private JButton submit;
	private JButton checker;
	private SpringLayout layout;
	private PopupDisplay display;
	private JLabel label;
	//Need a data member for scrollPane
	private JScrollPane chatScrollPane;
	
	public ChatPanel(ChatbotController appController) 
	{
		super();
		this.appController = appController;
		
		//Initialize GUI data members
		this.responseArea = new JTextArea(10, 25);
		this.input = new JTextField(20);
		this.submit = new JButton("submit");
		this.layout = new SpringLayout();
		this.checker = new JButton("quit");
		this.display = new PopupDisplay();
		this.label = new JLabel("Type to chat with the chatbot");
		this.chatScrollPane = new JScrollPane();
		
		setupPanel();
		setupLayout();
		setupListeners();
		setupScrollPane();
	}
	
	/**
	 * Sets all the aspects of the panel, such as putting in buttons and setting the background color.
	 */
	private void setupPanel()
	{
		this.setBackground(Color.CYAN);
		this.setLayout(layout);
		this.add(submit);
		this.add(input);
		this.add(checker);
		this.add(label);
		this.add(chatScrollPane);
		chatScrollPane.setEnabled(false);
		responseArea.setEditable(false);
	}
	
	
	/** 
	 * The dumping ground for all constraints in the GUI.
	 */
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, input, 0, SpringLayout.NORTH, submit);
		layout.putConstraint(SpringLayout.WEST, input, 0, SpringLayout.WEST, chatScrollPane);
		layout.putConstraint(SpringLayout.SOUTH, submit, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, submit, 0, SpringLayout.EAST, chatScrollPane);
		layout.putConstraint(SpringLayout.NORTH, chatScrollPane, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, chatScrollPane, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, chatScrollPane, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, checker, 6, SpringLayout.SOUTH, chatScrollPane);
		layout.putConstraint(SpringLayout.WEST, checker, 0, SpringLayout.WEST, submit);
		layout.putConstraint(SpringLayout.EAST, checker, 0, SpringLayout.EAST, submit);
		layout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, input);
		layout.putConstraint(SpringLayout.SOUTH, label, -6, SpringLayout.NORTH, input);
	}
	
	/**
	 * Where listeners are attached to GUI elements.
	 */
	private void setupListeners()
	{
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click)
			{
				String userText = input.getText();
				String displayText = appController.interactWithChatbot(userText);
				responseArea.append(displayText);
				input.setText("");
			}
		});
		
		checker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click)
			{
				String userText = input.getText();
				String displayText = appController.useCheckers(userText);
				responseArea.append(displayText);
				input.setText("");
			}
		});
	}
	
	private void setupScrollPane()
	{
		chatScrollPane.setViewportView(responseArea);
		chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
}
