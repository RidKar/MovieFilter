package com.festo.omdbapi;

import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieInfoController {
	
	@Autowired
	private Map<Integer,ArrayList<MovieInformation>> moviesMap;
	
	
	@RequestMapping("getImdbAverages/")
	public String getRatingAverage()
	{
		JSONArray jsonRateArray = new JSONArray();
		
		for(Map.Entry<Integer,ArrayList<MovieInformation>> entry : moviesMap.entrySet())
		{
			JSONObject jsonRate = new JSONObject();
			ArrayList<MovieInformation> moviesInfoArray =  entry.getValue();
			int cnt = 0;
			double average = 0;
			for(int i = 0; i < moviesInfoArray.size(); i++)
			{
				average += moviesInfoArray.get(i).getImdbRating();
				cnt++;
			}
			jsonRate.put("Year", entry.getKey().toString());
			jsonRate.put("imdbRateAverage", String.format("%.1f", (average/cnt)));
			jsonRateArray.add(jsonRate);
		}
		
		return jsonRateArray.toJSONString();
	}
	
	
	@RequestMapping("getMoviesOverRates")
	@ResponseBody
	public String getMoviesOverRates(@RequestParam String rate )
	{
		ArrayList<MovieInformation> movieOverRate = new ArrayList<MovieInformation>();
		for(Map.Entry<Integer,ArrayList<MovieInformation>> entry : moviesMap.entrySet())
		{
			ArrayList<MovieInformation> moviesInfoArray =  entry.getValue();
			for(int i = 0; i < moviesInfoArray.size(); i++)
			{
				double movieRate = moviesInfoArray.get(i).getImdbRating();
				if(Double.parseDouble(rate) < movieRate)
				{
					if(movieOverRate.isEmpty())
					{
						movieOverRate.add(moviesInfoArray.get(i));
					}
					else
					{
						boolean added = false;
						for(int d = 0; d < movieOverRate.size(); d++)
						{
							if(movieOverRate.get(d).getImdbRating() < movieRate)
							{
								movieOverRate.add(d, moviesInfoArray.get(i));
								added = true;
								break;
							}
							else if(movieOverRate.get(d).getImdbRating() == movieRate)
							{
								if(movieOverRate.get(d).getTitle().compareTo(moviesInfoArray.get(i).getTitle()) == 1)
								{
									movieOverRate.add(d, moviesInfoArray.get(i));
									added = true;
									break;
								}
							}
						}
						if(!added)
						{
							movieOverRate.add(moviesInfoArray.get(i));
						}
					}
				}
			}
		}	
		String jsonString = makeBigJson(movieOverRate);
		
		return jsonString;
	}
	
	public String makeBigJson (ArrayList<MovieInformation> array)
	{
		if(array == null || array.isEmpty())
		{
			return "ERROR_JSONARRAY_NULL";
		}
		if(array.isEmpty())
		{
			return "ERROR_JSONARRAY_EMPTY";
		}
		StringBuilder str = new StringBuilder();
		
		str.append("[");
		for(int i = 0; i <array.size(); i++ )
		{
			str.append(array.get(i).getJsonString());
			if(i+1 < array.size())
			{
				str.append(",");
			}
		}
		str.append("]");
		return str.toString();
	}

}
