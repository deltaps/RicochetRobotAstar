package model;

public class Case {
	//Variables static définie afin de bien représenter le nord sud est et ouest, utile pour le placement des murs
	//---------------------------------
	protected final int NORTH = 0;
	protected final int SOUTH = 1;
	protected final int EAST = 2;
	protected final int WEST = 3;
	//---------------------------------
	//Variable de position en x et y d'une case
	protected int x;
	protected int y;
	//Tableau de booléen permettant d'ajouter à la case ces murs respectifs, ainsi si un mur est présent dans n'importe quelle direction il aura la valeur true.
	protected boolean[] walls;
	//Constructeur
	public Case(int x, int y, boolean north, boolean south, boolean east , boolean west){
		this.x = x;
		this.y = y;
	  this.walls = new boolean[] {north ,south ,east ,west};
	}
	//Deux accesseurs permettant d'accéder à la position et au mur de la case.
	//-----------------------------
	public int[] getPosition(){
		return new int[] {x,y};
	}
	public boolean[] getWalls(){
		return this.walls;
	}
	//-----------------------------
	//Méthode permettant de changer les murs d'une case (effectuer avec un ou logique puisque nous utilisons des booléens).
	public void addWalls(boolean north, boolean south, boolean east , boolean west){
		this.walls[NORTH] = north || this.walls[NORTH];
		this.walls[SOUTH] = south || this.walls[SOUTH];
		this.walls[EAST] = east || this.walls[EAST];
		this.walls[WEST] = west|| this.walls[WEST];
	}
	//Méthode permettant de vérifier si la case est égale à une autre (donner en argument).
	public boolean egale(Case obj){
		boolean murteste = true;
		//Nous faisons une boucle 4 fois afin de vérifier si chaque mur est positionné au même endroit.
		for(int i = 0; i < 4; i++){
			if(this.walls[i] != obj.getWalls()[i]){
				murteste = false;
			}
		}
		//En faisant un et logique entre chaque élément de notre case, nous regardons si tout ces différents attributs sont exactements identiques.
		return (this.x == obj.getPosition()[0]) && (this.y == obj.getPosition()[1]) && murteste;
	}

}
