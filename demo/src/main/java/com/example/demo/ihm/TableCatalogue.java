package com.example.demo.ihm;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.example.demo.bo.Livre;

public class TableCatalogue extends JTable{
	public static final int COL_ICON = 0;
	public static final int COL_REFENI = 1;
	public static final int COL_NOMLIVRE = 2;
	public static final int COL_AUTEUR = 3;
	public static final int COL_CATEGORIE = 4;
	public static final int COL_QTESTOCK = 5;
	
    private List<Livre> catalogue;
	
	
	private static ImageLivreTableCellRenderer imageLivreCellRenderer;
	
    public TableCatalogue(List<Livre> catalogue) {
		
		super((TableModel) new TableCatalogueModel(catalogue));
		
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);

        this.getColumnModel().getColumn(COL_ICON).setPreferredWidth(50);
		this.getColumnModel().getColumn(COL_REFENI).setPreferredWidth(100);
		this.getColumnModel().getColumn(COL_NOMLIVRE).setPreferredWidth(100);
		this.getColumnModel().getColumn(COL_AUTEUR).setPreferredWidth(200);
		this.getColumnModel().getColumn(COL_CATEGORIE).setPreferredWidth(50);
		this.getColumnModel().getColumn(COL_QTESTOCK).setPreferredWidth(50);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		imageLivreCellRenderer= new ImageLivreTableCellRenderer();
		
		this.getColumnModel().getColumn(COL_ICON).setCellRenderer(imageLivreCellRenderer);
		
		this.setRowHeight(30);
		
	

}
}

