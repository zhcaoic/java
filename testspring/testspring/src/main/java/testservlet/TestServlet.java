package testservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TestServlet.class.getName());
	
	@Override
	public void init() throws ServletException {
		logger.info("initial start !");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet start !");
		
		PrintWriter pw = resp.getWriter();
		pw.println("Test Servlet !");
		pw.flush();
		pw.close();
	}

	@Override
	public void destroy() {
		logger.info("destroy start !");
	}


}
