package com.example.demo.bo;

public class Category {
		private int noCategorie;
		private String libelle;
		
		public Category() {}
		
		public Category(int noCategorie, String libelle) {
			this.noCategorie = noCategorie;
			this.setLibelle(libelle);
		}
		

		public int getNoCategorie(int i) {
			return noCategorie;
		}

		public void setNoCategorie(int noCategorie) {
			this.noCategorie = noCategorie;
		}

		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}

		

	}

