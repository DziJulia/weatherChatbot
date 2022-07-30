package com.howtodoinjava.demo.chatbot;



//create class for clothes
public class Clothes {


    //create method for recommending clothes
    public static String recommendClothes(double temp){
        String clothes;
        switch((int)temp){
            case -10:
            case -9:
            case -8:
            case -7:
            case -6:
            case -5:
            case -4:
            case -3:
            case -2:
            case -1:
                clothes = "It's freezing out, weather trousers, a jacket, hat, scarf and gloves";
                break;
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                clothes = "It's really cold, weather trousers, a jacket, hat and scarf";
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                clothes = "The weather is cold, weather trousers, a jacket and a hat";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                clothes = "The weather is mild, weather trousers and a jumper";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                clothes = "The weather is hot, weather shorts and a t-shirt";
                break;
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
                clothes = "The weather is hot, weather shorts and a t-shirt and don't forget suncream";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + temp);
        }
        return clothes;
    }
    
    public static String addedClothes(String description){
    	String addedclotes;
    	switch(description){
		     	case "Clear":
		   		    addedclotes = " don't forget sun glasses enjoy sunshine!";
		   		     break;
		    	case "Clouds":
		    		addedclotes= " don't forget your hoodie.";
		    		 break;
		    	case "Rain":
		    		 addedclotes = " open your umbrella on the way out!";
		    		 break;
		    	case "Thunderstorm":
		    		addedclotes = " better stay inside today!";
		    	case "Drizzle":	 
		    		 addedclotes = " wrap up and bring umbrella!";
		    		 break;
		    	case "Snow":
		    		 addedclotes = " don't forget Snow Boots stay dry!";
		    		 break;
		    	case "Atmosphere":	 
		    		 addedclotes = " and be carefull going outside!";
		    		 break;	 
		        default:
	                throw new IllegalStateException("Unexpected value: " + description);
    	}
    	return addedclotes;
    }

}
