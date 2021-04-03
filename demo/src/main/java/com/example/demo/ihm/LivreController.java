package com.example.demo.ihm;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.demo.bll.BLLException;
import com.example.demo.bll.CatalogueManager;
import com.example.demo.bo.Livre;

@Controller
public class LivreController {

    private EcranLivre ecrLivre ;
	
	private EcranCatalogue ecrCatalogue;
	
	//Attributs
	private int indexCatalogue;
	
	private CatalogueManager mger;
	
	private List<Livre> catalogue;
	
	private static LivreController instance;
	
	//Constructeur
	private LivreController(){
		try {
			mger = new CatalogueManager();
			
			//Initialisation du catalogue en mémoire
			catalogue = mger.getCatalogue();
			
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Méthodes
	public static synchronized LivreController get(){
		if(instance == null){
			instance = new LivreController();
		}
		return instance;
	}
	
	public void startApp(){
		ecrLivre = new EcranLivre();
		
		afficherPremierLivre();
		ecrLivre.setVisible(true);
		
		ecrCatalogue = new EcranCatalogue();
		ecrCatalogue.setVisible(true);
	}
	

	public void afficherPremierLivre(){
		if(catalogue.size()>0){
			indexCatalogue = 0;
			ecrLivre.afficherLivre(catalogue.get(indexCatalogue));
		}else{
			indexCatalogue = -1;
			ecrLivre.afficherNouveau();
		}

	}

	public void precedent(){
		if(indexCatalogue > 0){
			indexCatalogue--;
			ecrLivre.afficherLivre(catalogue.get(indexCatalogue));
		}
		
	}

	public void suivant() {
		if(indexCatalogue < catalogue.size()-1){
			indexCatalogue++;
			ecrLivre.afficherLivre(catalogue.get(indexCatalogue));
		}
		
	}

	public void nouveau() {
		indexCatalogue = catalogue.size();
		ecrLivre.afficherNouveau();
		notifierEcrCatalogue();
	}

	public void enregistrer() {
		Livre livreAffiche = ecrLivre.getLivre();
		
		try {
			if(livreAffiche.getIdLivre()==null){
				mger.addLivre(livreAffiche);
				System.out.println("article: " + livreAffiche);
				catalogue.add(livreAffiche);
				ecrLivre.afficherLivre(livreAffiche);
			}else{
				mger.updateLivre( livreAffiche);
				catalogue.set(indexCatalogue, livreAffiche);
			}
		} catch (BLLException e1) {
			ecrLivre.infoErreur("Erreur enregistrement.");
			e1.printStackTrace();
		}
		notifierEcrCatalogue();
	}

	public void supprimer() {
		
		try {
			mger.removeLivre(catalogue.get(indexCatalogue));
			catalogue.remove(indexCatalogue);
		} catch (BLLException e) {
			ecrLivre.infoErreur("Erreur suppression.");
			e.printStackTrace();
		}		

		if (indexCatalogue < catalogue.size() ) {
			// Afficher l'article suivant
			ecrLivre.afficherLivre(catalogue.get(indexCatalogue));
		} else if (indexCatalogue > 0) {
			indexCatalogue--;
			ecrLivre.afficherLivre(catalogue.get(indexCatalogue));
		} else {
			ecrLivre.afficherNouveau();
		}
		
		notifierEcrCatalogue();
		
	}

	public List<Livre> getCatalogue(){
			return catalogue;
	}
	
	private void notifierEcrCatalogue(){
		if(ecrCatalogue==null ){
			return;
		}
		
		ecrCatalogue.notifierChangementArticle();
	}
	

}
