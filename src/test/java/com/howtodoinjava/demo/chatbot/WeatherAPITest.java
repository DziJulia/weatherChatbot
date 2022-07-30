package com.howtodoinjava.demo.chatbot;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class WeatherAPITest {

	
    // testing if the town with the space found or not found
    @Test
	public void testtownWIthspace() throws IllegalArgumentException, IOException, InterruptedException, ParseException, JSONException{
    
    	String town = "Costa rica";
    	assertFalse(Chatbot.checkTown(town));//expecting false
    }
    
    
    // testing deletion of space
    @Test
	public void testtownWIthooutspace() throws IllegalArgumentException, IOException, InterruptedException, ParseException, JSONException{
    
    	String town = "Costa rica";
    	town = town.replace(" ", "%20");
    	assertFalse(Chatbot.checkTown(town));//expecting false
    }
    
    // testing if town belongs to country
    @Test
	public void testcountrytownFalse() throws IllegalArgumentException, IOException, InterruptedException, ParseException, JSONException{
    	// creating instance of class
    	String country = "Ireland";
    	String town = "Dublin";
    	CountryList c = new CountryList();
    	String code = c.getCODE(country);
    	assertFalse(WeatherAPI.getweather(town,code).contains("Error"));//expecting false
    }
    // testing if town belongs to country
    @Test
	public void testcountrytownTrue() throws IllegalArgumentException, IOException, InterruptedException, ParseException, JSONException{
    	// creating instance of class
    	String country = "Spain";
    	String town = "Dublin";
    	CountryList c = new CountryList();
    	String code = c.getCODE(country);
    	assertTrue(WeatherAPI.getweather(town,code).contains("Error"));//expecting true
    }
    // testing if the space matter in the testing country list
    @Test
	public void testCountryListAfricaNotequal(){
    	// creating instance of class
    	String country = "SOUTH AFRICA";
    	country = country.replace(" ", "%20");
    	CountryList c = new CountryList();
    	String code = c.getCODE(country);
    	assertNotEquals(code,"ZA");//expecting false
    }
    // testing the town boolean if it is recognized otr not if it recognized it will return false 
    //due i would need to break the while loop
    @Test
	public void testTown() throws IOException, InterruptedException, ParseException {
    	String town = "dublin";
    	assertFalse(Chatbot.checkTown(town));
    }
    @Test
	public void testTown2() throws IOException, InterruptedException, ParseException {
    	String town = "sdvfg";
    	assertTrue(Chatbot.checkTown(town)); // expected true due
    }



}
