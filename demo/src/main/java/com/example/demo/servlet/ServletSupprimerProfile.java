package com.example.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.bll.UserManager;
import com.example.demo.bo.userBean;
import com.example.demo.service.BusinessException;




@WebServlet("/ServletMisAJourDEProfile")
public class ServletSupprimerProfile extends HttpServlet{
private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 UserManager gestUtil = new UserManager();
	     HttpSession session = request.getSession();
	     userBean user = new userBean();
	     user = (userBean) session.getAttribute("user");
	     
	     try {
	    	 gestUtil.deleteUtilisateur(user);
	        } catch (BusinessException e) {
	            e.printStackTrace();
	        }
	        session.removeAttribute("user");

	        response.sendRedirect(request.getContextPath() + "/");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
