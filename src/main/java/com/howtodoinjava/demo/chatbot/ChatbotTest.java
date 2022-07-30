package com.howtodoinjava.demo.chatbot;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;

class ChatbotTest{

    @Test
	public void testMyBot01() {
        String resourcesPath = getResourcesPath();
		Bot bot = new Bot("super", resourcesPath);
        Chat chatSession = new Chat(bot);
        bot.brain.nodeStats();
		String input="name";
		 String response = chatSession.multisentenceRespond(input);
		String expected = "SUPER";
		
		assertTrue(response.contains(expected));
	}



	public static String getResourcesPath() {
		return Chatbot.getResourcesPath();
	}
}
