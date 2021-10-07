package model;

//########################################
// Cette classe n'est pas utile dans notre programme, nous l'avons laissé au cas où nous voulions ajouter les obstacles sur notre jeu.
//########################################

//La classe Obstacle est une sous-classe de Case, elle ajoute deux variables en plus, une couleur et une direction.
public class Obstacle extends Case{
  //Nos deux variables
  private int couleur;
  private int inclinaison;

  public Obstacle(int x, int y,boolean north, boolean south, boolean east , boolean west,int couleur, int inclinaison){
		super(x,y,north,south,east,west);
		this.couleur = couleur;
		this.inclinaison = inclinaison;
	}

  //Nous ajoutons donc deux accesseurs getColor et getInclinaison à notre classe. Elle va bien sûre hériter des autres accesseurs de Case.
  //---------------------------
  public int getColor(){
    return this.couleur;
  }

  public int getInclinaison(){
    return this.inclinaison;
  }
  //---------------------------
  //Encore une fois nous ajoutons une méthode égale permettant de vérifier si l'objectif est identique à un autre.
  public boolean egale(Obstacle obj){
		boolean murteste = true;
		for(int i = 0; i < 4; i++){
			if(this.walls[i] != obj.getWalls()[i]){
				murteste = false;
			}
		}
		return (this.x == obj.getPosition()[0]) && (this.y == obj.getPosition()[1]) && murteste && (this.inclinaison == obj.getInclinaison()) && (this.couleur == obj.getColor());
	}

}
