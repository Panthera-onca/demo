package com.example.demo.dal;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;



import com.example.demo.bo.Livre;



public class LivreDAOImpl implements LivreDAO {
	private static final String TYPE_LIVRE = "LIVRE";
	private static final String sqlSelectById = "select idLivre, refeni, nomLivre, auteur, categorie, qteStock " +
			" from livres where idArticle = ?";
	private static final String sqlSelectAll = "select idLivre, refeni, nomLivre, auteur, categorie, qteStock " +  
			" from livres";
	private static final String sqlUpdate = "update livres set refeni=?,nomLivre=?,auteur=?,categorie=?, qteStock=? where idArticle=?";
	private static final String sqlInsert = "insert into articles(refeni,nomLivre,auteur,categorie,qteStock) values(?,?,?,?,?)";
	private static final String sqlDelete = "delete from articles where idLivre=?";
	private static final String sqlSelectBynomLivre = "select refeni, nomLivre, auteur, categorie, qteStock " + 
													" from livres where nomLivre = ?";
	private static final String sqlSelectByAuteur = "select refeni, nomLivre, auteur, categorie, qteStock " + 
			" from livres where auteur = ?";
	private static final String sqlSelectByMotCle = "select refeni, nomLivre, auteur, categorie, qteStock " + 
													" from livres where nomLivre like ? or auteur like ?";

	@Override
	public Livre selectById(Livre livreCritere) throws DAOException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Livre liv = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, livreCritere.getIdLivre());

			rs = rqt.executeQuery();
			if (rs.next()){

				if (TYPE_LIVRE.equalsIgnoreCase(rs.getString("type").trim())){

					liv = new Livre(rs.getInt("idLivre"),
							rs.getString("refeni").trim(),
							rs.getString("nomLivre"),
							rs.getString("auteur"),
							rs.getObject("categorie"),
							rs.getInt("qteStock"));
				}
			}

		} catch (SQLException e) {
			throw new DAOException("selectById failed - livre = " + liv , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return liv;
	}

	@Override
	public List<Livre> selectAll() throws DAOException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Livre> liste = new ArrayList<Livre>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Livre liv = null;

			while (rs.next()) {
				if (TYPE_LIVRE.equalsIgnoreCase(rs.getString("type").trim())){

					liv = new Livre(rs.getInt("idLivre"),
							rs.getString("refeni").trim(),
							rs.getString("nomLivre"),
							rs.getString("auteur"),
							rs.getObject("categorie"),
							rs.getInt("qteStock"));
				}
				liste.add(liv);
			}
		} catch (SQLException e) {
			throw new DAOException("selectAll failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	@Override
	public void update(Livre data) throws DAOException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setInt(1, data.getIdLivre());
			rqt.setString(2, data.getRefeni());
			rqt.setString(3, data.getNomLivre());
			rqt.setString(4, data.getAuteur());
			rqt.setObject(5, data.getCategorie());
			rqt.setInt(6, data.getQteStock());
			if (data instanceof Livre) {
				Livre r = (Livre) data;
				rqt.setInt(6, r.getIdLivre());
				rqt.setNull(7, Types.VARCHAR);
			}

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Update article failed - " + data, e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx !=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		

	}

	@Override
	public void insert(Livre data) throws DAOException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setInt(1, data.getIdLivre());
			rqt.setString(2, data.getRefeni());
			rqt.setString(3, data.getNomLivre());
			rqt.setString(4, data.getAuteur());
			rqt.setObject(5, data.getCategorie());
			rqt.setInt(6, data.getQteStock());
			if (data instanceof Livre) {
				Livre l = (Livre) data;
				rqt.setString(6, TYPE_LIVRE);
				rqt.setNull(7, Types.VARCHAR);
			}

			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					data.setIdLivre(rs.getInt(1));
				}

			}

		}catch(SQLException e){
			throw new DAOException("Insert article failed - " + data, e);
		}
		finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DAOException("close failed - ", e);
			}

		}

	}

	@Override
	public void delete(Livre obj) throws DAOException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {		
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, obj.getIdLivre());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Delete article failed - article =" + obj, e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DAOException("close failed " , e);
			}

		}	

	}

	@Override
	public List<Livre> selectBynomLivre(String nomLivre) throws DAOException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Livre> liste = new ArrayList<Livre>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectBynomLivre);
			rqt.setString(1, nomLivre);
			rs = rqt.executeQuery();
			Livre liv = null;

			while (rs.next()) {
				if (TYPE_LIVRE.equalsIgnoreCase(rs.getString("type").trim())){

					liv = new Livre(rs.getInt("idLivre"),
							rs.getString("refeni"),
							rs.getString("nomLivre").trim(),
							rs.getString("auteur"),
							rs.getObject("categorie"),
							rs.getInt("qteStock"));
				}
				
				liste.add(liv);
			}
		} catch (SQLException e) {
			throw new DAOException("sqlSelectBynomLivre failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DAOException("close failed " , e);
			}
		}
		return liste;
	}

	@Override
	public List<Livre> selectByAuteur(String auteur) throws DAOException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Livre> liste = new ArrayList<Livre>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByAuteur);
			rqt.setString(1, auteur);
			rs = rqt.executeQuery();
			Livre liv = null;

			while (rs.next()) {
				if (TYPE_LIVRE.equalsIgnoreCase(rs.getString("type").trim())){

					liv = new Livre(rs.getInt("idLivre"),
							rs.getString("refeni"),
							rs.getString("nomLivre").trim(),
							rs.getString("auteur"),
							rs.getObject("categorie"),
							rs.getInt("qteStock"));
				}
				
				liste.add(liv);
			}
		} catch (SQLException e) {
			throw new DAOException("sqlSelectByAuteur failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DAOException("close failed " , e);
			}
		}
		return liste;
	}

	@Override
	public List<Livre> selectByMotCle(String motCle) throws DAOException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Livre> liste = new ArrayList<Livre>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByMotCle);
			rqt.setString(1, motCle);
			rs = rqt.executeQuery();
			Livre liv = null;

			while (rs.next()) {
				if (TYPE_LIVRE.equalsIgnoreCase(rs.getString("type").trim())){

					liv = new Livre(rs.getInt("idLivre"),
							rs.getString("refeni"),
							rs.getString("nomLivre").trim(),
							rs.getString("auteur"),
							rs.getObject("categorie"),
							rs.getInt("qteStock"));
				}
				liste.add(liv);
			}
		} catch (SQLException e) {
			throw new DAOException("selectByMotCle failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DAOException("close failed " , e);
			}
		}
		return liste;
	}
	}

