package com.example.demo.ihm;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


import com.example.demo.bo.Livre;

public class ImageLivreTableCellRenderer implements TableCellRenderer {
	private static ImageIcon imageLivre ;
	
	public ImageLivreTableCellRenderer() {
		imageLivre = new ImageIcon(getClass().getResource("../resources/pencil.gif"));
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		String type = (String) value;
		
		JLabel component = new JLabel();

		
		if(type.equals("L")){
			component.setIcon( imageLivre );	
		}
		
		component.setHorizontalAlignment(SwingConstants.CENTER);
		
		return component;
	}
	}


