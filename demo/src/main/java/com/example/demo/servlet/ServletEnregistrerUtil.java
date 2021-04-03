package com.example.demo.servlet;

import java.io.IOException;
import java.util.Map;

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

@WebServlet("/ServletEnregistrerUtil")
public class ServletEnregistrerUtil extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = new UserManager();

        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        Map parametres = request.getParameterMap();

        userBean user = new userBean();
        
        user.setUsername(request.getParameter("pseudo"));
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));
        user.setAddress(request.getParameter("address"));
        user.setCodePostal(Integer.parseInt(request.getParameter("codePostal")));
        user.setVille(request.getParameter("ville"));
        user.setPassword(request.getParameter("moteDePasse"));
        
        try {
            // Si l'insertion fonctionne, l'utilisateur est redirigé vers son profil
            userManager.ajouterUtilisateur(parametres, pseudo, email, user, request);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
            rd.forward(request, response);
        } catch (BusinessException e) {
            e.printStackTrace();
            request.setAttribute("errorList", e.getListeCodesErreur());
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
            rd.forward(request, response);

        }
        
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
