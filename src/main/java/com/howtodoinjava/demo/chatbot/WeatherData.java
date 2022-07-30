package com.howtodoinjava.demo.chatbot;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class WeatherData {

	private static double temp;
	private double high;
	private double low;
	private String weather;
	private static String description;
	private String location;
	private String country;
	private DayOfWeek day;
	private static String clothes;

	public WeatherData(double temp, double high, double low, String weather,String description, String location,String country, DayOfWeek day,String clothes) {
		setTemp(temp);
		setHigh(high);
		setLow(low);
		setWeather(weather);
		setDescription(description);
		setLocation(location);
		setCountry(country);
		setDay(day);
		setClothes(clothes);
	}




	// Return the data as a formatted message
	public String toString() {
		return String.format("\n\nDay: %s \t\t\tCity: %s \t\t\tCountry: %s \t\tWeather: %s - %s.\n\nTemperature: %.1f\u00B0 Celsius \tHigh of: %.1f\u00B0 Celsius \t\tLow of: %.1f\u00B0 Celsius.\n %s\n ,=====================================================================================================================================",day,location, country, weather.toUpperCase(), description, temp, high, low, clothes);

	}



	public double getHigh() {
		return high;
	}

	public void setHigh (double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather.toLowerCase();
	}

	public static double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		WeatherData.temp = temp;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public static String getDescription() {
		return description;
	}

	public static ArrayList<String> parseJSON(String json) {
		return WeatherAPI.parseJSON(json);
	}




	public void setDescription(String description) {
		WeatherData.description = description;
	}
	public void setClothes(String clothes) {
		WeatherData.clothes = clothes;
	}
	public DayOfWeek getDay() {
		return day;
	}
	public void setDay(DayOfWeek day) {
		this.day = day;
	}
}