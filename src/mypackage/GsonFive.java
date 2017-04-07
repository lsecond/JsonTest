package mypackage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonFive {
	Gson gson = new Gson();
	
	void parseNoHeaderJArray() throws IOException {
		 String strByJson = new String(Files.readAllBytes(Paths.get("student.json")), StandardCharsets.UTF_8);
		 JsonParser parser = new JsonParser();
		 JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();
		 System.out.println(jsonArray.get(0));
		 
		 ArrayList<UserBean> userBeanList = new ArrayList<>();
		 for (JsonElement student : jsonArray) {
		        UserBean userBean = gson.fromJson(student, UserBean.class);
		        userBeanList.add(userBean);
		    }
		 System.out.println(userBeanList.get(0).age);
	}
	
	void sampleWay() throws IOException {
		Gson gson = new Gson();
		 String strByJson = new String(Files.readAllBytes(Paths.get("student.json")), StandardCharsets.UTF_8);
		 List<UserBean> userBeanList = gson.fromJson(strByJson,new TypeToken<List<UserBean>>(){}.getType());
		 System.out.println(userBeanList.get(0).age);
	}
	
	void parseWithHeaderJArray() throws IOException {
		 String strByJson = new String(Files.readAllBytes(Paths.get("student_withTitle.json")), StandardCharsets.UTF_8);
		 JsonObject jsonObject = new JsonParser().parse(strByJson).getAsJsonObject();
		 JsonParser parser = new JsonParser();
		 JsonArray jsonArray = jsonObject.getAsJsonArray("muser");
		 System.out.println(jsonArray.get(0));
		 ArrayList<UserBean> userBeanList = new ArrayList<>();
		 for (JsonElement student : jsonArray) {
			    UserBean userBean = gson.fromJson(student, new TypeToken<UserBean>() {}.getType());
		        userBeanList.add(userBean);
		    }
		 System.out.println(userBeanList.get(0).age);
	}
	
	private void parseComplexJArrayByCommon() throws IOException {
		String strByJson = new String(Files.readAllBytes(Paths.get("student_more.json")), StandardCharsets.UTF_8);
	    ResultBean resultBean = new Gson().fromJson(strByJson,ResultBean.class);
	    List<ResultBean.UserBean> userBeanList = resultBean.muser;
	    System.out.println(userBeanList.get(0).age);
	}
	
	void parseFastJArray() throws IOException {
		 String strByJson = new String(Files.readAllBytes(Paths.get("student_withTitle.json")), StandardCharsets.UTF_8);
		 JsonObject jsonObject = new JsonParser().parse(strByJson).getAsJsonObject();
		 JsonArray jsonArray = jsonObject.getAsJsonArray("muser");
		 ArrayList<UserBean> userBeanList = new ArrayList<>();
		 for (JsonElement user : jsonArray) {
		      UserBean userBean = new Gson().fromJson(user, new TypeToken<UserBean>() {}.getType());
		      if (Integer.parseInt(userBean.age) > 10) {
		          userBeanList.add(userBean);
		      }
		  }
		 
		 System.out.println(userBeanList.get(0).age);
	}
	
	public static void main(String [] args) throws IOException {
		GsonFive gsonFive = new GsonFive();
		//gsonFive.parseNoHeaderJArray();
		//gsonFive.parseWithHeaderJArray();
		//gsonFive.sampleWay();
		//gsonFive.parseComplexJArrayByCommon();
		gsonFive.parseFastJArray();
	}
}
