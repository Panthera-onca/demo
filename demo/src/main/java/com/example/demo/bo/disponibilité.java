package com.example.demo.bo;

public enum disponibilité {
		CREE("Le livre est dsponible pour book emprunte"),
		EN_COURS("Le livre est en cours d'etre offerté à client"),
		TEMINE("La emprunte est terminée"),
		EFFECTUE("La emprunte est effectuée");
		
	    private final String description;
	    
	    private disponibilité(String description) {
	    	this.description = description;
	    }
	    
	    @Override
	    public String toString() {
			return description;
	    	
	    }

	}
