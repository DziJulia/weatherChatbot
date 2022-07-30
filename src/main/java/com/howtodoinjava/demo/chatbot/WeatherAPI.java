package com.howtodoinjava.demo.chatbot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;




public class WeatherAPI {


	public static String getweather(String q, String c) throws IOException, InterruptedException, ParseException, JSONException,IllegalArgumentException{
		// SO DUE DUBLIN IS IN 2 COUNTRIES IT IS ESENTIAL TO KNOW THE COUNTRY TO

		try {
			//		q = q.replace(" ", "%20"); i cant assign it because thatn my country wont match
			//		c = c.replace(" ", "%20");
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://community-open-weather-map.p.rapidapi.com/forecast/daily?q=" + q.replace(" ", "%20") + "%2C" + c.replace(" ", "%20") + "&lat=0&lon=0&callback=test&id=2172797&lang=null&units=metric&mode=xml"))
					.header("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
					.header("X-RapidAPI-Key", "63d9adbc22msha6c63ede5d4274bp1b8b2djsn4728a654c489")
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			String text =  response.body().substring(5,response.body().length()-1);
			//   System.out.println(text);
			//  String result = parseJSON(text);
			//	      System.out.println(parseJSON(text));
			return WeatherData.parseJSON(text).toString();
		}
		catch(JsonSyntaxException e ) {
			//			System.out.println("Robot : Town is not belong to the country, Please enter country again: ");
			return "Error! Town is not belong to the country, Please enter country again: ";
		}
	}  

	private static String getTown(String q) throws IOException, InterruptedException, ParseException{
		// SO DUE DUBLIN IS IN 2 COUNTRIES IT IS ESENTIAL TO KNOW THE COUNTRY TOO
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q=" + q.replace(" ", "%20") + "&lat=0&lon=0&callback=test&id=2172797&lang=null&units=metric&mode=xml"))
					.header("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
					.header("X-RapidAPI-Key", "63d9adbc22msha6c63ede5d4274bp1b8b2djsn4728a654c489")
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			String text =  response.body();
			//	      System.out.println(text);
			//  String result = parseJSON(text);
			return text;
		}
		catch(JsonSyntaxException e ) {
			return "Error";
		}
	}

	// boolean for checking the town if is recognized 
	// changed according to JUNIT test it coudnt the string coudnt equal to error as messaged for json syntax exception popped uop
	// it nneeded to be chagned for contain error
	public static boolean checkTown(String q) throws IOException, InterruptedException, ParseException{

		boolean valid = true;
		//		 System.out.println(getTown(q));
		if( getTown(q).contains("Error")) {
			valid = true;
		}else {
			valid = false;
		}
		//	System.out.println(valid + " " + q);
		return valid;
	}  


	static ArrayList<String> parseJSON(String json) {

		ArrayList<String> list = new ArrayList<String>();
		// Parse entire JSON string and convert to object
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();

		//extracting data array from json string
		JsonArray ja_data = object.getAsJsonArray("list");
		String country = "";
		String location = "";
		DayOfWeek day ;
		//getting lenght of json array
		LocalDate today = LocalDate.now();
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		//loop to get all json objects from data json array
		for(int i=0; i<3; i++) 
		{

			JsonObject jObj = ja_data.get(i).getAsJsonObject();
			// Get the object under the "main" key
			JsonObject main = jObj.getAsJsonObject("temp");

			// Get the temperatures from "main" object
			double temp = main.get("day").getAsDouble();
			double high = main.get("max").getAsDouble();
			double low = main.get("min").getAsDouble();

			// Get weather from the main object of first element of "weather" array in the "main" object
			String weather = jObj.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
			String description = jObj.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
			day = dayOfWeek.plus(i);
			//       System.out.println(day);

			JsonObject jObj_city =object.getAsJsonObject("city");
			// Get the location name from the root object
			location = jObj_city .get("name").getAsString();

			country= jObj_city .get("country").getAsString();

			WeatherData items = new WeatherData(temp, high, low, weather,description, location, country,day,WeatherAPI.recommendClothes(temp)+WeatherAPI.addedClothes(weather));
			String data = WeatherAPI.toString(items);
			// Return fetched data as a WeatherData object
			list.add(data);
			//  System.out.println(list);
		}

		return list;
	}

	public static String toString(WeatherData d){//overriding the toString() method  
		return d.toString();
	}

	public static String addedClothes(String description) {
		return Clothes.addedClothes(description);
	}

	public static String recommendClothes(double temp) {
		return Clothes.recommendClothes(temp);
	}  
}   