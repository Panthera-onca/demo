package com.example.demo.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.example.demo.bo.Livre;

public class EcranLivre extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtRefeni, txtNomLivre, txtAuteur, txtStock, txtCategorie;
	private JRadioButton radioLivre;
	private JPanel panelType;
	private JCheckBox chk80, chk100;
	private JComboBox<String> cboCouleur;
	private JTextField txtQteStock;
	
	private PanelBoutons panelBoutons;
	private Integer idCourant;
	
	public EcranLivre() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 400);
		setResizable(false);
		setTitle("Détail livre");
		initIhm();
	}

	private void initIhm() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		// Ligne 1
		gbc.gridy = 0;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Refeni"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getTxtRefeni(), gbc);

		// Ligne 2
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("NomLivre"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getTxtNomLivre(), gbc);

		// Ligne 3
		gbc.gridy = 2;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Auteur"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getTxtAuteur(), gbc);

		// Ligne 4
		gbc.gridy = 3;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Stock"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getTxtStock(), gbc);

		// Ligne 5
		gbc.gridy = 4;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Type"), gbc);

		gbc.gridx = 1;
		gbc.gridheight = 1;
		panelPrincipal.add(getPanelType(), gbc);


		// Ligne 7
		gbc.gridy = 6;

		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Couleur"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getCboCouleur(), gbc);

		// Ligne 8
		gbc.gridy = 7;

		gbc.gridx = 0;
		gbc.gridwidth = 2;
		panelPrincipal.add(getPanelBoutons(), gbc);

		setContentPane(panelPrincipal);
	}

	public JTextField getTxtRefeni() {
		if (txtRefeni == null) {
			txtRefeni = new JTextField(30);
		}
		return txtRefeni;
	}

	public JTextField getTxtNomLivre() {
		if (txtNomLivre == null) {
			txtNomLivre = new JTextField(30);
		}
		return txtNomLivre;
	}

	public JTextField getTxtAuteur() {
		if (txtAuteur == null) {
			txtAuteur = new JTextField(30);
		}
		return txtAuteur;
	}

	public JTextField getTxtStock() {
		if (txtStock == null) {
			txtStock = new JTextField(30);
		}
		return txtStock;
	}

	public JTextField getTxtCategorie() {
		if (txtCategorie == null) {
			txtCategorie = new JTextField(30);
		}
		return txtCategorie;
	}

	public JPanel getPanelType() {
		if (panelType == null) {
			panelType = new JPanel();
			panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
			panelType.add(getRadioLivre());
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getRadioLivre());
		}
		return panelType;
	}


	public JRadioButton getRadioLivre() {
		if (radioLivre == null) {
			radioLivre = new JRadioButton("Livre");
			radioLivre.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					getChk100().setEnabled(true);
					getChk80().setEnabled(true);
					getCboCouleur().setEnabled(false);
					
				}
				
			});
		}
		return radioLivre;
	}

	

	public JCheckBox getChk80() {
		if (chk80 == null) {
			chk80 = new JCheckBox("80 grammes");
		}
		return chk80;
	}

	public JCheckBox getChk100() {
		if (chk100 == null) {
			chk100 = new JCheckBox("100 grammes");
		}

		return chk100;
	}

	public JComboBox<String> getCboCouleur() {
		if (cboCouleur == null) {
			String[] couleurs = { "bleu", "rouge", "noir", "vert" };
			cboCouleur = new JComboBox<String>(couleurs);
		}
		return cboCouleur;
	}

	
	public void afficherNouveau() {
		// Par défaut un article est une rammette
		Livre l = new Livre(null, "", "", "", "", 0);

		afficherLivre(l); 

	}
	
	public void afficherLivre(Livre l) {

		idCourant = l.getIdLivre();
		// Autres caractéristiques de l'article
		getTxtRefeni().setText(l.getRefeni() + "");
		getTxtNomLivre().setText(l.getNomLivre() + "");
		getTxtAuteur().setText(l.getAuteur() + "");
		getTxtCategorie().setText(String.valueOf(l.getCategorie()) + "");
		getTxtStock().setText(new Integer(l.getQteStock()) + "");

		if (l.getClass().equals(Livre.class)) {
			// Cas du stylo
			// sélectionner le bouton radio correspondant
			getRadioLivre().setSelected(true);
			// activer le choix des couleurs
			getCboCouleur().setEnabled(true);
			// Désactiver les cases à cocher
			getChk80().setEnabled(false);
			getChk100().setEnabled(false);
		} else {
			// Cas de la ramette
			// activer le bouton radio
			getRadioLivre().setSelected(true);
			// activer les cases à cocher
			getChk80().setEnabled(true);
			getChk100().setEnabled(true);
			// Papier de 80g par défaut
			// Désactiver les champs inutiles
			getCboCouleur().setSelectedItem(null);
			getCboCouleur().setEnabled(false);
		}
		getRadioLivre().setEnabled(l.getIdLivre() == null);

	}

	public void precedent() {
		LivreController.get().precedent();

	}

	public void suivant() {
		LivreController.get().suivant();

	}

	public void nouveau() {
		LivreController.get().nouveau();

	}

	public void enregistrer() {
		LivreController.get().enregistrer();

	}

	public void supprimer() {
		LivreController.get().supprimer();

	}

	public PanelBoutons getPanelBoutons() {
		if (panelBoutons == null) {
			panelBoutons = new PanelBoutons();
			panelBoutons.addPanelBoutonObserver(new IPanelBoutonObserver(){

				@Override
				public void precedent() {
					LivreController.get().precedent();					
				}

				@Override
				public void suivant() {
					LivreController.get().suivant();
				}

				@Override
				public void nouveau() {
					LivreController.get().nouveau();
					
				}

				@Override
				public void enregistrer() {
					LivreController.get().enregistrer();
					
				}

				@Override
				public void supprimer() {
					LivreController.get().supprimer();
				}
				
			});
				
			}
			
		return panelBoutons;
	}
	
	
	public Livre getLivre() {
		Livre livre=null;
		if(getRadioLivre().isSelected()){
			livre = new Livre();
		}
		try {
			livre.setIdLivre(idCourant);
			livre.setRefeni( getTxtRefeni().getText());
			livre.setNomLivre(getTxtNomLivre().getText());
			livre.setAuteur( getTxtAuteur().getText());
			livre.setCategorie((Object)(getTxtCategorie().getText()));
			livre.setQteStock(Integer.parseInt(getTxtStock().getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livre;
	}

	public void infoErreur(String msg) {
		JOptionPane.showMessageDialog(EcranLivre.this, msg, "", JOptionPane.ERROR_MESSAGE);
	}

}
