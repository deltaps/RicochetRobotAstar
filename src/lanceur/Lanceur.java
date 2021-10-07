package lanceur;

import controller.Controller;
import model.*;
import java.util.ArrayList;

public class Lanceur {
	public static void main(String[] args) throws InterruptedException {
		Model model = new Model();
		Controller controller = new Controller(model);
		/*
		int[] test1 = new int[] {4,5};
		int[] test2 = new int[] {4,5};

		System.out.println(test1.equals(test2));
		Model jeu = new Model();
		Vue vue = new Vue(jeu);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Jouer avec l'algo a star? (1 oui 2 non)");
		int choixalgo = scanner.nextInt();
		while(!jeu.isOver()){
			ArrayList<Robot> robots = jeu.getRobot();
			System.out.println("Affichage du plateau de jeu");
			System.out.println("-------------------------------------");
			for(int x = 0; x < 16; x++){
				for(int y = 0; y < 16; y++){
					Case caseActuelle = jeu.getPlateau().getCase(x,y);
					boolean testrobpasse = true;
					for(Robot robot : robots){
						if(robot.getPosition()[0] == x && robot.getPosition()[1] == y){
							if(robot.getColor() == 0){
								System.out.print(" B ");
							}
							else if(robot.getColor() == 1){
								System.out.print(" R ");
							}
							else if(robot.getColor() == 2){
								System.out.print(" J ");
							}
							else{
								System.out.print(" V ");
							}
							testrobpasse = false;
						}
					}
					if(testrobpasse){
						if(caseActuelle instanceof Objectif){
							System.out.print(" O ");
						}
						else if(caseActuelle instanceof Obstacle){
							System.out.print(" S ");
						}
						else if(caseActuelle instanceof Case){
							System.out.print(" I ");
						}
						else{
							System.out.print(" WOW ");
						}
					}
					boolean[] mur = caseActuelle.getWalls();
					if(mur[0]){

					}
					if(mur[1]){

					}
					if(mur[2]){

					}
					if(mur[3]){

					}
				}
				System.out.println("");
			}
			System.out.println("-------------------------------------");
			if(choixalgo == 1){
				AlgoA algo = new AlgoA(jeu);
				ArrayList<Model> algochoix = algo.lancementAlgoA();
				System.out.println("Wala le tableau de choix de l'algo : " + algochoix);
			}
			System.out.println("Quelles robots veux tu déplacer? (0 = bleu, 1 = rouge, 2 = jaune, 3 = vert )");
			int couleur = scanner.nextInt();
			System.out.println("Quel Déplacmene t UT veux FaaIre? (0 = haut, 1= bas, 2 = droit, 3 = gauche )");
			int deplacement = scanner.nextInt();

			jeu.deplaceRobot(robots.get(couleur),deplacement);
			vue.afficheFenetre();
		}

		 */
	}
}
