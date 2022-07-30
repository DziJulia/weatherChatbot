package com.howtodoinjava.demo.chatbot;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import org.json.simple.parser.ParseException;

public class Chatbot {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";





	//test
	public static void main(String[] args) {
		try {

			String resourcesPath = ChatbotTest.getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("super", resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();
			String textLine = "";

			while (true) {
				System.out.print("Human : ");
				textLine = IOUtils.readInputTextLine();
				if ((textLine == null) || (textLine.length() < 1))
					textLine = MagicStrings.null_input; 
				if (textLine.equals("q")) {
					System.exit(0);
				} else if (textLine.equals("wq")) {
					bot.writeQuit();
					System.exit(0);
				} 
				/*ADDED CODE HARDOCED ABOUT ASKING FOR WEATHER*/
				else if(textLine.toLowerCase().contains("weather")|| textLine.toLowerCase().contains("forecast")){
					// JD it will be a good idea if the chatbot recognize and ask maybe if the town is in 2 countries and mabybe
					// ask about the right country?
					// also would be great if chatbot recognize weather and town in the same sentence? if weather is recognized
					//and town is not than ask to select the town.... and if its town in 2 countries ask about the right country

					HashMap<String, String> capitalCities = new HashMap<String, String>();
					int count = 0;
					while(count<5) {
						System.out.println("Robot : Please write your desired town or country" );
						System.out.print("Human : ");
						// need to check if the town actually exist
						textLine = IOUtils.readInputTextLine();
						String town = textLine;
						//               	 System.out.println(town);
						//checking the town if its not equal to country if it is not going to ask for the country
						while( Chatbot.checkTown(town)){
							//          		System.out.println(WeatherAPI.checkTown(town));
							if(Chatbot.checkTown(town)==false) {
								break;
							}
							System.out.println("Robot : Town or country is not found please enter again." );
							System.out.print("Human : ");
							textLine = IOUtils.readInputTextLine();
							town = textLine.substring(0, 1).toUpperCase() + textLine.substring(1).toLowerCase();

						}
						CountryList c = new CountryList();
						String code ; 
						// if town first input is not country if its country that getCODE wont be blank

						if(c.getCODE(town).isBlank()) {
							System.out.println("Robot : Please write your desired country" );
							System.out.print("Human : ");
							textLine = IOUtils.readInputTextLine();
							code = c.getCODE(textLine);
							// to check the country input is correct
							while(code.isBlank()){
								System.out.println("Robot : Country not recognized please enter again" );
								System.out.print("Human : ");
								textLine = IOUtils.readInputTextLine();
								c = new CountryList();
								code = c.getCODE(textLine);	
							}
							//need to add while loop to check if country exist
							while(WeatherAPI.getweather(town,code).contains("Error")) {
								if(WeatherAPI.getweather(town,code).contains("Error")) {
									System.out.println("Robot : "+WeatherAPI.getweather(town,code));
									System.out.print("Human : ");
									textLine = IOUtils.readInputTextLine();
									c = new CountryList();
									code = c.getCODE(textLine);	
								}
							}
						}else { code = town;}
						capitalCities.put(town, code);
						count++;
					}

					for ( String key : capitalCities.keySet() ) {
						// 		    System.out.println( key );

						System.out.println("Robot : "+ WeatherAPI.getweather(key,capitalCities.get(key)));
						//   	 System.out.println("Robot : "+ Clothes.recommendClothes((int) WeatherData.getTemp())); //call recommend clothes method from Clothes and pass temp from weather data as an int


					}

				}
				else {
					String request = textLine;
					if (MagicBooleans.trace_mode)
						System.out.println(
								"STATE=" + request + ":THAT=" + ((History<?>) chatSession.thatHistory.get(0)).get(0)
								+ ":TOPIC=" + chatSession.predicates.get("topic"));
					String response = chatSession.multisentenceRespond(request);
					while (response.contains("&lt;"))
						response = response.replace("&lt;", "<");
					while (response.contains("&gt;"))
						response = response.replace("&gt;", ">");
					System.out.println("Robot : " + response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}

	public static boolean checkTown(String q) throws IOException, InterruptedException, ParseException {
		return WeatherAPI.checkTown(q);
	}

}