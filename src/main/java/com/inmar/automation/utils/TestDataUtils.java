package com.inmar.automation.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;



public class TestDataUtils {
	private static long timeInMillis;
	public static int randomNumber;
	
	public static String uniqueAdultName; 
	public static String uniqueFirstName;
	public static String uniqueMiddleName;
	public static String uniqueLastName;
	
	public static String uniqueCustomerName;
	
	public static long getCurrentTimeInMillis() {
		timeInMillis = System.currentTimeMillis();
		return timeInMillis; 

	}
	public static long  getRandomNumbers()
	  {
		Random rand = new Random(); 
		  randomNumber = rand.nextInt(999);
		  System.out.println(randomNumber); 
		return randomNumber;
	  }
	
	public static long  getRandomNumbers5()
	  {
		Random rand = new Random(); 
		  randomNumber = rand.nextInt(99999);
		  System.out.println(randomNumber);
		return randomNumber;
	  }
	
	public static long  getnegitiveNumbers_D()
	  {
		Random rand = new Random(); 
		  randomNumber = rand.nextInt(999);
		  System.out.println(randomNumber);
		return randomNumber;
	  }
	
	public static long  getnegitiveNumbers_Ded()
	  {
		Random rand = new Random(); 
		  randomNumber = rand.nextInt(999);
		  System.out.println(randomNumber);
		return randomNumber;
	  }
	
	private static String[] Customer = {"Abraham Natural Foods","Amazon","American Outdoor Products","Ample Hills (Schmitt Ind.)","Blue Apron","Bozzuto's","C&S Wholesale Grocers","Christmas Tree Shops","Coastal Pacific Food","Davidson",
			"Dekalb Farmers Market","Diana's Bananas","Dot Foods","DPI - West","Food Lion (Hannaford)","Fresh Direct","General Trading Company Inc.","Grab the Gold","HelloFresh","HelloFresh Canada",
			"Holly Hill","iHerb, Inc.","KeHe","Lipari Foods","Marley Spoon","Merridian Distributors","Misfits Market","Nassau Candy","Peyton - Kroger","Royal Foods International",
			"UNFI","US Foods","Valu Merchandisers Company /AWG","Vitacost","Wakefern"};
	
	private static String[] Beginning = {""};
	 private static String[] Middle = {""};
	 private static String[] End = {""}; 

	   private static Random rand = new Random();

	   public static String generateFirstName() {
            
		   return Beginning[rand.nextInt(Beginning.length)];
	   } 
		   public static String generateMiddleName() {
	            
			   return Middle[rand.nextInt(Middle.length)];
		   }
		   public static String generateLastName() {
	            
			   return End[rand.nextInt(End.length)];
		   }
		   
		   
		   public static String getcustomer() {
	            
			   return Customer[rand.nextInt(Customer.length)];
		   } 
				
		   
		   public static String invoiceNumber()
			{
				return uniqueCustomerName;
			}
	      /*return Beginning[rand.nextInt(Beginning.length)] + 
	            Middle[rand.nextInt(Middle.length)]+
	            End[rand.nextInt(End.length)];*/

		   public static String getCustomerName() {
			   uniqueCustomerName = getcustomer();
				System.out.println("uniqueCustomerName: " + uniqueCustomerName);
				return uniqueCustomerName;
			}
		   public static String getFirstName() {
				uniqueFirstName = generateFirstName();
				System.out.println("uniqueFirstName: " + uniqueFirstName);
				return uniqueFirstName;
			}
		   public static String getMiddleName() {
				uniqueMiddleName = generateMiddleName();
				System.out.println("uniqueMiddleName: " + uniqueMiddleName);
				return uniqueMiddleName;
			}
		   public static String getlastName() {
				uniqueLastName = generateLastName();
				System.out.println("uniqueLastName: " + uniqueLastName);
				return uniqueLastName;
			}
		   
		   
	public static String randomIdentifier() {
		final String lexicon = "abcdefghijklmnopqrstuvwxyz";
		final java.util.Random rand = new java.util.Random();
		final Set<String> identifiers = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		String randomValue=null; 
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
			 randomValue =builder.toString();
		}
		return randomValue;
	}
	
}
