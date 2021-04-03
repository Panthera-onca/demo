package com.example.demo.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Livre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idLivre;
	private String refeni;
	private String nomLivre;
	private String auteur;
	private Object categorie;
	private int qteStock;
	
    public Livre(){
		
	}

    
    

	public Livre(Integer idLivre, String refeni, String nomLivre, String auteur, Object categorie, int qteStock) {
		super();
		this.idLivre = idLivre;
		this.refeni = refeni;
		this.nomLivre = nomLivre;
		this.auteur = auteur;
		this.categorie = categorie;
		this.qteStock = qteStock;
	}




	public Integer getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(Integer idLivre) {
		this.idLivre = idLivre;
	}

	public String getRefeni() {
		return refeni;
	}

	public void setRefeni(String refeni) {
		this.refeni = refeni;
	}

	public String getNomLivre() {
		return nomLivre;
	}

	public void setNomLivre(String nomLivre) {
		this.nomLivre = nomLivre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Object getCategorie() {
		return categorie;
	}

	public void setCategorie(Object categorie) {
		this.categorie = categorie;
	}

	public int getQteStock() {
		return qteStock;
	}

	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}




	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", refeni=" + refeni + ", nomLivre=" + nomLivre + ", auteur=" + auteur
				+ ", categorie=" + categorie + ", qteStock=" + qteStock + "]";
	}
    

}
