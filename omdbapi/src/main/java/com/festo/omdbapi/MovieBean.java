package com.festo.omdbapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MovieBean {
	private String webUrl = "https://omdbapi.com/?type=movie&plot=full&r=json";
	private String ApiKey = "100f7364";
	private String[] films99 = {"The Matrix", "The Talented Mr. Ripley", "The Arrangement", "Tabaahi: The Destroyer", "The Zen Master", "The Insider"};
	private String[] films00 = {"The Patriot", "The Cell", "The 6th Day", "Remember the Titans", "The Beach", "The Perfect Storm", "The Replacements"};
	private String[] films20 = {"The Empty Man", "The Hunt", "Bill & Ted Face the Music", "The Father", "Birds of Prey: And the Fantabulous Emancipation of One Harley Quinn", "The Courier", "The Croods: A New Age"};

	@Bean
	public Map<Integer,ArrayList<MovieInformation>> moviesMap() throws IOException
	{
		Map<Integer, ArrayList<MovieInformation>> moviesMap = new HashMap<Integer,ArrayList<MovieInformation>>();
		ArrayList<MovieInformation> movieInfoList99 = new ArrayList<MovieInformation>();
		for(int i = 0; i < films99.length; i++)
		{
			String finalUrl = webUrl + "&apikey="+ApiKey + "&y="+1999+"&t="+films99[i];
			String response = getHttpRespond(finalUrl);
			
			movieInfoList99.add(parseJson(response));
		}
		
		moviesMap.put(1999, movieInfoList99);
		
		ArrayList<MovieInformation> movieInfoList00 = new ArrayList<MovieInformation>();
		for(int i = 0; i < films00.length; i++)
		{
			String finalUrl = webUrl + "&apikey="+ApiKey + "&y="+2000+"&t="+films00[i];
			String response = getHttpRespond(finalUrl);
		
			movieInfoList00.add(parseJson(response));
		}
		
		moviesMap.put(2000, movieInfoList00);
		
		ArrayList<MovieInformation> movieInfoList20 = new ArrayList<MovieInformation>();
		for(int i = 0; i < films20.length; i++)
		{
			String finalUrl = webUrl + "&apikey="+ApiKey + "&y="+2020+"&t="+films20[i];
			String response = getHttpRespond(finalUrl);
		
			movieInfoList20.add(parseJson(response));
		}
		
		moviesMap.put(2020, movieInfoList20);
		
		
	
		System.out.println("Movies Loaded!");
		return moviesMap;
	}
	public MovieInformation parseJson(String jsonResp)
	{
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MovieInformation info =null;
		try
		{
			info = mapper.readValue(jsonResp, new TypeReference<MovieInformation>() {});
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	public String getHttpRespond (String url) throws IOException
	{
		URL obj = new URL(url.replace(" ", "%20"));
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader inbuff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String inLine;
		StringBuffer response = new StringBuffer();
		
		while((inLine = inbuff.readLine())!=null)
		{
			response.append(inLine);
		}
		inbuff.close();
		return response.toString();
	}

}
