package com.festo.omdbapi;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OmdbapiApplicationTests {

	@Test
	void testJsonString() {
		MovieInfoController test = new MovieInfoController();
		
		ArrayList<MovieInformation> info = null;
		
		assertEquals("ERROR_JSONARRAY_NULL",test.makeBigJson(info));
		
		info = new ArrayList<MovieInformation>();
		assertEquals("ERROR_JSONARRAY_EMPTY",test.makeBigJson(info));
		
		info.add(new MovieInformation("Testas", 2000, 10, "test","tests"));
		
		String resp = "[{\"Title\":\"Testas\",\"Year\":\"2000\",\"imdbRating\":\"10.0\",\"plot\":\"test\",\"actors\":\"tests\"}]";
		assertEquals(resp,test.makeBigJson(info));
	}

}
