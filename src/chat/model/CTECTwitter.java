package chat.model;

import chat.controller.*;

import twitter4j.*;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

public class CTECTwitter
{
	private ChatbotController appController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;
	private HashMap<String, Integer> wordsAndCount;
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.chatbotTwitter = TwitterFactory.getSingleton();
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.totalWordCount = 0;
		this.wordsAndCount = new HashMap<String, Integer>();
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			chatbotTwitter.updateStatus(textToTweet + " @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			appController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			appController.handleErrors(otherError);
		}
	}
	
	public String getMostCommonWord(String username)
	{
		String mostCommon = "";
		
		collectTweets(username);
		turnStatusesToWords();
		totalWordCount = tweetedWords.size();
		String [] boring = createIgnoredWordArray();
		trimTheBoringWords(boring);
		removeBlanks();
		generateWordCount();
		
		String mostCommonWord = topOne.keys().nextElement();
		maxWord = topOne.get(mostCommonWord);
		mostCommon = "The most common word in " + username + "'s " + searchedTweets.size() + " tweets is " + mostCommonWord + ", and it was used " + maxWord + " times.\nThis is " + (DecimalFormat.getPercentInstance().format(((double) maxWord)/totalWordCount) + " of total words: " + totalWordCount + " and is " + (DecimalFormat.getPercentInstance().format(((double) maxword)/wordsAndCount.size()) + " of the unique words: " + wordsAndCount.size();
		
		mostCommon += "\n\n" + sortedWords();
		
		return mostCommon;
	}
	
	private ArrayList<Map.Entry<String, Integer>> sortHashMap()
	{
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(wordsAndCount.entrySet());
		entries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		
		return entries;
	}
	
	private void trimTheBoringWords(String [] boringWords)
	{
		for(int index = tweetedWords.size() - 1; index >= 0; index --)
		{
			for( int boringIndex = 0; boringIndex < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equals(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private void removeBlanks()
	{
		for (int index = tweetedWords.size() - 1; index >=0; index--)
		{
			if (tweetedWords.get(index).trim().length() == 0)
			{
				tweetedWords.remove(index);
			}
		}
	}
	
	private void collectTweets(String username)
	{
		searchedTweets.clear();
		tweetedWords.clear();
		
		Paging statusPage = new Paging(1, 100);
		int page = 1;
		long lastID = Long.MAX_VALUE;
		
		while(page <= 10)
		{
			statusPage.setPage(page);
			try
			{
				ResponseList<Status> listedTweets = chatbotTwitter.getUserTimeline(username, statusPage);
				for(Status current : listedTweets)
				{
					if(current.getId() < lastID)
					{
						searchedTweets.add(current);
						lastID = current.getId();
					}
				}
			}
			catch(TwitterException searchTweetError)
			{
				appController.handleErrors(searchTweetError);
			}
			page++;
		}
	}
	
	private void turnStatusesToWords()
	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText();
			String [] tweetWords = tweetText.split(" ");
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(removePunctuation(tweetWords[index]).trim());
			}
		}
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,'?!:;\"() {}^[]<>-";
		tweetText = tweetText.replace("\n", "");
		String scrubbedString = "";
		for (int i = 0; i < currentString.length(); i++)
		{
			if (punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		return scrubbedString;
	}
	
	private String [] createIgnoredWordArray()
	{
		String [] boringWords;
		String fileText = IOController.loadFromFile(appController, "commonWords.txt");
		int wordCount = 0;
		
		Scanner wordScanner = new Scanner(fileText);
		
		while(wordScanner.hasNextLine())
		{
			wordScanner.nextLine();
			wordCount++;
		}
		
		boringWords = new String [wordCount];
		wordScanner.close();
		
		// Alternative file loading method.
		// Uses the InputStream class.
		// Notice the lack of try/catch.
		
		wordScanner = new Scanner(this.getClass().getResourceAsStream("data/commonWords,txt"));
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = wordScanner.nextLine();
		}
		
		wordScanner.close();
		return boringWords;
	}
	
	private void generateWordCount()
	{
		for (String word : tweetedWords)
		{
			if (!wordsAndCount.containsKey(word.toLowerCase()))
			{
				wordsAndCount.put(word.toLowerCase(), 1);
			}
			else
			{
				wordsAndCount.replace(word.toLowerCase(), wordsAndCount.get(word.toLowerCase()) + 1);
			}
		}
	}
	
	private String sortedWords()
	{
		String allWords = "";
		String [] words = (String []) wordsAndCount.keySet().toArray();
		ArrayList<String> wordList = new ArrayList<String>(wordsAndCount.keySet());
		
		for(int index = 0; index < wordsAndCount.size(); index++)
		{
			words[index] = wordList.get(index);
		}
		
		for(int index = 0; index < words.length - 1; index++)
		{
			int maxIndex = index;
			
			for (int inner = index + 1; inner < words.length; inner++)
			{
				if (words[inner].compareTo(words[maxIndex]) > 0)
				{
					maxIndex = inner;
				}
			}
			
			String tempMax = words[maxIndex];
			words[maxIndex] = words[index];
			words[index] = tempMax;
		}
		
		for (String word : words)
		{
			allWords += word + ", ";
		}
		
		return allWords;
	}
	
	public String analyzeTwitterForTopic(String topic)
	{
		String results = "";
		searchedTweets.clear();
		Query twitterQuery = new Query(topic);
		int resultMax = 750;
		long lastId = Long.MAX_VALUE;
		twitterQuery.setGeoCode(new GeoLocation(latitude, longitude), radius, Query.MEASUREMENT);
		ArrayList<Status> matchingTweets = new ArrayList<Status>();
		while(searchedTweets.size() < resultMax)
		{
			try 
			{
				QueryResult resultingTweets = chatbotTwitter.search(twitterQuery);
			}
			catch(TwitterException error)
			{
				appController.handleErrors(error);
			}
			
			twitterQuery.setMaxId(lastId - 1);
		}
		
		results += "talk about the search results";
		results += "Find a tweet that will pass one of the checkers in chatbot";
		
		int randomTweet = (int) (Math.random() * matchingTweets.size());
		results += matchingTweets.get(randomTweet);
		
		return results;
	}
}
