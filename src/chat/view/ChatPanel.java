package chat.view;

import chat.controller.ChatbotController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


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
	
	private JButton chatButton;
	private JButton searchButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton tweetButton;
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
		
		chatButton = new JButton("chat", new ImageIcon(getClass().getResource("/chat/view/images/chatIcon.png")));
		searchButton = new JButton("search", new ImageIcon(getClass().getResource("/chat/view/images/searchIcon.png")));
		layout.putConstraint(SpringLayout.EAST, searchButton, -120, SpringLayout.EAST, this);
		tweetButton = new JButton("tweet", new ImageIcon(getClass().getResource("/chat/view/images/tweetIcon.png")));
		layout.putConstraint(SpringLayout.NORTH, tweetButton, 2, SpringLayout.SOUTH, chatButton);
		layout.putConstraint(SpringLayout.WEST, tweetButton, 10, SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.SOUTH, tweetButton, 128, SpringLayout.SOUTH, chatScrollPane);
		layout.putConstraint(SpringLayout.EAST, tweetButton, -297, SpringLayout.EAST, this);
		saveButton = new JButton("save", new ImageIcon(getClass().getResource("/chat/view/images/saveIcon.png")));
		loadButton = new JButton("load", new ImageIcon(getClass().getResource("/chat/view/images/loadIcon.png")));
		
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
		this.setMinimumSize(new Dimension(800, 600));
		this.setBackground(Color.CYAN);
		this.setLayout(layout);
		this.add(submit);
		this.add(input);
		this.add(checker);
		this.add(label);
		this.add(chatScrollPane);
		this.add(chatButton);
		this.add(searchButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(tweetButton);
		chatScrollPane.setEnabled(false);
		responseArea.setEditable(false);
	}
	
	
	/** 
	 * The dumping ground for all constraints in the GUI.
	 */
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, chatScrollPane, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, chatScrollPane, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, chatScrollPane, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, chatButton, 0, SpringLayout.NORTH, searchButton);
		layout.putConstraint(SpringLayout.NORTH, searchButton, 6, SpringLayout.SOUTH, chatScrollPane);
		layout.putConstraint(SpringLayout.NORTH, label, 29, SpringLayout.SOUTH, saveButton);
		layout.putConstraint(SpringLayout.NORTH, saveButton, 6, SpringLayout.SOUTH, chatScrollPane);
		layout.putConstraint(SpringLayout.WEST, saveButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, chatButton, 89, SpringLayout.EAST, loadButton);
		layout.putConstraint(SpringLayout.NORTH, loadButton, 6, SpringLayout.SOUTH, chatScrollPane);
		layout.putConstraint(SpringLayout.WEST, loadButton, 6, SpringLayout.EAST, saveButton);
		layout.putConstraint(SpringLayout.SOUTH, loadButton, 0, SpringLayout.SOUTH, chatButton);
		layout.putConstraint(SpringLayout.EAST, loadButton, 94, SpringLayout.EAST, saveButton);
		layout.putConstraint(SpringLayout.NORTH, chatButton, 0, SpringLayout.NORTH, searchButton);
		layout.putConstraint(SpringLayout.NORTH, searchButton, 6, SpringLayout.SOUTH, chatScrollPane);
		layout.putConstraint(SpringLayout.WEST, checker, 282, SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, input, 14, SpringLayout.SOUTH, label);
		layout.putConstraint(SpringLayout.NORTH, checker, -5, SpringLayout.NORTH, label);
		layout.putConstraint(SpringLayout.WEST, label, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, label, -395, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, input, 0, SpringLayout.WEST, label);
		layout.putConstraint(SpringLayout.EAST, checker, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, submit, 0, SpringLayout.EAST, checker);
		layout.putConstraint(SpringLayout.NORTH, submit, 0, SpringLayout.NORTH, input);
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
		
		chatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		tweetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click)
			{
				appController.tweet(input.getText());
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click)
			{
				
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
