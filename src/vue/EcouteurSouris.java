package vue;

import controller.Controller;
import model.Model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Classe de l'écouteur de la souris, faite avec MouseAdapter qui permet d'utiliser la méthode mousePressed(Mouse Event e).
public class EcouteurSouris extends MouseAdapter{

    private final Controller controller;
    private final Model model;
    boolean deuxiemeClic=false;
    int x, y, couleurRobot;

    public EcouteurSouris(Controller controller) {
        this.controller = controller;
        this.model = controller.getModel();
    }

    // Fonction de MouseAdapter qui s'active dès qu'un clique de souris est détecté.
    @Override
    public void mousePressed(MouseEvent e) {
        int xSouris = e.getY();
        int ySouris = e.getX(); // getX() et getY() permettent d'avoir la position de la souris. Ils sont inversés ici pour correspondre au plateau du model.
        int numeroCaseColonne = xSouris / 40;
        int numeroCaseLigne = ySouris / 40; // Pour avoir le numéro de la colonne et de la ligne, on divise par 40, qui est la taille d'une case. Ces variables sont très utiles pour travailler avec les positions du model.

        int couleurClique = robotClique(numeroCaseColonne,numeroCaseLigne); // Peut prendre cinq valeurs, les quatre couleurs différentes (0 pour bleu, 1 pour rouge, 2 pour jaune et 3 pour vert) et -1 si le clique n'est pas sur un robot.

        if(deuxiemeClic) { // Détecte si il y a déjà eu un clique sur un robot, si oui l'utilisateur peut choisir une direction.
            if(x > numeroCaseColonne && y == numeroCaseLigne) { // Si le clique est en haut du premier.
                this.controller.setDirection(0); // Met la direction vers le nord.
                this.controller.deplaceRobot();
            }

            else if(x < numeroCaseColonne && y == numeroCaseLigne) { // Si le clique est en bas du premier.
                this.controller.setDirection(1); // Sud
                this.controller.deplaceRobot();
            }

            else if(x == numeroCaseColonne && y < numeroCaseLigne) { // Si le clique est à droite du premier.
                this.controller.setDirection(2); // Est
                this.controller.deplaceRobot();
            }

            else if(x == numeroCaseColonne && y > numeroCaseLigne) { // Si le clique est à gauche du premier.
                this.controller.setDirection(3); // Ouest
                this.controller.deplaceRobot();
            }

            this.controller.setCouleur(-1);
            this.controller.setDirection(-1); // Remet les valeurs à -1 lors du deuxieme clic.
            deuxiemeClic = !deuxiemeClic; // deuxiemeClic = false.
        }

        if (couleurClique != -1) {

            if(!deuxiemeClic) {
                x = numeroCaseColonne;
                y = numeroCaseLigne; // Permet de mémoriser la position du premier clic, si on clique sur un robot.
                couleurRobot = couleurClique; // Permet de mémoriser la couleur du robot cliqué.
                this.controller.setCouleur(couleurClique);
                deuxiemeClic = !deuxiemeClic; // deuxiemeClic = true. Permet à l'utilisateur de faire un deuxième clic s'il a cliqué sur un robot.
            }
        }
    }

    // Fonction qui permet de vérifier si la position correspond à celle d'un robot, et renvoie sa couleur si c'est le cas, sinon retourne -1.
    public int robotClique(int x, int y) {
        for(model.Robot rob : model.getRobot()){
            if(x == rob.getPosition()[0] && y == rob.getPosition()[1]) {
                return rob.getColor();
            }
        }
        return -1;
    }
}
