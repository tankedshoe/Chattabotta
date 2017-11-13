package chat.controller;

import chat.model.Chatbot;
import chat.view.PopupDisplay;

public class ChatbotRunner
{
	public static void main(String [] args)
	{
		ChatbotController myApp = new ChatbotController();
		myApp.start();
	}
}
