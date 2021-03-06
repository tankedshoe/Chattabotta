package chat.model;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The source of Chatbot's conversing ability.
 * @author Dane Heaps
 * @version 21/11/17 1.2
 */
public class Chatbot
{
	//Data members
	private List<Movie> movieList;
	private List<String> shoppingList;
	private List<String> cuteAnimalMemes;
	private String [] verbs;
	private String [] topics;
	private String [] followUps;
	private String [] questions;
	private String username;
	private String content;
	private String intro;
	private LocalTime currentTime;
	
	//Initializes data members, and calls helper methods.
	public Chatbot(String username)
	{
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.currentTime = null;
		this.questions = new String [10];
		this.username = username;
		this.content = "";
		this.intro = "";
		this.currentTime = LocalTime.now();
		this.topics = new String [7];
		this.verbs = new String [4];
		this.followUps = new String [5];
		
		buildVerbs();
		buildShoppingList();
		buildQuestions();
		buildCuteAnimals();
		buildMovieList();
		processConversation("");
		getCurrentTime();
		buildTopics();
		buildFollowups();
	}
	
	//Builds the topics
	private void buildTopics()
	{
		topics[0] = "animals";
		topics[1] = "food";
		topics[2] = "trees";
		topics[3] = "weather";
		topics[4] = "games";
		topics[5] = "sports";
		topics[6] = "miscellaneous";
	}
	
	//Builds followups
	private void buildFollowups()
	{
		followUps[0] = "You are trash";
		followUps[1] = "I hate you";
		followUps[2] = "I'm tired of your junk";
	}
	
	//Builds verbs
	private void buildVerbs()
	{
		verbs[0] = "like";
		verbs[1] = "dislike";
		verbs[2] = "ambivalent about";
		verbs[3] = "am thinking about";
	}
	
	//Builds questions
	private void buildQuestions()
	{
		questions[0] = "What is your name?";
		questions[1] = "What do you like to do?";
		questions[2] = "Are you an avid movie-goer?";
		questions[3] = "Do you like to go shopping?";
		questions[4] = "Do you like cute animals?";
		questions[5] = "Do you like memes?";
		questions[6] = "What's your favorite color?";
		questions[7] = "Here's some trivia: Who is the president of your country?";
		questions[8] = "Here's some trivia: What is a ligerian?";
		questions[9] = "What's your favorite fruit?";
	}

	//Builds the movie list
	private void buildMovieList()
	{
		movieList.add(new Movie(""));
		movieList.add(new Movie("Spiderman"));
		movieList.add(new Movie("Hidden Figures"));
		movieList.add(new Movie("Thor: Ragnarok"));
		movieList.add(new Movie("Titanic"));
		movieList.add(new Movie("Gladiator"));
	}
	
	//Builds the shopping list
	private void buildShoppingList()
	{
		shoppingList.add("snacks");
		shoppingList.add("veggies");
		shoppingList.add("protein");
		shoppingList.add("fruits");
		shoppingList.add("grains");
	}
	
	//Builds the cute animal list
	private void buildCuteAnimals()
	{
		cuteAnimalMemes.add("otter");
		cuteAnimalMemes.add("otter");
		cuteAnimalMemes.add("FLOOFER");
		cuteAnimalMemes.add("FLOOFER");
		cuteAnimalMemes.add("kittie");
		cuteAnimalMemes.add("kittie");
		cuteAnimalMemes.add("pupper");
	}
	
	/**
	 * The vehicle for conversation between the user and the chatbot.
	 * @param input This is for the user's input, used in building the chatbot's response.
	 * @return Returns the response that the program will spit out.
	 */
	public String processConversation(String input)
	{
		String chatbotResponse = "";
		chatbotResponse += currentTime.getHour() + ":" + currentTime.getMinute() + "\n";
		chatbotResponse += "You said: " + "\n" + input + "\n";
		
		chatbotResponse += buildChatbotResponse();
		
		return chatbotResponse;
	}
	
	//Creates the chatbot's response
	private String buildChatbotResponse()
	{
		String response = "I";
		int random = (int) (Math.random() * verbs.length);
		
		response += verbs[random];
		
		random = (int) (Math.random() * topics.length);
		response += " " + topics[random] + ".\n";
		
		random = (int) (Math.random() * questions.length);
		response += questions[random];
		
		random = (int) (Math.random() * 2);
		if (random % 2 == 0)
		{
			random = (int) (Math.random() * movieList.size());
			response += movieList.get(random).getTitle() + "is a great movie!";
		}
		
		int followup = (int) (Math.random() * 5);
		
		switch (followup)
		{
		case 0:
			response += followUps[0] + "\n";
			break;
		case 3:
			response += followUps[1] + "\n";
		case 1:
			response += followUps[2] + "\n";
			break;
		default:
			response += followUps[4] + "\n";
			response += followUps[3] + "\n";
			break;
		}
		
		return response;
	}
	
	//Checks for adequate length.
	public boolean lengthChecker(String input)
	{
		boolean validLength = false;
		
		if (input != null && input.length() > 2)
		{
			validLength = true;
		}
		
		return validLength;
	}
	
	//Checks for adequate tags.
	public boolean htmlTagChecker(String input)
	{
		boolean containsHTML = false;
		
		if(input == null || !input.contains("<"))
		{
			return containsHTML;
		}
		
		int firstOpen = input.indexOf("<");
		int firstClose = input.indexOf(">", firstOpen);
		int secondOpen = 9;
		int secondClose = -9;
		String tagText = "";
		
		//Check bad tags
		if(input.contains("<>") || input.indexOf("< >") > -1)
		{
			containsHTML = false;
		}
		
		//Check singleton
		if(input.toUpperCase().contains("<P>") || input.toLowerCase().contains("<br>"))
		{
			containsHTML = true;
		}
		
		//Check others
		else if(firstClose > firstOpen)
		{
			//Others
			tagText = input.substring(firstOpen + 1, firstClose).toLowerCase();
			secondOpen = input.toLowerCase().indexOf("</" + tagText, firstClose);
		}
		
		return containsHTML;
	}
	
	//Checks for a valid username.
	public boolean userNameChecker(String input)
	{
		if (input == null || !input.startsWith("@@"))
		{
			return false;
		}
		
		if (input.startsWith("@"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Checks for existence of content.
	public boolean contentChecker(String contentCheck)
	{
		if (contentCheck == null)
		{
			return false;
		}
		
		return true;
	}
	
	//Checks for an adequate cuteAnimalMeme list.
	public boolean cuteAnimalMemeChecker(String input)
	{
		int index = 0;
		
		for (index = 0; index < cuteAnimalMemes.size(); index +=1)
		{
			if (input.contains("pupper") || input.contains("otter") || input.contains("kittie"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	//Checks for an adequate shoppingList.
	public boolean shoppingListChecker(String shoppingItem)
	{
		int index = 0;
		
  	for (index = 0; index < shoppingList.size(); index+=1)		
  		{
			if (shoppingItem.contains("protein") || shoppingItem.contains("veggies") || shoppingItem.contains("snacks"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return true;
	}
	
	//Checks for and adequate movieTitle list.
	public boolean movieTitleChecker(String title)
	{
		int index = 0;
		
		for (index = 0; index < movieList.size(); index+=1)
		{
			if (title.contains("Spiderman") || title.contains("Hidden Figures"))
			{
				return true;
			}
			else
				
			{
				return false;
			}
		}
		return true;
	}
	
	//Checks for a valid movie genre.
	public boolean movieGenreChecker(String genre)
	{
		return false;
	}

	//Checks for a valid quit.
	public boolean quitChecker(String exitString)
	{
		if (exitString == null)
		{
			return false;
		}
		
		else if (exitString.equalsIgnoreCase("quit"))
		{
			return true;
		}
		return false;
	}

	//Checks for keyboard mashing.
	public boolean keyboardMashChecker(String sample)
	{
		return true;
	}
	
	public boolean toStringChecker(String input)
	{
		if (input.contains("@"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//Getters.
	public List<Movie> getMovieList()
	{
		return movieList;
	}
	
	public List<String> getShoppingList()
	{
		return shoppingList;
	}
	
	public List<String> getCuteAnimalMemes()
	{
		return cuteAnimalMemes;
	}

	public String [] getQuestions()
	{
		return questions;
	}
	
	public String[] getVerbs()
	{
		return verbs;
	}

	public String[] getTopics()
	{
		return topics;
	}

	public String[] getFollowUps()
	{
		return followUps;
	}

	public String getUsername()
	{
		return username;
	}
	
	public String getContent()
	{
		return content;
	}

	public String getIntro()
	{
		return null;
	}
	
	public LocalTime getCurrentTime()
	{
		return currentTime;
	}
	
	//Setters.
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}
