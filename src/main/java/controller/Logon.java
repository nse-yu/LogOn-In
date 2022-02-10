package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.LogonLogic;
import entity.Account;

/**
 * Servlet implementation class Logon
 */
@WebServlet("/Logon")
public class Logon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dsp = request.getRequestDispatcher("/view/logon.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		//フォームの不足は再入力
		if(id == "" || pass == "" || mail == "" || name == "" || !pass.strip().equals(request.getParameter("re_pass").strip())) 
		{
			RequestDispatcher dsp = request.getRequestDispatcher("view/logon.jsp");
			dsp.forward(request, response);
			return;
		}
		
		Account account = new Account(id,pass,mail,name,age);
		
		if(LogonLogic.execute(account) == false) 
		{
			//登録できない
			//再入力
			System.out.println("logon failed");
			response.sendRedirect("Logon");
			return;
		}
		//セッションへ保存
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		//forwarding
		System.out.println("logon successed");
		RequestDispatcher dsp = request.getRequestDispatcher("/view/main.jsp");
		dsp.forward(request, response);
	}

}
