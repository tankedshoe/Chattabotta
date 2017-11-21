package chat.controller;

import chat.model.Chatbot;
import chat.view.PopupDisplay;

/**
 * Essentially what starts the Chatbot.
 * @author Dane Heaps
 * @version 21/11/17 1.2
 */
public class ChatbotRunner
{
	public static void main(String [] args)
	{
		ChatbotController myApp = new ChatbotController();
		myApp.start();
	}
}
