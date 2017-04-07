package mypackage;

import java.io.IOException;
import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class jsonParse {
	
	
	public static void main(String[] arg)  {
		Gson gson = new Gson();
		User user = new User();
		String jsonObject = gson.toJson(user);

		System.out.println(jsonObject);
		String jsonString = "{\"name\":\"kidou\",\"age\":24,\"email\":\"jiang@gmail.com\"}";
		JsonReader reader = new JsonReader(new StringReader(jsonString));
		try {
			reader.beginObject();
			while (reader.hasNext()){
				String s = reader.nextName();
				switch (s){
					case "name": 
						user.name = reader.nextString();
						break;
					case "age":
						user.age = reader.nextInt();
						break;
					case "email":
						user.emailAddress =reader.nextString();
						break;
				}
			}
			reader.endObject();
			System.out.println(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user1 = gson.fromJson(jsonString, User.class);
		System.out.println(user1);
	}

}



