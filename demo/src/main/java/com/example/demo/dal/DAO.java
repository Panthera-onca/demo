package com.example.demo.dal;

import java.util.List;
import com.example.demo.dal.DAOException;

public interface DAO<T> {
	
			public T selectById(T obj) throws DAOException;
			
			//Sélectionner tous les business objects 
			public List<T> selectAll() throws DAOException;
			
			//Modifier les attributs d'un business object
			public void update(T data) throws DAOException;
			
			//Insérer un nouveau business object
			public void insert(T data) throws DAOException;
			
			//Supprimer un business object
			public void delete(T obj) throws DAOException;

}
