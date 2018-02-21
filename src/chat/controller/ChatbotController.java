
package chat.controller;

import chat.view.*;
import chat.model.*;

/**
 * The controller for the Chatbot.
 * @author Dane Heaps
 * @version 21/11/17 1.2
 */
public class ChatbotController
{
	private PopupDisplay display;
	private Chatbot chatbot;
	private ChatFrame appFrame;
	
	//Initialization for the controller.
	public ChatbotController()
	{
		chatbot = new Chatbot("Dane Heaps");
		//View initialized after Model
		display = new PopupDisplay();
		appFrame = new ChatFrame(this);
	}
	
	//Starts the app.
	public void start()
	{
		display.displayText("Welcome to Chattabotta!");
	}
	
	//Returns the chatbot's response
	public String interactWithChatbot(String input)
	{
		String chatbotSays = "";
		
		if (chatbot.quitChecker(input))
		{
			close();
		}
		
		chatbotSays += chatbot.processConversation(input);
		
		return chatbotSays;
	}
	
	//Uses the checkers when a button is pressed.
	public String useCheckers(String text)
	{
		String response = "";
		
		if(chatbot.contentChecker(text))
		{
			response += "This text matches the special content\n";
		}
		if(chatbot.cuteAnimalMemeChecker(text))
		{
			response += "This matches the animal memes\n";
		}
		if(chatbot.htmlTagChecker(text))
		{
			response += "This matches the html tga needed\n";
		}
		if(chatbot.keyboardMashChecker(text))
		{
			response += "This is not a keyboard mash\n";
		}
		if(chatbot.userNameChecker(text))
		{
			response += "This is an adequate username\n";
		}
		if(chatbot.movieGenreChecker(text))
		{
			response += "This is a valid movie genre\n";
		}
		if(chatbot.movieTitleChecker(text))
		{
			response += "This is a valid movie title\n";
		}
		if(chatbot.shoppingListChecker(text))
		{
			response += "This is a valid shopping list\n";
		}
		//continue will all checkers except length and quit checker
		
		return response;
	}
	
	//The quit method.
	private void close()
	{
		display.displayText("Goodbye!");
		System.exit(0);
	}
	
	//
	private String popupChat(String chat)
	{
		String chatbotSays = "";
		
		chatbotSays += chatbot.processConversation(chat);
		
		return chatbotSays;
	}
	
	public void handleErrors(Exception error)
	{
		display.displayText(error.getMessage());
	}
	
	public Chatbot getChatbot()
	{
		return chatbot;
	}
	
	public PopupDisplay getDisplay()
	{
		return display;
	}
	
	public ChatFrame getChatFrame()
	{
		return appFrame;
	}
	
}

