package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinformController implements Controller{

	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/user/form.jsp";
	}
	
}
