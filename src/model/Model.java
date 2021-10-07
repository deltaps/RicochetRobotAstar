package model;
import java.util.Random;
import java.util.ArrayList;

//La class Model va avoir pour rôle de créer une partie de jeu Ricochet Robot, et surtout de faire des méthodes permettant à l'utilisateur de jouer, associant ainsi toutes nos classes (sauf la classe AlgoA).
public class Model {
	// Variables qu'une partie de ricochet robot doit avoir
	private Plateau plateau;
	private ArrayList<Robot> listeRobot;
	private Objectif objectifPrincipale;

	//Constructeur
	public Model() {
		this.plateau = new Plateau(); //On crée un plateau puis on le construit
		this.plateau.buildPlateau();
		this.listeRobot = new ArrayList<>(); //Initialisation de notre liste de Robots
		//Initialisation des quatre robots, et placement dans la liste de robots.
		for(int nbRobot = 0; nbRobot < 4; nbRobot++){
			Robot roboto = new Robot(nbRobot);
			this.listeRobot.add(roboto);
		}
		//Placement aléatoire de chaque robot sur le terrain a l'aide de la méthode setRobot de cette classe (expliquer dans la méthode elle-même).
		for(Robot teste : listeRobot){
			this.setRobot(teste);
		}
		//Choix aléatoire de notre objectif principal, qui est donc l'objectif à atteindre.
		Random rand = new Random();
		int x = rand.nextInt(this.plateau.getListObjectif().size());
		this.objectifPrincipale = this.plateau.getListObjectif().get(x);
	}
	// Tous les accesseurs de notre class Model.
	//----------------------------------------------------------------
	public Plateau getPlateau(){
		return this.plateau;
	}
	public void setPlateau(Plateau plateau){
		this.plateau = plateau;
	}
	public Objectif getObjectifPrincipale(){
		return this.objectifPrincipale;
	}
	public void setObjectifPrincipal(Objectif objectifPrincipale){
		this.objectifPrincipale = objectifPrincipale;
	}
	public ArrayList<Robot> getRobot(){
		return this.listeRobot;
	}
	// Il existe deux accesseurs setRobot, l'un pour modifier la liste entière de robot, l'autre pour placer un robot sur le terrain de jeu
	public void setRobot(ArrayList<Robot> listeRobot){
		this.listeRobot = listeRobot;
	}
	public void setRobot(Robot robot){
		// On choisit aléatoirement un emplacement où on pourrait placer le robot.
		Random rand = new Random();
    int x = rand.nextInt(13) + 1;
		int y = rand.nextInt(13) + 1;
		int[] positionTotal = {x,y};
		// On initialise aussi une liste contenant la position de tous les robots présents sur le terrain afin de ne pas les empiler.
		int[][] listePositionRobot = {this.listeRobot.get(0).getPosition(),this.listeRobot.get(1).getPosition(),this.listeRobot.get(2).getPosition(),this.listeRobot.get(3).getPosition()};
		// On vérifie donc si la position choisie aléatoirement est une position ou le robot peut être placé, et tant qu'elle n'est pas correcte, on en rechoisit une autre.
		while(x >= 6 && x <= 9 || y >= 6 && y <= 9 || this.plateau.getCase(x,y) instanceof Obstacle || this.plateau.getCase(x,y) instanceof Objectif || listePositionRobot[0][0] == x && listePositionRobot[0][1] == y || listePositionRobot[1][0] == x && listePositionRobot[1][1] == y || listePositionRobot[2][0] == x && listePositionRobot[2][1] == y || listePositionRobot[3][0] == x && listePositionRobot[3][1] == y){
			x = rand.nextInt(13) + 1;
			y = rand.nextInt(13) + 1;
    }
		// Au final on place le robot a la position choisie.
    robot.setPosition(x,y);
  }
	// Méthode principals

	// La méthode deplaceRobot permet de déplacer un robot en fonction d'une direction, pour cela elle utilise une fonction auxiliaire.
	public void deplaceRobot(Robot robot,int dir){
		int[] position = testDeplaceRobot(robot,dir);// On prend la position ou le robot doit se déplacer,
		robot.setPosition(position[0],position[1]);// puis on le place à cette position.
	}
	// La fonction auxiliaire testDeplaceRobot permet de donner la position à laquelle le robot doit aller en fonction d'une direction donnée
	public int[] testDeplaceRobot(Robot rob, int dir){
    int[] CurrentCase = {rob.getPosition()[0],rob.getPosition()[1]};// On initialise CurrentCase à la position de départ du robot.
		// Si jamais il-y-a un mur sur la case du robot dans la direction donnée, alors le robot ne peut pas bouger, donc on renvoie la position de départ.
		if(this.plateau.getCase(rob.getPosition()[0],rob.getPosition()[1]).getWalls()[dir]){
        return CurrentCase;
    }
    else{
				//On initialise la variable dir2 en fonction de la variable d'entrée dir. dir2 est la direction en x et y dans laquelle le robot doit se déplacer.
				int[] dir2 = null;
        if(dir == 0){
					dir2 = new int[] {-1,0};
				}
        if(dir == 1){
					dir2 = new int[] {1,0};
				}
        if(dir == 2){
					dir2 = new int[] {0,1};
				}
        if(dir == 3){
					dir2 = new int[] {0,-1};
				}
				// Nous bouclons à l'infinie tant que le robot n'arrive pas à une case où il doit se stopper, auquel cas on renvoie cette position.
        while(true){
					// On vérifie s'il n'y a pas de robot dans la prochaine case, auquel cas on renvoie la position actuelle.
					for(Robot robot : this.listeRobot ){
            if(((this.plateau.getCase(CurrentCase[0]+dir2[0],CurrentCase[1]+dir2[1])).getPosition())[0] == (robot.getPosition())[0] && ((this.plateau.getCase(CurrentCase[0]+dir2[0],CurrentCase[1]+dir2[1])).getPosition())[1] == (robot.getPosition())[1]){
              return CurrentCase;
            }
          }
					// On vérifie s'il n'y a pas de mur dans la direction de la prochaine case, auquel cas on renvoie la position prochaine du robot.
          if(this.plateau.getCase(CurrentCase[0]+dir2[0],CurrentCase[1]+dir2[1]).getWalls()[dir]){
              return new int[] {CurrentCase[0]+dir2[0],CurrentCase[1]+dir2[1]};
          }
					/*
					if(this.plateau.getCase(CurrentCase[0],CurrentCase[1]) instanceof Obstacle){
						if(((Obstacle)(this.plateau.getCase(CurrentCase[0],CurrentCase[1]))).getColor() != rob.getColor()){
							if(((Obstacle)(this.plateau.getCase(CurrentCase[0],CurrentCase[1]))).getInclinaison() == 0){
								switch(dir){
						      case 0:
						        dir2 = new int[] {0,-1};
										dir = 3;
						        break;
						      case 1:
										dir2 = new int[] {0,1};
										dir = 2;
										break;
							    case 2:
										dir2 = new int[] {1,0};
										dir = 1;
										break;
									case 3:
										dir2 = new int[] {-1,0};
										dir = 0;
										break;
						    }
							}
							else{
								switch(dir){
						      case 0:
						        dir2 = new int[] {0,1};
										dir = 2;
						        break;
						      case 1:
										dir2 = new int[] {0,-1};
										dir = 3;
										break;
							    case 2:
										dir2 = new int[] {-1,0};
										dir = 0;
										break;
									case 3:
										dir2 = new int[] {1,0};
										dir = 1;
										break;
						    }
							}
						}
					}
					*/
					// À la fin de la boucle, on incrémente à la CurrentCase la position de la case future.
					CurrentCase = new int[] {CurrentCase[0]+dir2[0],CurrentCase[1]+dir2[1]};
      	}
    }
	}
	//La méthode isOver permet de déterminer si la partie est finie ou non, si oui elle renvoie true, sinon elle renvoie false.
	public boolean isOver(){
		// On boucle sur tous les robots présents sur le terrain,
		for(Robot i : this.listeRobot){
			// et on regarde si le robot de la bonne couleur se situe sur l'objectif principal, auquel cas le jeu est terminé et nous renvoyons true.
			if(i.getColor() == this.objectifPrincipale.getColor() && i.getPosition()[0] == this.objectifPrincipale.getPosition()[0] && i.getPosition()[1] == this.objectifPrincipale.getPosition()[1]){
				System.out.println("Partie terminé");
				return true;
			}
		}
		// Si le bon robot ne se situe pas sur l'objectif principal alors on retourne false.
		return false;
	}
	// La méthode clonage permet d'effectuer une copie profonde du Model.
	public Model clonage(){
		Model copie = new Model(); // On commence par créer un nouveau model, qui sera notre copie.
		ArrayList<Robot> listeRobot = new ArrayList<>(); // On initialise une listeRobot qui sera une copie de la liste de robots de ce model.
		// Pour cela on boucle sur tous les robots présents sur le terrain, puis on ajoute à notre copie de listeRobot un nouveau robot avec les mêmes valeurs que celui du model.
		for(Robot i : this.listeRobot){
			listeRobot.add(new Robot(i.getPosition()[0],i.getPosition()[1],i.getColor()));
		}
		copie.setRobot(listeRobot); // on ajoute donc cette liste à notre copie de model.
		// On fait aussi une copie de l'objectif principal du model, puis on l'ajoute à la copie.
		Objectif copieObjectifPrincipal = new Objectif(this.objectifPrincipale.getPosition()[0], this.objectifPrincipale.getPosition()[1],this.objectifPrincipale.getWalls()[0], this.objectifPrincipale.getWalls()[1], this.objectifPrincipale.getWalls()[2], this.objectifPrincipale.getWalls()[3],this.objectifPrincipale.getNumObjectif(),this.objectifPrincipale.getColor());
		copie.setObjectifPrincipal(copieObjectifPrincipal);
		// À l'aide de notre méthode de clonage présent dans notre class Plateau, on ajoute à notre copie une copie profonde du plateau de notre model.
		copie.setPlateau(this.plateau.clonage());
		return copie;
	}
	// La méthode égale permet de vérifier si le model est égal à un autre.
	public boolean egale(Model model){
		boolean robegal = true;
		// On boucle sur chaque robot possible, et on regarde si tous ces attributs sont les mêmes, si ce n'est pas le cas pour un seul d'entre eux, alors la variable robegal prend la valeur false.
		for(int rob = 0; rob < 4; rob++){
			if(this.listeRobot.get(rob).getPosition()[0] != model.getRobot().get(rob).getPosition()[0]){
				robegal = false;
			}
			else if(this.listeRobot.get(rob).getPosition()[1] != model.getRobot().get(rob).getPosition()[1]){
				robegal = false;
			}
			else if(this.listeRobot.get(rob).getColor() != model.getRobot().get(rob).getColor()){
				robegal = false;
			}
		}
		// À l'aide de toutes nos méthodes egale définies dans nos classes, ont vérifie si le plateau et l'objectifPrincipale sont les mêmes que le model donner en argument,
		// si c'est le cas alors les méthode retourne true, et si toutes nos variables sont égales à true alors les deux model sont égal.
		return robegal && (this.plateau.egale(model.getPlateau())) && (this.objectifPrincipale.egale(model.getObjectifPrincipale()));
	}
}
