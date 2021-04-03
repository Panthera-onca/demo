package com.example.demo.dal;

import com.example.demo.bo.Livre;
import com.example.demo.dal.DAOException;
import java.util.List;

public interface LivreDAO extends DAO<Livre> {
	public List<Livre> selectBynomLivre(String nomLivre) throws DAOException;
	public List<Livre> selectByAuteur(String auteur) throws DAOException;
	public List<Livre> selectByMotCle(String motCle) throws DAOException;
	
}
