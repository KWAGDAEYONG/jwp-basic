package next.dispatcher;

import java.util.HashMap;
import java.util.Map;

import next.controller.Controller;
import next.controller.CreateUserController;
import next.controller.JoinformController;
import next.controller.ListUserController;

public class RequestMapping {
	private static Map<String, Controller> controllers = new HashMap<String, Controller>();
	
	static{
		controllers.put("/users", new ListUserController());
		controllers.put("/users/form", new JoinformController());
		controllers.put("/users/create", new CreateUserController());
	}
	
	static Controller getController(String requestUrl){
		return controllers.get(requestUrl);
	}
	
}
