package vue;

import javax.swing.*;
import java.awt.*;

import controller.Controller;
import model.Model;

// Classe de la fenêtre principale.
public class Vue extends JFrame {

	private final Controller controller;
    private final Model model;
	private boolean activerEcouteur;

	public Vue(Controller controller) {
		this.controller = controller;
	  	this.model = this.controller.getModel();

		this.activerEcouteur = true; // Les écouteurs sont activés de base.

	  	this.afficheFenetre();
		this.setLocationRelativeTo(null); // Permet de placer la fenêtre au centre.
	}

	// Fonction qui permet d'afficher tous les composants qui sont dans le package vue sur la fenêtre principale définie ici aussi.
	public void afficheFenetre() {

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Permet de fermer la fenêtre lorsque l'on clique sur la croix en haut à droite.
		this.setPreferredSize(new Dimension(800, 680)); // Taille de la fenêtre de 800 de largeur et 680 de hauteur.
		this.pack();

		JPanel contentPane = new JPanel(); // JPanel qui va contenir tout le contenu de la fenêtre, que nous appellerons le panneau principal.
		contentPane.setLayout(null);

		Jeu jeu = new Jeu(this.model); // Plateau de jeu.
		BoutonSolve boutonSolve = new BoutonSolve(this.controller); // Bouton pour résoudre l'algorithme A*.


		EcouteurSouris souris = new EcouteurSouris(this.controller);

		if(activerEcouteur) {
			jeu.addMouseListener(souris); // Permet d'ajouter l'écouteur de la souris
		}

		contentPane.add(jeu);
		contentPane.add(boutonSolve); // Ajoute les deux éléments dans le panneau principal, leur taille et situation sont définies dans leur classe.

		if(activerEcouteur) {
			new EcouteurClavier(this.controller, contentPane); // Permet d'ajouteur l'écouteur du clavier à ce composant.
		}

		this.setContentPane(contentPane); // Ajoute le panneau principal dans cette fenêtre JFrame.

		this.setVisible(true);
	}

	// Fonction qui permet d'activer ou non les écouteurs de la souris et du clavier (true active et false désactive).
	public void setActiverEcouteur(boolean b) {
		this.activerEcouteur = b;
	}
}
