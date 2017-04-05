package mypackage;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class JsonResultParse{
	
	public void readSuites(JsonReader jsonReader) throws IOException{
		
		int blockCount = 0;
		int overTimeCount = 0;
		
		SortedMap<String, String> passMap = new TreeMap<String, String>();
		SortedMap<String, String> failMap = new TreeMap<String, String>();
		
    	String node = jsonReader.nextName();
    	if (node.equals("suite_name")){
            System.out.println("Test suites : " + jsonReader.nextString());
        } 
        if(node.equals("results")){
        	 jsonReader.beginArray();
        	 while  (jsonReader.hasNext()) {
        		  jsonReader.beginObject();
        		  String tempName = "";
        		  String tempTime = "";
        		  while(jsonReader.hasNext()) {
					 String details = jsonReader.nextName();
	        		 if(details.equals("test_name")){
	        			 tempName = jsonReader.nextString().trim();
	        		 }
	        		 if(details.equals("time")){
	        			 tempTime = jsonReader.nextString();
	        			 double timeDouble= 0.0;
	        			 if(!tempTime.isEmpty()){
	        				try {
		        				 timeDouble = Double.parseDouble(tempTime);
							} catch (NumberFormatException e) {
								System.out.println("Parse error" + e.toString());
							}
	        				 if (timeDouble > 10.0){
	        					 overTimeCount++;
	        				 }
	        			 }
	        		 }
	        		 if(details.equals("status")){
	        			 String status = jsonReader.nextString();
	        			 switch (status) {
	        			 case "blocked":
	        				blockCount++; 
							break;
	        			 case "pass":
		        				passMap.put(tempName, tempTime);
								break;
	        			 case "fail":
		        				failMap.put(tempName, tempTime);
								break;
						default:
							break;
						}
	        		 }
				}
        		 jsonReader.endObject();
            }
        	jsonReader.endArray();
        	System.out.println("Pass test: " + passMap.size());
        	for(Map.Entry<String,String> entry : passMap.entrySet()) {
        		System.out.println(entry.getKey() + " runs " + entry.getValue() + " seconds");
        	}
        	System.out.println("Failed test: " + failMap.size());
        	for(Map.Entry<String,String> entry : failMap.entrySet()) {
        		System.out.println(entry.getKey() + " runs " + entry.getValue() + " seconds");
        	}
        	System.out.println("Block test: " + blockCount);
        	System.out.println("Over 10 seconds test: " + overTimeCount);
        }
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String [] args) throws IOException{
		JsonResultParse jsonResultParse = new JsonResultParse();
		JsonReader jsonReader = new JsonReader(new FileReader("test_results.json"));
	    jsonReader.beginObject();
	    jsonReader.nextName();
	    jsonReader.beginArray();
	    while(jsonReader.hasNext()){
	    	jsonReader.beginObject();
		    while (jsonReader.hasNext()) {
		    	jsonResultParse.readSuites(jsonReader);
		    }
		    System.out.print("\n");
		    jsonReader.endObject();
	   }
	   jsonReader.endArray();
	   jsonReader.endObject();
	   jsonReader.close();
	}
}
