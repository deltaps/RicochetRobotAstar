package model;
import java.util.Random;

//La classe Objectif est une sous-classe de Case, elle ajoute deux variables en plus, un Numéro d'objectif et une couleur.
public class Objectif extends Case {
	//Nos deux variables.
	private int numObjectif;
	private int color;


	public Objectif(int x, int y,boolean north, boolean south, boolean east , boolean west,int numObjectif, int color){
		super(x,y,north,south,east,west);
		this.numObjectif = numObjectif;
		this.color = color;
	}

	//Nous ajoutons donc deux accesseurs getColor et getNumObjectif à notre classe. Elle va bien sûre hériter des autres accesseurs de Case.
	//----------------------------
	public int getColor(){
		return this.color;
	}

	public int getNumObjectif(){
		return this.numObjectif;
	}
	//----------------------------

	//Encore une fois nous ajoutons une méthode égale permettant de vérifier si l'objectif est identique à un autre.
	public boolean egale(Objectif obj){
		boolean murteste = true;
		for(int i = 0; i < 4; i++){
			if(this.walls[i] != obj.getWalls()[i]){
				murteste = false;
			}
		}
		return (this.x == obj.getPosition()[0]) && (this.y == obj.getPosition()[1]) && murteste && (this.numObjectif == obj.getNumObjectif()) && (this.color == obj.getColor());
	}
}
