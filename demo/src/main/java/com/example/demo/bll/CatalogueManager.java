package com.example.demo.bll;

import java.util.List;
import com.example.demo.bo.Livre;
import com.example.demo.dal.DAOFactory;
import com.example.demo.dal.LivreDAO;
import com.example.demo.dal.DAOException;


public class CatalogueManager {
	private static LivreDAO daoLivres;

	public CatalogueManager() throws BLLException{
			//Instancier le Data Access Object
		daoLivres = DAOFactory.getLivreDAO();
	}
	
	
	public List<Livre> getCatalogue() throws BLLException{
		List<Livre> livres=null;
		try {
			livres = daoLivres.selectAll();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération catalogue", e);
		}
		
		return livres;
	}

	
	/**
	 * Ajout d'un article au catalogue
	 * @param newArticle
	 * @return index du nouvel article dans le catalogue
	 * @throws BLLException 
	 */
	public void addLivre(Livre newLivre) throws BLLException {
		if(newLivre.getIdLivre()!=null){
			throw new BLLException("Livre deja existant.");
		}
		try {
			validerLivre(newLivre);
			daoLivres.insert(newLivre);
		} catch (DAOException e) {
			throw new BLLException("Echec addArticle", e);
		}
	}

	/**
	 * updateArticle : Modifier un article du catalogue
	 * @param article
	 * @throws BLLException
	 */
	public void updateLivre(Livre livre) throws BLLException{
		try {
			validerLivre(livre);
			daoLivres.update(livre);
			
		} catch (DAOException e) {
			throw new BLLException("Echec updateArticle-article:"+livre, e);
		}
	}
	
	
	
	
	/**
	 * Supprimer un article du catalogue
	 * @param index
	 * @throws BLLException
	 */
	public void removeLivre(Livre livre) throws BLLException{
		try {
			daoLivres.delete(livre);
		} catch (DAOException e) {
			throw new BLLException("Echec de la suppression de l'article - ", e);
		}
		
	}
	
	/**
	 * Valider les données d'un article
	 * @param a
	 * @throws BLLException
	 */
	public void validerLivre(Livre l) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(l==null){
			throw new BLLException("Livre null");
		}
		//Les attributs des articles sont obligatoires
		if(l.getRefeni()==null || l.getRefeni().trim().length()==0){
			sb.append("La refeeni est obligatoire.\n");
			valide = false;
		}
		if(l.getNomLivre()==null || l.getNomLivre().trim().length()==0){
			sb.append("Le titre de livre  est obligatoire.\n");
			valide = false;
		}
		if(l.getAuteur()==null || l.getAuteur().trim().length()==0){
			sb.append("L'auteur  est obligatoire.\n");
			valide = false;
		}
		if(l instanceof Livre && ((Livre)l).getQteStock()<=0){
			sb.append("La quantite doit avoir une valeur positive.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
	
	

}

