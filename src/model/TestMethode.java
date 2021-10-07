package model;
import java.util.Random;
import java.util.ArrayList;


public class TestMethode {
	private Model model;
	private Robot roboto;
	private Objectif objectifPrincipale;
	private Plateau terrain;
	public TestMethode() {
		this.model = new Model();
		this.terrain = new Plateau();
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				this.terrain.setCase(new Case(i,j,false,false,false,false));
			}
		}
		this.terrain.getCase(0,5).addWalls(true,false,true,false);
    this.terrain.getCase(0,6).addWalls(true,false,false,true);
    this.terrain.getCase(0,8).addWalls(true,false,true,false);
    this.terrain.getCase(0,9).addWalls(true,false,false,true);
		this.terrain.getCase(5,0).addWalls(false,true,false,true);
    this.terrain.getCase(6,0).addWalls(true,false,false,true);
    this.terrain.getCase(12,0).addWalls(false,true,false,true);
    this.terrain.getCase(13,0).addWalls(true,false,false,true);
		this.terrain.getCase(4,15).addWalls(false,true,true,false);
    this.terrain.getCase(5,15).addWalls(true,false,true,false);
    this.terrain.getCase(12,15).addWalls(false,true,true,false);
    this.terrain.getCase(13,15).addWalls(true,false,true,false);
		this.terrain.getCase(15,6).addWalls(false,true,true,false);
    this.terrain.getCase(15,7).addWalls(false,true,false,true);
    this.terrain.getCase(15,10).addWalls(false,true,true,false);
    this.terrain.getCase(15,11).addWalls(false,true,false,true);
		this.terrain.getCase(1,10).addWalls(true,false,true,false);

		this.model.setPlateau(this.terrain);
		this.objectifPrincipale = new Objectif(1,10,true,false,true,false,0,0);
		this.model.setObjectifPrincipal(objectifPrincipale);
		Robot roboto1 = this.model.getRobot().get(0);
		Robot roboto2 = this.model.getRobot().get(1);
		Robot roboto3 = this.model.getRobot().get(2);
		Robot roboto4 = this.model.getRobot().get(3);
		roboto1.setPosition(1,1);
		roboto2.setPosition(9,10);
		roboto3.setPosition(0,0);
		roboto4.setPosition(15,15);
		this.roboto = roboto1;
		this.model.deplaceRobot(roboto1,2);
		boolean	test = this.roboto.getPosition()[0] == 1 && this.roboto.getPosition()[1] == 10;
		System.out.println("Deplace Robot vers mur " + test);
		System.out.println("isOver bien " + this.model.isOver());
		this.model.deplaceRobot(roboto1,1);
		test = this.roboto.getPosition()[0] == 8 && this.roboto.getPosition()[1] == 10;
		System.out.println("Deplace Robot dans Robot " + test);
	}
}
