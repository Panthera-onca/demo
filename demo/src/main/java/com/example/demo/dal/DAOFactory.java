package com.example.demo.dal;

public abstract class DAOFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
		
	}
	
	@SuppressWarnings("deprecation")
	public static LivreDAO getLivreDAO()  {
		LivreDAO livreDAO=null;
		try {
			livreDAO=(LivreDAO ) Class.forName("com.example.demo.dal.LivreDAOImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return livreDAO; 
	}
}
