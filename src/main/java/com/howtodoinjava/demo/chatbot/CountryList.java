package com.howtodoinjava.demo.chatbot;


import java.text.Collator;
import java.util.*;
public class CountryList {
	

// country list for more accurancy of weather
	 private List<Country> getList() {
	       // Get ISO countries, create Country object and
	        // store in the collection.
		 List<Country> countries = new ArrayList<>();
	        String[] isoCountries = Locale.getISOCountries();
	        for (String country : isoCountries) {
	            Locale locale = new Locale("en", country);
	            String code = locale.getCountry();
	            String name = locale.getDisplayCountry();

	            if (!"".equals(code) && !"".equals(name)) {
	                countries.add(new Country(code, name));
	            }
	        }

	        // Sort the country by their name and then display the content
	        // of countries collection object.
	        countries.sort(new CountryComparator());
			return countries;
	    }
	 public String getCODE(String country) {
		  List<Country> c = getList();
		  String countrycode = "";
		    for (Country code : c) {
//		    	code.getName();
	            if(code.getName().equalsIgnoreCase(country) || code.getCode().equalsIgnoreCase(country)) {
	            countrycode = code.getCode();
	            }
		    }
	//    System.out.println();
		    return countrycode;
	 }
	 
	    /**
	     * CountryComparator class.
	     */
	    private static class CountryComparator implements Comparator<Country> {
	        private final Comparator<Object> comparator;

	        CountryComparator() {
	            comparator = Collator.getInstance();
	        }

	        public int compare(Country c1, Country c2) {
	            return comparator.compare(c1.name, c2.name);
	        }
	    }
    /**
     * Country pojo class.
     */
 private class Country {

        private final String code;
        private final String name;

        Country(String code, String name) {
            this.code = code;
            this.name = name;
        }
        public String getCode() {
        return code;
        }
        public String getName() {
            return name;
            }
        public String toString() {
            return code + " - " + name.toUpperCase();
        }
    }
}
