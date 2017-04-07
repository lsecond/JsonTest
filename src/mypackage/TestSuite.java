package mypackage;

import java.util.List;

public class TestSuite {
	public String suite_name;
	public List<Result> results;
	
	public class Result {
		public String test_name;
		public String time;
		public String status;
	}
	
	public void printResult(Result result){
		System.out.println("test name: " + result.test_name + " test time : " + result.time );
	}
}
