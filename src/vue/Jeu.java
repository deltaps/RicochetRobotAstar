package vue;

import javax.swing.*;
import java.awt.*;
import model.Model;

// Classe qui va permettre d'afficher le plateau de jeu.
public class Jeu extends JPanel {

    private final Model model;

    public Jeu(Model model) {
        this.setSize(new Dimension(640,640)); // Défini la taille de ce composant à 640 de largeur et 640 de hauteur.
        this.setLocation(0,0); // Place ce composant en haut à gauche de l'écran.
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) { // Fonction appelée lorsque l'on exécute la méthode setVisible(true), sert à afficher tous les éléments grâce au Grapics g qui est défini automatiquement.
        super.paintComponent(g);
        for(int x = 0; x < 16; x++) {
          for (int y = 0; y < 16; y++) { // Deux boucles allant de 0 à 15 car le plateau de jeu fait 16x16 en taille (256 cases).
              g.drawImage(Images.imageCase, x * 40, y * 40, null); // Affiche les cases une à une. Le 40 de x * 40 et y * 40 est la taille de l'image. Cela place donc une image tous les 40 pixels.

              afficheMur(x,y,g,this.model);
          }
        }
        afficheObjectif(g, this.model);
        afficheObjectifPrincipal(g, this.model);
        afficheRobot(g, this.model);
       }

    // Fonction pour afficher les murs
    public static void afficheMur(int x, int y, Graphics g, Model model) {

        boolean[] walls = model.getPlateau().getCase(y,x).getWalls(); // Inversion du x et du y pour un bon rendu graphique.
        x = x*40;
        y = y*40;

        if (walls[0]) { // Si il y a un mur au nord de cette case, cette fonction affiche ce mur.
            g.drawImage(Images.imageMurNord, x, y, null);
        }

        if (walls[1]) { //Sud
            g.drawImage(Images.imageMurSud, x, y, null);
        }

        if (walls[2]) { //Est
            g.drawImage(Images.imageMurEst, x, y, null);
        }

        if (walls[3]) { //Ouest
            g.drawImage(Images.imageMurOuest, x, y, null);
        }
    }

    // Fonction qui affiche tous les objectif du plateau.
    public static void afficheObjectif(Graphics g, Model model) {

        int bleu; // Variable permettant d'obtenier la bonne image d'objectif (il y a quatre objectifs bleus).
        int rouge;
        int jaune;
        int vert;

        for(int i = 0; i < model.getPlateau().getListObjectif().size(); i++) {

            if(model.getPlateau().getListObjectif().get(i).getColor() == 0) { // Si l'objectif est bleu
                bleu = model.getPlateau().getListObjectif().get(i).getNumObjectif();
                if(bleu == 0) { //sert pour afficher différentes images d'objectif de même couleur
                    g.drawImage(Images.objectifBleu1, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(bleu == 1) {
                    g.drawImage(Images.objectifBleu2, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(bleu == 2) {
                    g.drawImage(Images.objectifBleu3, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(bleu == 3) {
                    g.drawImage(Images.objectifBleu4, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

            }


            if(model.getPlateau().getListObjectif().get(i).getColor() == 1) { // Si l'objectif est rouge
                rouge = model.getPlateau().getListObjectif().get(i).getNumObjectif();
                if(rouge == 0) { //sert pour afficher différentes images d'objectif de même couleur
                    g.drawImage(Images.objectifRouge1, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(rouge == 1) {
                    g.drawImage(Images.objectifRouge2, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(rouge == 2) {
                    g.drawImage(Images.objectifRouge3, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(rouge == 3) {
                    g.drawImage(Images.objectifRouge4, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

            }

            if(model.getPlateau().getListObjectif().get(i).getColor() == 2) { // Si l'objectif est jaune
                jaune = model.getPlateau().getListObjectif().get(i).getNumObjectif();
                if(jaune == 0) { //sert pour afficher différentes images d'objectif de même couleur
                    g.drawImage(Images.objectifJaune1, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(jaune == 1) {
                    g.drawImage(Images.objectifJaune2, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(jaune == 2) {
                    g.drawImage(Images.objectifJaune3, model.getPlateau().getListObjectif().get(i).getPosition()[1] * 40, model.getPlateau().getListObjectif().get(i).getPosition()[0] * 40, null);
                }

                if(jaune == 3) {
                    g.drawImage(Images.objectifJaune4, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

            }

            if(model.getPlateau().getListObjectif().get(i).getColor() == 3) { // Si l'objectif est vert
                vert = model.getPlateau().getListObjectif().get(i).getNumObjectif();
                if(vert == 0) { //sert pour afficher différentes images d'objectif de même couleur
                    g.drawImage(Images.objectifVert1, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(vert == 1) {
                    g.drawImage(Images.objectifVert2, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(vert == 2) {
                    g.drawImage(Images.objectifVert3, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }

                if(vert == 3) {
                    g.drawImage(Images.objectifVert4, model.getPlateau().getListObjectif().get(i).getPosition()[1]*40, model.getPlateau().getListObjectif().get(i).getPosition()[0]*40, null);
                }
            }
        }
    }

    // Fonction qui affiche l'objectif principal défini dans le model au centre du plateau de jeu.
    public static void afficheObjectifPrincipal(Graphics g, Model model) {

        int couleurObjectifPrincipal = model.getObjectifPrincipale().getColor();
        int numObjectifPrincipal = model.getObjectifPrincipale().getNumObjectif();

        if(couleurObjectifPrincipal == 0) { // On vérifie si l'objectif principal est bleu. Cette vérification est répétée quatre fois pour les quatre couleurs différentes.

            if(numObjectifPrincipal == 0) { // On attribue la bonne image à l'objectif principal. Cette vérification est répétée quatre fois pour els quatre objectifs différents de cette même couleur.
                g.drawImage(Images.objectifBleu1, 300,300,null);
            }
            if(numObjectifPrincipal == 1) {
                g.drawImage(Images.objectifBleu2, 300,300,null);
            }
            if(numObjectifPrincipal == 2) {
                g.drawImage(Images.objectifBleu3, 300,300,null);
            }
            if(numObjectifPrincipal == 3) {
                g.drawImage(Images.objectifBleu4, 300,300,null);
            }

        }

        else if(couleurObjectifPrincipal == 1) { // Rouge

            if(numObjectifPrincipal == 0) { // On attribue la bonne image à l'objectif principal. Cette vérification est répétée quatre fois pour els quatre objectifs différents de cette même couleur.
                g.drawImage(Images.objectifRouge1, 300,300,null);
            }
            if(numObjectifPrincipal == 1) {
                g.drawImage(Images.objectifRouge2, 300,300,null);
            }
            if(numObjectifPrincipal == 2) {
                g.drawImage(Images.objectifRouge3, 300,300,null);
            }
            if(numObjectifPrincipal == 3) {
                g.drawImage(Images.objectifRouge4, 300,300,null);
            }
        }

        else if(couleurObjectifPrincipal == 2) { // Jaune

            if(numObjectifPrincipal == 0) { // On attribue la bonne image à l'objectif principal. Cette vérification est répétée quatre fois pour els quatre objectifs différents de cette même couleur.
                g.drawImage(Images.objectifJaune1, 300,300,null);
            }
            if(numObjectifPrincipal == 1) {
                g.drawImage(Images.objectifJaune2, 300,300,null);
            }
            if(numObjectifPrincipal == 2) {
                g.drawImage(Images.objectifJaune3, 300,300,null);
            }
            if(numObjectifPrincipal == 3) {
                g.drawImage(Images.objectifJaune4, 300,300,null);
            }
        }

        else if(couleurObjectifPrincipal == 3) { // Vert

            if(numObjectifPrincipal == 0) { // On attribue la bonne image à l'objectif principal. Cette vérification est répétée quatre fois pour els quatre objectifs différents de cette même couleur.
                g.drawImage(Images.objectifVert1, 300,300,null);
            }
            if(numObjectifPrincipal == 1) {
                g.drawImage(Images.objectifVert2, 300,300,null);
            }
            if(numObjectifPrincipal == 2) {
                g.drawImage(Images.objectifVert3, 300,300,null);
            }
            if(numObjectifPrincipal == 3) {
                g.drawImage(Images.objectifVert4, 300,300,null);
            }
        }
    }

/* Fonction qui allait permettre d'afficher les obstacles. Celle-ci n'est pas finie car nous n'avons finalement pas implémenté les obstacles dans le model.

    public static void afficheObstacle(Graphics g, Model model) {

        for(int i = 0; i < model.getPlateau().getListObstacle().size(); i++) {

            if(model.getPlateau().getListObstacle().get(i).getColor() == 0) { // si l'obstacle est bleu

                g.setColor(Color.blue);

                if (model.getPlateau().getListObstacle().get(i).getInclinaison() == 0) {
                    g.fillRect(model.getPlateau().getListObstacle().get(i).getPosition()[0]+20, model.getPlateau().getListObstacle().get(i).getPosition()[1], 10, 50);
                }

                else {
                    g.fillRect(model.getPlateau().getListObstacle().get(i).getPosition()[0]+20, model.getPlateau().getListObstacle().get(i).getPosition()[1], 10, 50);
                }
            }
        }
    }
*/

    // Fonction qui affiche les quatre robots de couleur différentes, placés aléatoirement sur le plateau.
    public static void afficheRobot(Graphics g, Model model) {

        for(model.Robot rob : model.getRobot()){
          if(rob.getColor() == 0){ // Vérifie la couleur du robot de la liste et l'affiche. Cette vérification est répétée quatre fois pour les quatre couleurs différentes.
            g.drawImage(Images.robotBleu, rob.getPosition()[1]*40, rob.getPosition()[0]*40, null);
          }
          else if(rob.getColor() == 1){
            g.drawImage(Images.robotRouge, rob.getPosition()[1]*40, rob.getPosition()[0]*40, null);
          }
          else if(rob.getColor() == 2){
            g.drawImage(Images.robotJaune, rob.getPosition()[1]*40, rob.getPosition()[0]*40, null);
          }
          else if(rob.getColor() == 3){
            g.drawImage(Images.robotVert, rob.getPosition()[1]*40, rob.getPosition()[0]*40, null);
          }
        }
    }
}
