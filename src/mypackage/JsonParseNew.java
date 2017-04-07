package mypackage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParseNew {
	TestSuite testSuite = new TestSuite();
	
	public void readSuites() throws IOException{
		List<TestSuite.Result> pass = new ArrayList<TestSuite.Result>();
		List<TestSuite.Result> failed = new ArrayList<TestSuite.Result>();
		List<TestSuite.Result> blocked = new ArrayList<TestSuite.Result>();
		List<TestSuite.Result> overTime = new ArrayList<TestSuite.Result>();
		
		String strByJson = new String(Files.readAllBytes(Paths.get("test_results.json")), StandardCharsets.UTF_8);
		JsonObject jsonObject = new JsonParser().parse(strByJson).getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("test_suites");
		for (JsonElement suite : jsonArray) {
			 TestSuite testSuite = new Gson().fromJson(suite.getAsJsonObject(), TestSuite.class);
			 for(TestSuite.Result result : testSuite.results){
			 	 try{
					 if(result.status.equals("pass")){
						 pass.add(result);
					 }
					 if(result.status.equals("fail")){
						 failed.add(result);
					 }
					 if(result.status.equals("blocked")){
						 blocked.add(result);
					 }
					 if(!result.time.isEmpty()){
						 try {
							 if(Double.parseDouble(result.time)>10.0){
								 overTime.add(result);
							 }
						 } catch (Exception e) {
							 System.out.println("Parse error" + e.toString());
						 }

					 }
				 } catch (NullPointerException e){
			 	 	continue;
				 }

			 }
			 System.out.println(testSuite.suite_name );
			 System.out.println("Passed : " + pass.size());
			 for(TestSuite.Result result: pass){
				 testSuite.printResult(result);
			 }
			 System.out.println("Failed : " + failed.size());
			 for(TestSuite.Result result: failed){
				 testSuite.printResult(result);
			 }
			 System.out.println("Blocked :" + blocked.size());
			 for(TestSuite.Result result: blocked){
				 testSuite.printResult(result);
			 }
			 System.out.println("Overtime: " + overTime.size());
			 for(TestSuite.Result result: overTime){
				 testSuite.printResult(result);
			 }
			 System.out.println();
			 pass.clear();
			 failed.clear();
			 blocked.clear();
			 overTime.clear();
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String [] args) throws IOException{
		
		JsonParseNew jsonParseNew = new JsonParseNew();
		jsonParseNew.readSuites();
	}
}
