package com.example.demo.ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBoutons extends JPanel{
	private static final long serialVersionUID = 1L;

	private List<IPanelBoutonObserver> observateurs;
	
	private JButton btnPrecedent;
	private JButton btnNouveau;
	private JButton btnEnregistrer;
	private JButton btnSupprimer;
	private JButton btnSuivant;

	public PanelBoutons() {
		setLayout(new FlowLayout());
		
		add(getBtnPrecedent());
		add(getBtnNouveau());
		add(getBtnEnregistrer());
		add(getBtnSupprimer());
		add(getBtnSuivant());
		
		observateurs = new ArrayList<IPanelBoutonObserver>();
	}
	
	
	public void addPanelBoutonObserver(IPanelBoutonObserver observer){
		observateurs.add(observer);
	}
	
	
	public JButton getBtnPrecedent() {
		if(btnPrecedent ==null){
			btnPrecedent = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("resources/Back24.gif"));
			btnPrecedent.setIcon(image);
			btnPrecedent.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					for(IPanelBoutonObserver observateur: observateurs){
						observateur.precedent();
					}
				}

			});
		}
		return btnPrecedent;
	}


	public JButton getBtnNouveau() {
		if(btnNouveau ==null){
			btnNouveau = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("resources/New24.gif"));
			btnNouveau.setIcon(image);
			btnNouveau.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonObserver p: observateurs){
						p.nouveau();
					}
				}

			});
		}

		return btnNouveau;
	}


	public JButton getBtnEnregistrer() {
		if(btnEnregistrer ==null){
			btnEnregistrer = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("resources/Save24.gif"));
			btnEnregistrer.setIcon(image);
			btnEnregistrer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {

					for(IPanelBoutonObserver p: observateurs){
						p.enregistrer();
					}
					
				}

			});
		}
		return btnEnregistrer;
	}


	public JButton getBtnSupprimer() {
		if(btnSupprimer ==null){
			btnSupprimer = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("resources/Delete24.gif"));
			btnSupprimer.setIcon(image);
			btnSupprimer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonObserver p: observateurs){
						p.supprimer();
					}

					
				}

			});
		}
		return btnSupprimer;
	}


	public JButton getBtnSuivant() {
		if(btnSuivant ==null){
			btnSuivant = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("resources/Forward24.gif"));
			btnSuivant.setIcon(image);
			btnSuivant.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonObserver p: observateurs){
						p.suivant();
					}
				}

			});
		}
		return btnSuivant;
	}

	

}
