package com.festo.omdbapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieInformation {
	private @JsonProperty("Title") String title;
	private @JsonProperty("Year") int year;
	private @JsonProperty("imdbRating") double imdbRating;
	private @JsonProperty("Plot") String plot;
	private @JsonProperty("Actors") String actors;

	public MovieInformation()
	{
		
	}
	public MovieInformation(String title, int year, double imdbRating, String plot, String actors)
	{
		this.title = title;
		this.year = year;
		this.imdbRating = imdbRating;
		this.plot = plot;
		this.actors = actors;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public double getImdbRating()
	{
		return imdbRating;
	}

	public void setImdbRating(double imdbRating)
	{
		this.imdbRating = imdbRating;
	}

	public String getPlot()
	{
		return plot;
	}

	public void setPlot(String plot)
	{
		this.plot = plot;
	}

	public String getActors()
	{
		return actors;
	}

	public void setActors(String actors)
	{
		this.actors = actors;
	}
	
	public String getJsonString()
	{
		return "{\"Title\":\"" + title + "\",\"Year\":\"" + year + "\",\"imdbRating\":\"" + imdbRating + "\",\"plot\":\""+plot +"\",\"actors\":\""+actors +"\"}";
	}

}
