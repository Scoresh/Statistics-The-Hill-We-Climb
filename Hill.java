/*
   @author: Daniel Sabalakov
   @date: 11/24/2025
   @purpose: To automate processes used in "The Hill We Climb"
*/

import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.ArrayList;

//Class Tale defines the Class
public class Hill{
   //Default Main, throws FileNotFound because I do not want to implement a Try Catch statement. Also surpress warnings for unchecked hashmap variables.
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws FileNotFoundException{
      //load map from a .csv file (comma seperated values). This matches a number with a word.
      HashMap<Integer,String> map = loadFromFile(new File("hill_we_climb.csv"));
      
      //initialize total Words, total Random Generations, and an array to hold all the generated integers.
      int totalWords = countMap(map);
      int totalGeneration = 24;
      int[] generated = generateRandomArray(totalGeneration,totalWords);
      
      //print the array
      printArray(generated);
      
      //seperator between problems
      printSeperator();
      
      
      //print out hashmap (p2)
      for (int i : generated){
         System.out.printf("[Position: %3d, Word: %-10s, Length: %2d]\n",i,map.get(i),map.get(i).length()); 
      }
      
      //seperator between problems
      printSeperator();
      

     
	}

   /*
         -------------
         BEGIN METHODS
         -------------
    */

   //generates random array
   public static int[] generateRandomArray(int totalGeneration, int totalWords){
      int[] generated = new int[totalGeneration];
      //Generate all random numbers, while also ensuring there are no duplicates.
		for (int i = 0; i < totalGeneration; i++){
			int z = (int)(Math.random() * totalWords) + 1;
			if (notInArray(generated,z)){generated[i] = z;}
			else {i-=1;}
		}
      return generated;
   }
   
   //counts highest key value.
   public static int countMap(HashMap<Integer,String> wordMap){
      Integer key = 0;
      for (Map.Entry<Integer, String> entry : wordMap.entrySet()) {
         key = entry.getKey();
      }
      return key;
   }
   
   //Load from file, generates Hashmap
   public static HashMap<Integer,String> loadFromFile(File f) throws FileNotFoundException{
      HashMap<Integer,String> returnMap = new HashMap<Integer,String>();
      Scanner c = new Scanner(f);
      while (c.hasNextLine()){
         String n = c.nextLine();
         String fp = n.substring(0,n.indexOf(","));
         String ep = n.substring(n.indexOf(",")+1);
         returnMap.put(Integer.valueOf(fp),ep);
      }
      return returnMap;
   }
   
   //generates frequency table checks for "existing" key, and if it cannot find it, it creates one and updates it.
   public static HashMap<Integer,Integer> generateFrequencyMap(HashMap<Integer,String> wordMap, int[] generated){
      HashMap<Integer,Integer> frequencyMap = new HashMap<Integer,Integer>();
      //loop through and find
      for (int i : generated){
         String word = wordMap.get(i);
         int wordLength = word.length();
         if (frequencyMap.containsKey(wordLength)){
            frequencyMap.put(wordLength,frequencyMap.get(wordLength)+1);
            
         }
         else {
            frequencyMap.put(wordLength,1);
         }
      }
      return frequencyMap;
      
   }
   
   //prints array of integers
	public static void printArray(int[] array){
		for (int i : array){
			System.out.println("Number: " + i);
		}
	}
   //prints array of doubles
	public static void printArray(double[] array){
		for (double i : array){
			System.out.println("Number: " + i);
		}
	}
   
   //checks if an integer i is NOT in array comp
	public static boolean notInArray(int[] comp, int n){
		for (int i : comp){
			if (i == n) {return false;}
			if (i == 0) {return true;}
		}
		return true;
	}
   //print map prints the map so I can generate some box plots
   public static void printMap(HashMap<Integer,Integer> map){
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
         Integer key = entry.getKey();
         Integer value = entry.getValue();
         for (int i = 0; i < value; i++){
            System.out.print(key + " ");
         }
      }
   }
   //frequency N or lower calculates number of objects in a hashmap N or lower.
   public static int calculateFrequencyOfN_or_lower(int n, HashMap<Integer,Integer> frequencyMap){
      int nOrLower = 0;
      for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
         Integer key = entry.getKey();
         Integer value = entry.getValue();
         if (key <= n){
            nOrLower+=value;
         }
      }
     return nOrLower;
   }

   //Calculuates Sample Population Integer N or lower.
   public static double sampleProportion_N_OrLower(int n, HashMap<Integer,Integer> frequencyMap){
      int total = 0;
      int nOrLower = 0;
      for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
         Integer key = entry.getKey();
         Integer value = entry.getValue();
         if (key <= n){
            nOrLower+=value;
         }
         total+=value;
     }
     return (double)nOrLower / total;
   }
   
   //print seperator
   public static void printSeperator(){
      System.out.println("\n\n-----------------------------------------------\n\n");
   }
}