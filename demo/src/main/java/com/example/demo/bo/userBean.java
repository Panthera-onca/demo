package com.example.demo.bo;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class userBean implements Serializable {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private static final long serialVersionUID = -3217244469889002238L;
	private UUID id = null;
	private int noUtilisateur;
	private String username;
	private String nom;
	private String prenom;
	private String address;
	private int codePostal;
	private String ville;
	private String password;
	private boolean administrateur;
	private boolean user;
	
	private boolean connected;
	
	public userBean() {
		super();
		this.id = id;
		this.setNoUtilisateur(noUtilisateur);
		this.setUsername(username);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAddress(address);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setPassword(password);
		this.setAdministrateur(administrateur);
		this.setUser(user);
	}

	public UUID getId() {
		return id;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int i) {
		this.noUtilisateur = i;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		if ( administrateur == false) {
			if(user != false) 
				return false;
		}else if (administrateur == false || user == false)
			return false;
		return true;
	}
	public boolean isConnected() {
        return connected;
    }

	public void setConnected(boolean connected) {
		this.connected = connected;
		
	}
	
	

}
