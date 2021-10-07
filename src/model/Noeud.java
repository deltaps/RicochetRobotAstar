package model;
import java.util.ArrayList;
// La classe noeud est utile seulement pour le fonctionnement de l'algorithme A étoile.
public class Noeud{
  // Elle contient ainsi un parent, un plateau, les valeurs cout heuristique et somme et tous les robots de sa situation.
  public Noeud parent;
  public Plateau plateau;
  public int cout;
  public int heuristique;
  public int somme;
  public Robot r1,r2,r3,r4;
  public int[] deplacement; // Cette variable permet de savoir le démarche que représente ce noeud, ainsi le tableau est constitué d'un numéro de robot, et du déplacement à effectuer.

  public Noeud(Plateau plateau, Robot r1, Robot r2, Robot r3, Robot r4){
    // On lui donne donc en argument les éléments de la partie actuelle
    this.plateau = plateau;
    this.r1 = r1;
    this.r2 = r2;
    this.r3 = r3;
    this.r4 = r4;
    // Et on initialise toutes ces informations pour l'algorithme à null ou 0.
    this.parent = null;
    this.cout = 0;
    this.heuristique = 0;
    this.somme = 0;
    this.deplacement = null;
  }
  // accesseurs ----------------------------------------------
  public Robot[] getRobot(){
    return new Robot[] {this.r1,this.r2,this.r3,this.r4};
  }
  public int getSomme(){
		return this.somme;
	}
	public void setSomme(int somme){
		this.somme = somme;
	}
	public Noeud getParent(){
		return this.parent;
	}
	public void setParent(Noeud parent){
		this.parent = parent;
	}
	public int getHeuristique(){
		return this.heuristique;
	}
	public void setHeuristique(int heuristique){
		this.heuristique = heuristique;
	}
	public int getCout(){
		return this.cout;
	}
	public void setCout(int cout){
		this.cout = cout;
	}
	public void setDeplacement(int[] set){
		this.deplacement = set;
	}
	public int[] getDeplacement(){
		return this.deplacement;
	}
  //-------------------------------------------------------------
  // Méthode pour vérifier si le noeud est égal avec un autre.
  public boolean egale(Noeud noeud){
    return noeud.getRobot()[0].egale(this.r1) && noeud.getRobot()[1].egale(this.r2) && noeud.getRobot()[2].egale(this.r3) && noeud.getRobot()[3].egale(this.r4);
  }

}
