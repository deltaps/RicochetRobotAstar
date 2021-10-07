package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Classe de l'écouteur du clavier, avec le système de Key Binding qui associe une fonction à une touche du clavier. Cette fonction est utile uniquement pour définir la direction d'un robot.
public class EcouteurClavier {

    private final Controller controller;
    private final JComponent component;
    private static final int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;

    public EcouteurClavier(Controller controller, JComponent component) {
        this.controller = controller;
        this.component = component;

        InputMap inputMap = this.component.getInputMap(condition); // Permet de récupérer les InputMap du composant component (ici un JPanel). Les InputMap permettent d'associer des variables à des touches du clavier. La variable condition est un entier qui permet de faire en sorte que les fonctions activés par ce système sont mises en marche seulement si cette fenêtre est ciblée par l'utilisateur.
        ActionMap actionMap = this.component.getActionMap(); // Permet de récupérer les ActionMap du composant component (ici un JPanel). Les ActionMap permettent d'associer une fonction à une variable définie par les InputMap.

        String arrowU = "arrowU";
        inputMap.put(KeyStroke.getKeyStroke("UP"), arrowU); // Permet d'associer à la variable arrowU la touche flèche du haut du clavier, définie avec KeyStroke.getKeyStroke("UP").
        String arrowD = "arrowD";
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), arrowD); // Flèche du bas
        String arrowR = "arrowR";
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), arrowR); // Flèche de droite
        String arrowL = "arrowL";
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), arrowL); // Flèche de gauche

        actionMap.put(arrowU, new Action(this.controller, 0)); // Permet de lancer une action si la touche flèche du haut du clavier est détectée.
        actionMap.put(arrowD, new Action(this.controller, 1));
        actionMap.put(arrowR, new Action(this.controller, 2));
        actionMap.put(arrowL, new Action(this.controller, 3));
    }

    private static class Action extends AbstractAction {
        Controller controller;
        int direction;

        Action(Controller controller, int direction) {
            this.controller = controller;
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.controller.setDirection(this.direction);
            this.controller.deplaceRobot(); // Déplace le robot sélectionné de la direction this.direction.
            this.controller.setDirection(-1);
        }
    }
}
