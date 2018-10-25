package com.programming.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartGameServlet
 */
public class StartGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartGameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String email = request.getParameter("email"); 
		PrintWriter out = response.getWriter(); 
		
		
		if(email==null||email.trim().equals(""))
		{
			out.write("<html><body><b> Error! Enter your email address again! </b></body></html>");
			
			request.getRequestDispatcher("StartGame.html").include(request, response);
		}
		
		else
		{
			HttpSession session = request.getSession(); 
			
			session.setAttribute("email", email);
			
			int genValue = (int) (Math.random()*10); 
			
			session.setAttribute("genValue", genValue);
			
			int numOfTries=0; 
			
			session.setAttribute("Tries", numOfTries);
			
			request.getRequestDispatcher("Guess.html").forward(request, response);
			
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
