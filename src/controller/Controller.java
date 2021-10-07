package controller;

import model.Model;
import model.AlgoA;
import vue.Vue;

import java.util.ArrayList;

public class Controller {

    private Model model;
    private final Vue vue;
    private int couleur;
    private int direction;
    private ArrayList<int[]> algo;

    public Controller(Model model) throws InterruptedException {
        this.model = model;
        this.vue = new Vue(this);

        this.couleur = -1;
        this.direction = -1; // La couleur du robot à déplacer et la direction sont mis à la valeur -1 de base.

        this.algo = new ArrayList<>(); // Variable permettant de stocker le résultat de l'algorithme A*, est une liste vide de base

        affichageAlgoA();
    }

    // Toutes les fonctions ci-dessous servent principalement pour les écouteurs, placés dans le package vue.

    // Accesseur permettant d'avoir accès au model.
    public Model getModel() {
        return this.model;
    }

    // Modificateur permettant de modifier le model.
    public void setModel(Model model) {
        this.model = model;
    }

    //Fonction pour lancer l'algorithme A*, déclenchée lorsque l'on appuie sur BoutonSolve. le résultat est stocké dans this.algo et est une liste de tableau de couleur et de direction.
    public void lancerAlgoA() {
        AlgoA algo = new AlgoA(getModel());
        this.algo = algo.lancementAlgoA();
    }

    // Fonction permettant de détecter si la variable contient les résultats de l'algorithme, afin de l'afficher. C'est une boucle infinie.
    public void affichageAlgoA() throws InterruptedException {
        while(true) {
            if(this.algo.size() != 0)  {
                this.vue.setActiverEcouteur(false); // Désactive les écouteurs, pour ne pas modifier le model le temps de l'affichage du résultat.
                for (int[] deplacement : this.algo) { // deplacement est un tuple d'entier, avec deplacement[0] est la couleur du robot à bouger et deplacement [1] est la direction.
                    Thread.sleep(2000); // Permet d'attendre 2000 millisecondes (2 secondes), pour voir toutes les étapes qu'a fait l'algorithme.
                    this.setCouleur(deplacement[0]);
                    this.setDirection(deplacement[1]);
                    this.deplaceRobot();
                }
                this.algo.clear(); // Vide la liste pour éviter de reboucler.
                this.vue.setActiverEcouteur(true); // Réactive les écouteurs.
                this.vue.afficheFenetre(); // Actualise la fenêtre en remettant les écouteurs.
            }
            Thread.sleep(1000);
        }
    }

    // Fonction qui sert à déplacer le robot, grâce aux écouteurs du clavier et de la souris.
    public void deplaceRobot() {
        if(this.couleur != -1 && this.direction != -1) { // Vérifie qu'une couleur de robot et qu'une direction sont données.
            this.model.deplaceRobot(this.model.getRobot().get(this.couleur), this.direction); // Appelle de fonction deplaceRobot de model, qui déplace le robot de couleur this.couleur dans la direction this.direction.
            this.setCouleur(-1);
            this.setDirection(-1); // Remise des variables à -1.

            this.vue.afficheFenetre(); // Permet de mettre à jour la vue.
        }
    }

    // Modificateur de la couleur d'un robot, servant pour le déplacement de celui-ci.
    public void setCouleur(int c) {
        this.couleur = c;
    }

    // Modificateur de la direction dans laquelle bouger un robot.
    public void setDirection(int dir) {
        this.direction = dir;
    }
}
