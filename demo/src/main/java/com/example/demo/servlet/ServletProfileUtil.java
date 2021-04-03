package com.example.demo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.bll.UserManager;
import com.example.demo.bo.userBean;
import com.example.demo.service.BusinessException;


@WebServlet("/ServletProfileUtil")
public class ServletProfileUtil extends HttpServlet{
private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        userBean utilisateurCourant = new userBean();
        String username = request.getParameter("username");
        utilisateurCourant = (userBean) session.getAttribute("user");
        if (session.getAttribute("user") == null && username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        UserManager userManager = new UserManager();
        userBean profile = new userBean();
        
        if (username != null) {
            if (session.getAttribute("user") == null || !username.equals(utilisateurCourant.getUsername())) {
                request.setAttribute("visitor", "visitor");
                try {
                    profile = userManager.getUtilisateurInfor(username);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            } else {
                profile = utilisateurCourant;
            }
        } else {
            profile = utilisateurCourant;}
        
        request.setAttribute("profile", profile);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
