package com.programming.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessServlet
 */
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	
    	//poimts need to be alive tille the appln is alive, application specific data so creating context. and we need to do it only once at the beginning, so we override init(). 
    	
    	ServletContext sc = getServletContext(); 
    	Map<String,Integer> points = new HashMap<String, Integer>(); 
    	sc.setAttribute("PointsMap", points);
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String number = request.getParameter("guess"); 
		PrintWriter out = response.getWriter();
		
		String msg ="<html><body>";
		
		
		
		
		
		try
		{
			int num = Integer.parseInt(number);
			 
			 HttpSession session = request.getSession(false); 
				
				
				if(session==null)
				{
					msg = msg + "Session timed out! Start the game again! ";
					out.write(msg);
					request.getRequestDispatcher("StartGame.html").include(request, response);
				}
				
				else
				{
					
					String email = (String) session.getAttribute("email");
					int numOfTries = (Integer) session.getAttribute("Tries"); 
					int genValue = (Integer) session.getAttribute("genValue"); 
					if(numOfTries >= 3)
					{
						msg =  msg + "Exceeded the number of tries! Start the game again";
						out.write(msg);
						request.getRequestDispatcher("StartGame.html").include(request, response);
						
						session.removeAttribute("email");
						session.removeAttribute("genValue");
						session.removeAttribute("Tries");
						session.invalidate();
					}
					
					if(num == genValue)
					{
						msg =  msg + "Correct guess! you got 5 points!! <a href='leader'>Leader board</a> ";
						
						
						ServletContext sc = getServletContext(); 
						Map<String,Integer> map = (Map<String, Integer>) sc.getAttribute("PointsMap"); 
						
						if(map.get(email)!=null)
						{
							int pts = (Integer) map.get(email);
							pts = pts+5; 
							map.put(email, pts); 
							
						}
						
						else
						{
							map.put(email, 5); 
						}
						
						
						session.removeAttribute("email");
						session.removeAttribute("Tries");
						session.removeAttribute("genValue");
						session.invalidate();
						
						
					}
					else
					{
						numOfTries++; 
						 session.setAttribute("Tries", numOfTries);
						 
						 if(num>genValue)
						 {
							 msg = msg + "the guessed number is > generated value <br/>";
						 }
						 else
						 {
							 msg = msg + "the guessed number is < generated value <br/>";
						 }
						 
						 msg = msg + "Wrong guess ! Guess again!" + "<a href='Guess.html'>Click to guess again</a>" ;
						
						 
						 
						
						
					}
					
					out.write(msg);
					
				}
		}
		catch(NumberFormatException e)
		{
			out.write("Error! Enter the number in a correct format");
			request.getRequestDispatcher("Guess.html").include(request, response);
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
