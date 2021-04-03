package com.example.demo.ihm;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EcranCatalogue extends JFrame{
private TableCatalogue tblCatalogue;
	
	public EcranCatalogue() {
		super("Catalogue");
		
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Toolkit tk =Toolkit.getDefaultToolkit();
		this.setIconImage(tk.getImage(getClass().getResource("../resources/aim.png")));

		
		initComposants();
		 

	        
	}
	
	private void initComposants(){
		JPanel mainContent = new JPanel();
		mainContent.setOpaque(true);
		
		mainContent.setLayout(new GridLayout(1, 0));
		tblCatalogue = new TableCatalogue(LivreController.get().getCatalogue());

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(tblCatalogue);

        //Add the scroll pane to this panel.
        mainContent.add(scrollPane);

       
		
		this.setContentPane(mainContent);
	}
	
	public void notifierChangementArticle(){
		((TableCatalogueModel)tblCatalogue.getModel()).fireTableDataChanged();
	}


}
