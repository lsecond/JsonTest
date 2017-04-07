package mypackage;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class GsonNew {
	Gson gson = new Gson();
	Student student = new Student(); 
	HashMap<String,Book> booksMap = new HashMap(); 
	
	public void test1 () {
	    student.id = 1;  
	    student.nickName = "lsecond";  
	    student.age = 22;  
	    student.email = "965266509@qq.com";  
	    System.out.println(  gson.toJson(student));
	    ArrayList<String> books = new ArrayList<String>();  
        books.add("math");  
        books.add("language");  
        books.add("englsih");  
        books.add("physics");  
        books.add("chemistry");  
        books.add("bio");  
        student.books = books; 
        System.out.println(  gson.toJson(student));
        HashMap<String, String> booksMap = new HashMap<String, String>();  
        booksMap.put("1", "math");  
        booksMap.put("2", "language");  
        booksMap.put("3", "englsih");  
        booksMap.put("4", "physics");  
        booksMap.put("5", "chemistry");  
        booksMap.put("6", "bio");  
        student.booksMap = booksMap;  
        String result = gson.toJson(student);
        System.out.println(  result);
        Student studentG = gson.fromJson(result, Student.class);
        System.out.println("id:" +  studentG.id);
	}
	
	public void test2() {
		
	}
	
	public static void main(String [] args) {
		GsonNew gsonNew = new GsonNew();
		gsonNew.test1();
	}
}
