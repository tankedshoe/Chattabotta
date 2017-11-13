package chat.model;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

public class Chatbot
{
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
	
	private void buildFollowups()
	{
		
	}
	
	private void buildVerbs()
	{
		verbs[0] = "like";
		verbs[1] = "dislike";
		verbs[2] = "ambivalent about";
		verbs[3] = "am thinking about";
	}
	
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

	private void buildMovieList()
	{
		movieList.add(new Movie(""));
		movieList.add(new Movie("Spiderman"));
		movieList.add(new Movie("Hidden Figures"));
		movieList.add(new Movie("Thor: Ragnarok"));
		movieList.add(new Movie("Titanic"));
		movieList.add(new Movie("Gladiator"));
	}
	
	private void buildShoppingList()
	{
		shoppingList.add("snacks");
		shoppingList.add("veggies");
		shoppingList.add("protein");
		shoppingList.add("fruits");
		shoppingList.add("grains");
	}
	
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
		chatbotResponse += "You said: " + "\n" + input + "\n";
		chatbotResponse += buildChatbotResponse();
		
		return chatbotResponse;
	}
	
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
		
		return response;
	}
	
	public boolean lengthChecker(String input)
	{
		boolean validLength = false;
		
		if (input != null && input.length() > 2)
		{
			validLength = true;
		}
		
		return validLength;
	}
	
	public boolean htmlTagChecker(String input)
	{
		return false;
	}
	
	public boolean userNameChecker(String input)
	{
		if (input.contains("@") || !input.equals(null) || !input.contains("") || !input.contains(".com") || !input.contains("@@"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean contentChecker(String contentCheck)
	{
		return false;
	}
	
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
	
	public boolean movieGenreChecker(String genre)
	{
		return false;
	}

	public boolean quitChecker(String exitString)
	{
		if (exitString.equalsIgnoreCase("quit"))
		{
			return true;
		}
		return false;
	}

	public boolean keyboardMashChecker(String sample)
	{
		return true;
	}
	
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
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}
