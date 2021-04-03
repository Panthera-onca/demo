package com.example.demo.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.*;
import org.springframework.stereotype.Component;

import com.example.demo.bo.userBean;
import com.example.demo.service.BusinessException;



@Component
public class UserDAOImpl implements UserDAO {
	private static final String INSERT_NEW_USER = "insert into utilisateurs (username, nom, prenom,  address, codePostal, ville, password) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_PWD = "select password from utilisateurs where no_utilisateur = ?";
    private static final String SELECT_USER_BY_USERNAME = "select username from utilisateurs where username = ?";
    private static final String SELECT_USER_BY_ID = "select username, password from utilisateurs where username = ? and password = ?";
    private static final String SELECT_USER_PUBLIC_INFO = "select username, nom, prenom, address, codPostal, ville from utilisateurs where username = ?";
    private static final String UPDATE_USER_INFO = "update utilisateurs set username = ?, nom = ?, prenom = ?, address = ?, codePostal = ?, ville = ?, password = ? where no_utilisateur = ?";
    private static final String DELETE_USER = "delete from utilisateurs where no_utilisateur = ?";

	@Override
	public int insertUtilisateur(UUID id, userBean Utilisateur) throws BusinessException{
		if(Utilisateur == null) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.NULL_OBJECT_EXCEPTION);
            throw businessException;
        }
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt = cnx.prepareStatement(INSERT_NEW_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, Utilisateur.getUsername());
                pstmt.setString(2, Utilisateur.getNom());
                pstmt.setString(3, Utilisateur.getPrenom());
                pstmt.setString(4, Utilisateur.getAddress());
                pstmt.setInt(5, Utilisateur.getCodePostal());
                pstmt.setString(6, Utilisateur.getVille());
                pstmt.setString(7, Utilisateur.getPassword());
                
                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                
                if (rs.next()) {
                	Utilisateur.setNoUtilisateur(rs.getInt(1));
                }
                rs.close();
                pstmt.close();
                cnx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                cnx.rollback();
                throw e;
            }
                
			
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.ECHEC_INSERT_OBJECT);
            throw businessException;
        }
		return 1;

	}

	@Override
	public userBean checkID(userBean login) throws BusinessException {
		boolean ok = false;
		 try(Connection cnx = ConnectionProvider.getConnection()){
			 PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
			 pstmt.setString(1, login.getUsername());
	         pstmt.setString(2, login.getPassword());
	         ResultSet rs = pstmt.executeQuery();
	         ok = rs.next();
		 }catch (SQLException throwables) {
	            throwables.printStackTrace();
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesErreurDAO.ECHEC_VALIDATION_LOGIN);
	            throw businessException;
	        }
	        if (!ok) {
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesErreurDAO.ECHEC_VALIDATION_LOGIN);
	            throw businessException;
	        }
	        return login;
	}

	@Override
	public void checkUsername(String username) throws BusinessException {
		boolean usernameNotAvailable = false;
		if(username == null) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.NULL_OBJECT_EXCEPTION);
            throw businessException;
        }
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_USERNAME);
			pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            usernameNotAvailable = rs.next();
			
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.ECHEC_LECTURE_DB);
            throw businessException;
        }

        if (usernameNotAvailable) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.ECHEC_SIGNUP_USERNAME_INUSE);
            throw businessException;
        }

		
	}


	@Override
	public userBean selectUserPublicInfo(String username) throws BusinessException {
		userBean public_info = new userBean();
		 List<String> profileInfo = new ArrayList<String>();
		 if(username == null) {
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesErreurDAO.NULL_OBJECT_EXCEPTION);
	            throw businessException;
	        }
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_PUBLIC_INFO);
		 }catch (SQLException throwables) {
	            throwables.printStackTrace();
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesErreurDAO.ECHEC_LECTURE_DB);
	            throw businessException;
	        }

	        return public_info;
	}

	@Override
	public userBean misAJourDUtilisateur(userBean Utilisateur) throws BusinessException {
		if(Utilisateur == null) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.NULL_OBJECT_EXCEPTION);
            throw businessException;
        }
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
                cnx.setAutoCommit(false);
                PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER_INFO);
                pstmt.setString(1, Utilisateur.getUsername());
                pstmt.setString(2, Utilisateur.getNom());
                pstmt.setString(3, Utilisateur.getPrenom());
                pstmt.setString(4, Utilisateur.getAddress());
                pstmt.setInt(5, Utilisateur.getCodePostal());
                pstmt.setString(6, Utilisateur.getVille());
                pstmt.setString(7, Utilisateur.getPassword());
                pstmt.executeUpdate();
                pstmt.close();
                cnx.commit();
			} catch (Exception e) {
                e.printStackTrace();
                cnx.rollback();
                throw e;
            }
			
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.ECHEC_INSERT_OBJECT);
            throw businessException;
        }
		return Utilisateur;
	}

	@Override
	public boolean passwordValid(userBean username) throws BusinessException {
		boolean ok = false;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_PWD);
			pstmt.setInt(1, username.getNoUtilisateur());
            ResultSet rs = pstmt.executeQuery();
            ok = rs.next();
			
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.ECHEC_VALIDATION_PASSWORD);
            throw businessException;
        }
        if (!ok) {
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.ECHEC_VALIDATION_PASSWORD);
            throw businessException;
        }
        return ok;
	}

	@Override
	public void deleteUtilisateur(userBean Utilisateur) throws BusinessException {
        try(Connection cnx = ConnectionProvider.getConnection()){
        	PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
        	pstmt.setInt(1, Utilisateur.getNoUtilisateur());
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesErreurDAO.ECHEC_DELETE_OBJECT);
            throw businessException;
        }
		
	}

	@Override
	public userBean selectUserPrivateInfo(String username) throws BusinessException {
		userBean private_info = new userBean();
		 List<String> profileInfo = new ArrayList<String>();
		 if(username == null) {
	           BusinessException businessException = new BusinessException();
	           businessException.ajouterErreur(CodesErreurDAO.NULL_OBJECT_EXCEPTION);
	           throw businessException;
	       }
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_PUBLIC_INFO);
		 }catch (SQLException throwables) {
	           throwables.printStackTrace();
	           BusinessException businessException = new BusinessException();
	           businessException.ajouterErreur(CodesErreurDAO.ECHEC_LECTURE_DB);
	           throw businessException;
	       }

	       return private_info;
		}
	
	@SuppressWarnings("unused")
	private userBean userBuilder(ResultSet rs) throws SQLException {
		userBean Utilisateur = new userBean();
		
		Utilisateur.setNom(rs.getString("nom"));
		Utilisateur.setUsername(rs.getString("username"));
		Utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		Utilisateur.setPrenom(rs.getString("prenom"));
		Utilisateur.setCodePostal(rs.getInt("code_postal"));
		Utilisateur.setPassword(rs.getString("mot_de_passe"));
		Utilisateur.setAddress(rs.getString("address"));
		Utilisateur.setVille(rs.getString("ville"));
        
		
		return Utilisateur;
	}

	@Override
	public void deleteUtilisateur(int i) throws BusinessException {
           try(Connection cnx = ConnectionProvider.getConnection()){
			
		   }catch (SQLException throwables) {
			   throwables.printStackTrace();
			   BusinessException businessException = new BusinessException();
			   businessException.ajouterErreur(CodesErreurDAO.ECHEC_DELETE_OBJECT);
			   throw businessException;
        }
		
	}
	}

