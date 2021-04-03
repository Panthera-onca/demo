package com.example.demo.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.example.demo.bo.userBean;
import com.example.demo.bo.Livre;

public class beanStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime dateRetraite;
    private int idUtilisateur;
    private String nomUtilisateur;
    private int noLivre;
    
    public beanStock() {}
    
    public beanStock(LocalDateTime dateRetraite, int noLivre, int idUtilisateur) {
    	this.setDateRetraite(dateRetraite);
    	this.setNoLivre(noLivre);
    	this.setIdUtilisateur(idUtilisateur);
    	
    }

	public LocalDateTime getDateRetraite() {
		return dateRetraite;
	}

	public void setDateRetraite(LocalDateTime dateRetraite) {
		this.dateRetraite = dateRetraite;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public int getNoLivre() {
		return noLivre;
	}

	public void setNoLivre(int noLivre) {
		this.noLivre = noLivre;
	}

}
