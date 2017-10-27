package chat.model;

import java.time.LocalDate;

public class Movie
{
	private String title;
	private String genre;
	private String ratingMPAA;
	private String review;
	private int length;
	private LocalDate releaseDate;
	private double starScore;
	
	public Movie(String title)
	{
		this.title = "";
		this.genre = "";
		this.ratingMPAA = "";
		this.review = "";
		this.length = 0;
		this.releaseDate = null;
		this.starScore = 0.0;
	}

	public String getTitle()
	{
		return title;
	}

	public String getGenre()
	{
		this.genre = "@@@@@@@";
		return genre;
	}

	public String getRatingMPAA()
	{
		return ratingMPAA;
	}

	public String getReview()
	{
		return review;
	}

	public int getLength()
	{
		return length;
	}

	public LocalDate getReleaseDate()
	{
		return releaseDate;
	}

	public double getStarScore()
	{
		return starScore;
	}

	public void setTitle(String title)
	{
	}

	public void setGenre(String genre)
	{
	}

	public void setRatingMPAA(String ratingMPAA)
	{
	}

	public void setReview(String review)
	{
	}

	public void setLength(int length)
	{
	}

	public void setReleaseDate(LocalDate releaseDate)
	{
	}

	public void setStarScore(double starScore)
	{
	}
	
	public String toString()
	{
		return null;
	}
}
