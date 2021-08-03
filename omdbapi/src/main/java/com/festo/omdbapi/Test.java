package com.festo.omdbapi;

import javax.sound.midi.MidiDevice.Info;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void  main(String args[])
	{
	
		String test ="{\"Title\":\"The Matrix\",\"Year\":\"1999\",\"Rated\":\"R\",\"Released\":\"31 Mar 1999\",\"Runtime\":\"136 min\",\"Genre\":\"Action, Sci-Fi\",\"Director\":\"Lana Wachowski, Lilly Wachowski\",\"Writer\":\"Lilly Wachowski, Lana Wachowski\",\"Actors\":\"Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss\",\"Plot\":\"Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a hacker known as Neo. Neo has always questioned his reality, but the truth is far beyond his imagination. Neo finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker branded a terrorist by the government. As a rebel against the machines, Neo must confront the agents: super-powerful computer programs devoted to stopping Neo and the entire human rebellion.\",\"Language\":\"English\",\"Country\":\"United States, Australia\",\"Awards\":\"Won 4 Oscars. 42 wins & 51 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.7/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"88%\"},{\"Source\":\"Metacritic\",\"Value\":\"73/100\"}],\"Metascore\":\"73\",\"imdbRating\":\"8.7\",\"imdbVotes\":\"1,721,603\",\"imdbID\":\"tt0133093\",\"Type\":\"movie\",\"DVD\":\"01 Jan 2009\",\"BoxOffice\":\"$171,479,930\",\"Production\":\"Village Roadshow Prod., Silver Pictures\",\"Website\":\"N/A\",\"Response\":\"True\"}";
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
		try
		{
			MovieInformation info = mapper.readValue(test, MovieInformation.class);
			System.out.println(info.getYear());
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}

}
