package model;
import java.util.ArrayList;
import vue.*;
//Cette class est une implémentation de l'algorithme a étoile pour notre jeu de ricochet robot.
//Son but est donc en fonction d'un model (donc d'une situation de jeu) de trouver la liste des situations menant à la victoire en faisant en sorte de bouger le moins de fois possible chaque robot.
public class AlgoA{
  //Cet algorithme fonctionne avec deux variables principales, l'openlist et la closelist.
  //L'algorithme va fonctionner avec un noeud, ce noeud contient lui-même trois variables importantes pour son bon fonctionnement, l'heuristique ( la distance en vol d'oiseau), le cout de déplacement et la somme(qui fait la somme de c'est deux variables).
  //C'est avec c'est trois variables qu'il va pouvoir bien fonctionner, elles sont toutes les trois définies dans un noeud, donc dans notre class Noeud.
  private ArrayList<Noeud> openlist; //L'openlist contient tous les noeuds à analyser
  private ArrayList<Noeud> closelist; // La closelist contient tous les noeuds déjà analysées.
  private Model situation;
  //Le constructeur prend un model en entrée, qui va donc être notre situation de départ.
  public AlgoA(Model situationDepart){
    this.openlist = new ArrayList<>();
    this.openlist.add(new Noeud(situationDepart.getPlateau(),situationDepart.getRobot().get(0).clonage(),situationDepart.getRobot().get(1).clonage(),situationDepart.getRobot().get(2).clonage(),situationDepart.getRobot().get(3).clonage())); //L'openlist est initialisé avec comme premier élément la situation de départ.
    this.closelist = new ArrayList<>(); //La closelist est initialisée comme étant vide.
    this.situation = situationDepart.clonage();
  }
  //Méthode principal contenant l'algorithme a étoile, retournant ainsi une liste de tableau d'integrale contenant respectivement le numéro d'un robot, et le déplacement a effectué.
  // En effectuant chaque mouvement donné dans cette liste, on peut terminer le jeu.
  public ArrayList<int[]> lancementAlgoA(){
    // On boucle tant que l'openlist n'est pas vide, dans le cas échéant il n'y a plus de situation à analyser, donc le jeu n'est tout simplement pas solvable.
    while(this.openlist.size() > 0){
      // On cherche le meilleur noeud que contient l'openlist, et on place ce noeud dans notre variable currentnode qui va être le noeud courant pour cette boucle.
      Noeud currentnode = this.openlist.get(0);
      for(Noeud choix : this.openlist){
        //Le meilleur noeud est donc le noeud avec la meilleure somme.
        if(choix.getSomme() < currentnode.getSomme()){
          currentnode = choix;
        }
      }
      // Le currentnode étant choisi, et étant donné qu'il va être analysé, nous le retirons de l'openlist, et nous le plaçons dans la closelist car il est désormais déjà analysé.
      this.openlist.remove(currentnode);
      this.closelist.add(currentnode);
      // On ajoute les robots dans une liste afin de ne pas les perdre lorsque nous allons chercher tous les noeuds possibles.
      ArrayList<Robot> listeRobot2 = new ArrayList<>();
      ArrayList<int[]> listePositionRobot = new ArrayList<>();
      listeRobot2.add(currentnode.getRobot()[0]);
      listePositionRobot.add(currentnode.getRobot()[0].getPosition());
      listeRobot2.add(currentnode.getRobot()[1]);
      listePositionRobot.add(currentnode.getRobot()[1].getPosition());
      listeRobot2.add(currentnode.getRobot()[2]);
      listePositionRobot.add(currentnode.getRobot()[2].getPosition());
      listeRobot2.add(currentnode.getRobot()[3]);
      listePositionRobot.add(currentnode.getRobot()[3].getPosition());
      this.situation.setRobot(listeRobot2);
      //Si jamais le noeud que l'on regarde est terminal, alors l'algorithme est fini.
      if(this.situation.isOver()){
        // Pour retourner la bonne liste d'entiers, nous prenons tous les parents du noeud courant, puis nous les plaçons dans une liste.
        // Cette liste va ensuite être inversé (car elle est à l'envers) puis on la retourne.
        ArrayList<int[]> listeFinal = new ArrayList<>();
        Noeud valeur = currentnode;
        while(valeur.getParent() != null){
          listeFinal.add(valeur.getDeplacement());
          valeur = valeur.getParent();
        }
        ArrayList<int[]> listeFinal2 = new ArrayList<>();
        for(int i = 0; i < listeFinal.size(); i++){
          listeFinal2.add(listeFinal.get(listeFinal.size() - 1 - i));
        }
        return listeFinal2;
      }

      ArrayList<Noeud> listePosibilite = new ArrayList<>();
      // Les boucles suivantes permettent d'obtenir tous les noeuds possibles en fonction de notre noeud actuel.
      // Pour cela nous prenons chaque robot puis nous le déplaçons dans chaque direction, la situation qui en découle est placée dans la liste de situation possible.
      for(int robot = 0; robot < 4; robot++){
        for(int dir = 0; dir < 4; dir++){
          ArrayList<Robot> listeRobot = new ArrayList<>();
          // La position des robots étant changée, nous les replaçons dans leur position d'origine.
          currentnode.getRobot()[0].setPosition(listePositionRobot.get(0)[0],listePositionRobot.get(0)[1]);
          listeRobot.add(currentnode.getRobot()[0]);
          currentnode.getRobot()[1].setPosition(listePositionRobot.get(1)[0],listePositionRobot.get(1)[1]);
          listeRobot.add(currentnode.getRobot()[1]);
          currentnode.getRobot()[2].setPosition(listePositionRobot.get(2)[0],listePositionRobot.get(2)[1]);
          listeRobot.add(currentnode.getRobot()[2]);
          currentnode.getRobot()[3].setPosition(listePositionRobot.get(3)[0],listePositionRobot.get(3)[1]);
          listeRobot.add(currentnode.getRobot()[3]);
          this.situation.setRobot(listeRobot);
          this.situation.deplaceRobot(currentnode.getRobot()[robot],dir);
          Noeud noeudAjout = new Noeud(this.situation.getPlateau(),this.situation.getRobot().get(0).clonage(),this.situation.getRobot().get(1).clonage(),this.situation.getRobot().get(2).clonage(),this.situation.getRobot().get(3).clonage());
          noeudAjout.setParent(currentnode);
          noeudAjout.setDeplacement(new int[] {robot,dir});
          listePosibilite.add(noeudAjout);
        }
      }
      // La position des robots étant changée, nous les replaçons dans leur position d'origine.
      currentnode.getRobot()[0].setPosition(listePositionRobot.get(0)[0],listePositionRobot.get(0)[1]);
      currentnode.getRobot()[1].setPosition(listePositionRobot.get(1)[0],listePositionRobot.get(1)[1]);
      currentnode.getRobot()[2].setPosition(listePositionRobot.get(2)[0],listePositionRobot.get(2)[1]);
      currentnode.getRobot()[3].setPosition(listePositionRobot.get(3)[0],listePositionRobot.get(3)[1]);
      //La boucle suivante permet de placer (si possible) les noeuds possibles dans notre openlist et de définir leur heuristique cout et somme.
      for(Noeud noeud : listePosibilite){
        int testcontinue = 0;
        int testcontinue2 = 0;
        // Dans un premier temps on vérifie si le noeud ne se situe pas déjà dans la closelist, auquel cas on lance la boucle suivante.
        for(Noeud n : this.closelist){
          if(n.egale(noeud)){
            testcontinue = 1;
          }
        }
        if(testcontinue == 1){
          continue;
        }
        // Si ce noeud ne se situe pas déjà dans la closelist nous lui attribuons son heuristique, son coût et ça somme.
        // L'heuristique est donc la distance en vol d'oiseau entre le robot de la même couleur que l'objectif principal, et l'objectif principal.
        noeud.setHeuristique(Math.abs((noeud.getRobot()[this.situation.getObjectifPrincipale().getColor()].getPosition()[0]) - (this.situation.getObjectifPrincipale().getPosition()[0]) ) + Math.abs((noeud.getRobot()[this.situation.getObjectifPrincipale().getColor()].getPosition()[1]) - (this.situation.getObjectifPrincipale().getPosition()[1])));
        // On incrémente le cout de 4, car pour ce jeu il faut absolument priorisé un nombre de cout maximum, ainsi l'algorithme va devoir prioriser un chemin avec un nombre de coût minimum.
        noeud.setCout(currentnode.getCout() + 4);
        // La somme est donc la somme de ces deux variables.
        noeud.setSomme(noeud.getHeuristique() + noeud.getCout());
        // Ensuite nous vérifions si le noeud se situe dans l'openlist, et si c'est le cas, si son coup est supérieur ou égal à cette situation.
        // Si c'est le cas, nous stoppons la boucle, et nous relancons une nouvelle.
        for(Noeud i : this.openlist){
          if(noeud.egale(i) && noeud.getCout() >= i.getCout()){
            testcontinue2 = 1;
          }
        }
        if(testcontinue2 == 1){
          continue;
        }
          // Enfin si tous ces tests sont passés, le noeud est ajoutée dans l'openlist, et la boucle recommence.
        this.openlist.add(noeud);
      }
    }
    // Si jamais l'openlist est vide, alors la situation actuelle n'est pas résolvable, donc nous retournons une liste vide et une erreur.
    System.out.println("Erreur Algorithme impossible");
    ArrayList<int[]> teste = new ArrayList<>();
    return teste;
  }
}
