package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.LoginLogic;
import entity.Account;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dsp = request.getRequestDispatcher("view/login.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get param
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		//define user
		Account account = new Account(id,pass);
		
		if(LoginLogic.execute(account) == true) 
		{
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			//account found
			RequestDispatcher dsp = request.getRequestDispatcher("view/main.jsp");
			dsp.forward(request, response);
			return;
		}
		response.sendRedirect("Login");
	}

}
