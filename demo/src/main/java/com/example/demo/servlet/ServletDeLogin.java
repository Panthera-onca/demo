package com.example.demo.servlet;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ServletDeLogin")
public class ServletDeLogin extends HttpServlet{
private static final long serialVersionUID = 1L;
	 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userBean user = new userBean();
	    List<Integer> listErreur = new ArrayList<>();
	    UserManager userManager = new UserManager();
	    user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password")); 
        
        if (user.getUsername().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
        	listErreur.add(CodesErreurServlet.CHAMPS_VIDES_SERVLETS);
        }

        // Si la liste d'erreur n'est pas vide, on renvoie directement à la page login avec la liste d'erreur en attribut
        if (listErreur.size() > 0) {
            request.setAttribute("errorList", listErreur);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            rd.forward(request, response);
        } else {
        	 try {
                 userManager.verificationLogin(user);
                 user = userManager.getUtilisateurInfor(request.getParameter("username"));
                 user.setConnected(true);
                 HttpSession session = request.getSession();
                 session.setAttribute("user", user);

                 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
                 rd.forward(request, response);
        }catch (BusinessException e) {
            e.printStackTrace();
            request.setAttribute("errorList", e.getListeCodesErreur());
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            rd.forward(request, response);
        }
    }
	     
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		doGet(request, response);
	}

}
