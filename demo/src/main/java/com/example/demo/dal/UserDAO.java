package com.example.demo.dal;

import com.example.demo.bo.userBean;
import com.example.demo.service.BusinessException;

import java.util.UUID;

public interface UserDAO {
	int insertUtilisateur(UUID id, userBean Utilisateur) throws BusinessException;
	default int addUtilisateur(userBean Utilisateur) throws BusinessException {
		UUID id = UUID.randomUUID();
		return insertUtilisateur(id, Utilisateur);
	}
	public userBean checkID(userBean login) throws BusinessException;
	public void checkUsername(String username) throws BusinessException;
	public userBean selectUserPublicInfo(String username) throws BusinessException;
	public userBean misAJourDUtilisateur(userBean Utilisateur) throws BusinessException;
	public boolean passwordValid(userBean username) throws BusinessException;
	public void deleteUtilisateur(int i) throws BusinessException;
	public userBean selectUserPrivateInfo(String username) throws BusinessException;
	void deleteUtilisateur(userBean Utilisateur) throws BusinessException;

}
