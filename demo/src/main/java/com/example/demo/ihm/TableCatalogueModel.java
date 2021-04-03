package com.example.demo.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import com.example.demo.bo.Livre;

public class TableCatalogueModel extends AbstractTableModel{

	private List<Livre> catalogue;
	
	private String[] columnNames = {"", "Refeni",
            "nomLivre",
            "auteur",
            "categorie",
            "qteStock"};
	
	public TableCatalogueModel(List<Livre> catalogue) {
		this.catalogue = catalogue;
		
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }

	@Override
	public int getRowCount() {
		return catalogue.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object val = null;
		switch(columnIndex){
			case TableCatalogue.COL_ICON:
			val = catalogue.get(rowIndex) instanceof Livre;
			break;
			case TableCatalogue.COL_REFENI:
				val = catalogue.get(rowIndex).getRefeni();
				break;
			case TableCatalogue.COL_NOMLIVRE:
				val = catalogue.get(rowIndex).getNomLivre();
				break;
			case TableCatalogue.COL_AUTEUR:
				val = catalogue.get(rowIndex).getAuteur();
				break;

			case TableCatalogue.COL_CATEGORIE:
				val = catalogue.get(rowIndex).getCategorie();
				break;
			case TableCatalogue.COL_QTESTOCK:
				val = catalogue.get(rowIndex).getQteStock();
				break;
		}
		return val;
	}
	
	
	 
}
