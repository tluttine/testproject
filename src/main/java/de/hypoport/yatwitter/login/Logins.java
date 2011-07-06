package de.hypoport.yatwitter.login;

import java.util.HashMap;
import java.util.Map;


public class Logins {
	static Map<String,String> users=new HashMap<String, String>();
	static {
		users.put("Klaus", "klein");
		users.put("Peter", "hacke");
		users.put("Hanse", "glück");
	}
	
	public static boolean isValid(String name, String password) {
		String passwd = users.get(name);
		return ((passwd!=null) && (password.equals(passwd)));
	}
}
