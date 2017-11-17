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

public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JTextArea responseArea;
	private JTextField input;
	private JButton submit;
	private SpringLayout layout;
	
	public ChatPanel(ChatbotController appController) 
	{
		super();
		this.appController = appController;
		
		//Initialize GUI data members
		this.responseArea = new JTextArea(10, 25);
		this.input = new JTextField(20);
		this.submit = new JButton("submit");
		this.layout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();
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
		this.add(responseArea);
		responseArea.setEnabled(false);
		responseArea.setEditable(false);
	}
	
	
	/** 
	 * The dumping ground for all constraints in the GUI.
	 */
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, input, 0, SpringLayout.NORTH, submit);
		layout.putConstraint(SpringLayout.WEST, input, 0, SpringLayout.WEST, responseArea);
		layout.putConstraint(SpringLayout.SOUTH, submit, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, submit, 0, SpringLayout.EAST, responseArea);
		layout.putConstraint(SpringLayout.NORTH, responseArea, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, responseArea, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, responseArea, -25, SpringLayout.EAST, this);
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
	}
}
