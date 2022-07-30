package com.howtodoinjava.demo.chatbot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountryListTest {
	
	
	
    // testing country for checking if is correct input or not
    @Test
	public void testCountry() {
    	// creating instance of class
    	String country = "Bratislava";
    	CountryList c = new CountryList();
    	String code = c.getCODE(country);
    	assertTrue(code.isBlank());//expecting true
    }

    @Test
	public void testCountry2(){
    	// creating instance of class
    	String country = "United Kingdom";
    	CountryList c = new CountryList();
    	String code = c.getCODE(country);
    	System.out.println("Code" + code);
    	assertTrue(code.equalsIgnoreCase("GB"));//expecting true
    }
    // testing if the space matter in the testing country list
    @Test
	public void testCountryListAfrica(){
    	// creating instance of class
    	String country = "SOUTH AFRICA";
    	CountryList c = new CountryList();
    	String code = c.getCODE(country);
    	assertEquals(code,"ZA"); // expecting equals
    }
    
    @Test
	public void testCountryList(){
    	// creating instance of class
    	String country = "Ireland";
    	CountryList c = new CountryList();
    	String code = c.getCODE(country);
    	assertFalse(code.isBlank());//expecting false
    }

}
