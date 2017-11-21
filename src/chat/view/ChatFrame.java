package chat.view;

import chat.controller.ChatbotController;
import javax.swing.JFrame;

/**
 * The general frame for the GUI.
 * @author Dane Heaps
 * @version 21/11/17 1.2
 */
public class ChatFrame extends JFrame
{
	private ChatbotController appController;
	private ChatPanel appPanel;
	
	public ChatFrame(ChatbotController appController)
	{
		super();
		this.appController = appController;
		appPanel = new ChatPanel(appController);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setSize(500, 500);
		this.setTitle("ChattaBotta");
		this.setContentPane(appPanel);
		this.setResizable(false);
		this.setVisible(true);
	}
}
