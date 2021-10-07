package model;
import java.util.Random;

public class Robot {
  //Variables static définies afin de bien représenter la couleur d'un robot, permet de rendre clair le code.
  //---------------------------------
  public final static int BLEU = 0;
	public final static int ROUGE = 1;
	public final static int JAUNE = 2;
	public final static int VERT = 3;
  //---------------------------------
  //Trois variables primordiales pour un robot, sa position x et y, et sa couleur.
  private int color;
  private int x;
  private int y;
  //Nous faisons deux constructeurs, un pour définir directement la position d'un robot, l'autre simplement pour l'initialiser.
  public Robot(int x,int y,int color){
    this.x = x;
    this.y = y;
    this.color = color;
  }
  public Robot(int color){
    this.x = 0;
    this.y = 0;
    this.color = color;
  }
  //Deux accesseurs pour obtenir la position et la couleur de notre robot.
  //-------------------------------------
  public int[] getPosition(){
    return new int[] {this.x,this.y};
  }
  public int getColor(){
    return this.color;
  }
  //-------------------------------------
  //Méthode permettant de placer le robot.
  public void setPosition(int x, int y){
    this.x = x;
    this.y = y;
  }
  public boolean egale(Robot robot){
    return this.color == robot.getColor() && this.x == robot.getPosition()[0] && this.y == robot.getPosition()[1];
  }
  public Robot clonage(){
    return new Robot(this.x,this.y,this.color);
  }



}
