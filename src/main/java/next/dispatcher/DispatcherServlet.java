package next.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.Controller;


@WebServlet(name="dispatcher", urlPatterns="/", loadOnStartup=1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:"; 
       
    public DispatcherServlet() {
        super();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	log.debug("req: "+req);
    	log.debug("resp: "+resp);
    	
    	String url = req.getRequestURI();
    	log.debug("requestURI: "+url);
    	Controller controller = RequestMapping.getController(url);
    	try{
    		String viewName = controller.execute(req, resp);
    		log.debug("execute result : "+ viewName);
    		move(viewName, req, resp);
    	} catch(Throwable e){
    		e.printStackTrace();
    	}
    }


	private void move(String viewName, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(viewName.startsWith(DEFAULT_REDIRECT_PREFIX)){
			resp.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
			return;
		}
		log.debug("viewName final: " + viewName);
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.forward(req, resp);
	}
    
    

}
