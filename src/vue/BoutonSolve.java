package vue;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe qui va permettre d'afficher le bouton pour résoudre le problème à droite du plateau.
public class BoutonSolve extends JButton {

    private final Controller controller;

    public BoutonSolve(Controller controller) {
        this.controller = controller;

        this.setText("Solve"); // Ajoute le texte "Solve" sur le bouton.

        this.setSize(new Dimension(150,640)); // Défini la taille de ce composant à 150 de largeur et 640 de hauteur.
        this.setLocation(640,0); // Place ce composant en x = 640 à partir du haut de l'écran (y = 0).

        this.addActionListener(new ActionListener() { // Ajoute un écouteur lorsque l'on appuie sur ce composant.
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().lancerAlgoA(); // Lance l'algorithme A*.
            }
        });
    }

    public Controller getController() {
        return this.controller;
    }
}
