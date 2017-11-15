package chat.view;

import chat.controller.ChatbotController;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JButton;

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
		appController = new ChatbotController();
		responseArea = new JTextArea();
		input = new JTextField();
		submit = new JButton();
		layout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
}
