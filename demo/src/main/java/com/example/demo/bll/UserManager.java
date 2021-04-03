package com.example.demo.bll;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.bo.userBean;
import com.example.demo.dal.CodesErreurDAO;
import com.example.demo.dal.DAOFactory;
import com.example.demo.dal.UserDAO;
import com.example.demo.service.BusinessException;

public class UserManager {
private UserDAO user;
	
	public UserManager() {
		this.user = DAOFactory.getUserDAO();
	}
	
	public userBean verificationLogin(userBean login) throws BusinessException {
        return this.user.checkID(login);
    }
    
	@SuppressWarnings("unused")
	private void validerTousLesChamps(Map parametres, BusinessException businessException) {
        for (Object o : parametres.keySet()) {
            String key = (String) o;
            String value = ((String[]) parametres.get(key))[0];
            if (value.trim().isEmpty() && !key.equals("telephone")) {
            	businessException.ajouterErreur(CodesErreur.CHAMPS_VIDE_ERREUR);
                break;
            }
        }
    }
	
	private void validerFormatUsername1(Map parametres, BusinessException businessException) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        Matcher m = p.matcher((CharSequence) parametres);
        while(m.find()) {
            if(!m.matches()) {
            	businessException.ajouterErreur(CodesErreur.ERREUR_FORMAT_USERNAME);
                break;
            }
        }
    }
	
	public userBean ajouterUtilisateur(@SuppressWarnings("rawtypes") Map parametres, String username, String email, userBean utilisateur, HttpServletRequest req) throws BusinessException{
		BusinessException businessException = new BusinessException();
		this.validerTousLesChamps(parametres, businessException);
		this.validerFormatUsername1(parametres, businessException);
		if (!req.getParameter("moteDePasse").equals(req.getParameter("confirmation"))) {
			businessException.ajouterErreur(CodesErreur.ERREUR_SAISIE_MDP);
            throw businessException;
        }
		if (!businessException.hasErreurs()) {
            this.validerUsername(username, businessException);
            user.insertUtilisateur(null, utilisateur);
        } else {
            throw businessException;
        }

        return utilisateur;
		
	}
	
	public userBean misAJourDUtilisateur(Map parametres, userBean utilisateurCourant, userBean modifs, HashMap<String, String> password, HttpServletRequest req) throws BusinessException{
		BusinessException businessException = new BusinessException();
		this.validerTousLesChamps(parametres, businessException);
		this.validerFormatUsername(modifs, businessException);
		
		if (!req.getParameter("nouveaumdp").equals(req.getParameter("confirmation"))) {
			businessException.ajouterErreur(CodesErreur.ERREUR_SAISIE_MDP);
            throw businessException;
        }
        if (!businessException.hasErreurs()) {
            if (!modifs.getUsername().equals(utilisateurCourant.getUsername())) {
                this.validerUsername(modifs.getUsername(), businessException);
            }
            if (!modifs.getPassword().equals(utilisateurCourant.getPassword())) {
            	businessException.ajouterErreur(CodesErreurDAO.ECHEC_VALIDATION_PASSWORD);
                throw businessException;
            }
            modifs.setNoUtilisateur(utilisateurCourant.getNoUtilisateur());
            user.misAJourDUtilisateur(modifs);

        } else {
            throw businessException;
        }

        return modifs;
	}
	
	private void validerUsername(String username, BusinessException businessException) throws BusinessException {
		this.user.checkUsername(username);
		
	}
	
	private void validerFormatUsername(userBean modifs, BusinessException businessException) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        Matcher m = p.matcher((CharSequence) modifs);
        while(m.find()) {
            if(!m.matches()) {
            	businessException.ajouterErreur(CodesErreur.ERREUR_FORMAT_USERNAME);
                break;
            }
        }
        }
	public userBean afficherUtilisateurInfoPrive(String username) throws BusinessException {
        return this.user.selectUserPublicInfo(username);
	}
	
	public userBean getUtilisateurInfor(String username) throws BusinessException {
        return this.user.selectUserPrivateInfo(username);
    }
	
	public boolean verificationMoteDePasse(userBean user) throws BusinessException {
        return this.user.passwordValid(user);
    }
	
	public void deleteUtilisateur(userBean user) throws BusinessException {
        this.user.deleteUtilisateur(user.getNoUtilisateur());
    }
	
}
