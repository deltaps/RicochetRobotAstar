package model;
import java.util.Random;
import java.util.ArrayList;

public class Plateau{
  //Définitions de nos trois variables.
  private Case[][] terrain; //Un tableau de Case permettant de représenter un terrain de jeu.
  private ArrayList<Objectif> listeObjectif; //Une liste d'objectif contenant tous les objectifs d'un terrain
  private ArrayList<Obstacle> listeObstacle; //Une liste d'obstacle contenant tous les obstacles d'un terrain
  //Constructeur
  public Plateau(){
    this.terrain = new Case[16][16]; //Initialisation de la variable terrain.
    listeObjectif = new ArrayList<>(); //Initialisation de la liste d'objectif.
    for(int i = 0; i < 16; i++){
      listeObjectif.add(null); //On sait qu'un terrain possède seulement 16 objectifs, 4 par couleurs, pour le moment on les initialise à null.
    }
    listeObstacle = new ArrayList<>(); //Idem pour notre liste d'obstacle, sauf que dans ce cas, il n'y a que 8 obstacles, 2 par couleurs.
    for(int i = 0; i < 8 ; i++){
      listeObstacle.add(null);
    }
  }
  //Tous les accesseurs de la classe.
  //---------------------------------------------------------------------------------
  public Case getCase(int x, int y){
    return this.terrain[x][y];
  }
  public void setCase(Case insert){
    this.terrain[insert.getPosition()[0]][insert.getPosition()[1]] = insert;
  }
  public ArrayList<Objectif> getListObjectif(){
    return this.listeObjectif;
  }
  public void setListObjectif(ArrayList<Objectif> listeObjectif){
    this.listeObjectif = listeObjectif;
  }
  public ArrayList<Obstacle> getListObstacle(){
    return this.listeObstacle;
  }
  public void setListObstacle(ArrayList<Obstacle> listeObstacle){
    this.listeObstacle = listeObstacle;
  }
  //---------------------------------------------------------------------------------
  //Méthode permettant de faire une copie profonde de la classe plateau.
  public Plateau clonage(){
    Plateau clone = new Plateau(); //On crée un nouveau plateau qui va être une copie conforme du plateau actuel.
    ArrayList<Objectif> listeObjectif = new ArrayList<>(); //On initialise la liste des objectifs et des obstacles qui vont eux aussi être une copie conforme du plateau actuel.
    ArrayList<Obstacle> listeObstacle = new ArrayList<>();
    for(int x = 0; x < 16; x++){ // On boucle sur toutes les cases du terrain actuel pour les copier.
      for(int y = 0; y < 16; y++){
        if(this.getCase(x,y) instanceof Objectif){ // Si la case qu'on regarde est un objectif alors:
          //On crée un nouvel objectif avec exactement les mêmes attributs que l'objectif se trouvant sur le terrain à copier.
          Objectif copie = new Objectif(x, y,this.getCase(x,y).getWalls()[0],this.getCase(x,y).getWalls()[1],this.getCase(x,y).getWalls()[2],this.getCase(x,y).getWalls()[3],((Objectif)this.getCase(x,y)).getNumObjectif(),((Objectif)this.getCase(x,y)).getColor());
          listeObjectif.add(copie); // On ajoute cet objectif dans notre liste d'objectif initialisée précédemment.
          clone.terrain[x][y] = copie; // et nous l'ajoutons aussi sur la copie.
        }
        else if (this.getCase(x,y) instanceof Obstacle){ //Si la case qu'on regarde est un obstacle, alors nous faisons la même chose qu'avec un objectif, mais pour un obstacle.
          Obstacle copie = new Obstacle(x,y,this.getCase(x,y).getWalls()[0],this.getCase(x,y).getWalls()[1],this.getCase(x,y).getWalls()[2],this.getCase(x,y).getWalls()[3],((Obstacle)this.getCase(x,y)).getColor(),((Obstacle)this.getCase(x,y)).getInclinaison());
          listeObstacle.add(copie);
          clone.terrain[x][y] = copie;
        }
        else{ // Enfin s'il ne s'agit ni d'un objectif ni d'un obstacle, c'est donc une case.
          // On crée donc une nouvelle case avec les mêmes attributs que la case se trouvant au même endroit.
          Case copie = new Case(x,y,this.getCase(x,y).getWalls()[0],this.getCase(x,y).getWalls()[1],this.getCase(x,y).getWalls()[2],this.getCase(x,y).getWalls()[3]);
          clone.terrain[x][y] = copie; // et on l'ajoute sur la copie.
        }
      }
    }
    // À la fin de la boucle, on ajoute notre liste d'objectifs et d'obstacle à notre copie
    clone.setListObjectif(listeObjectif);
    clone.setListObstacle(listeObstacle);
    return clone;
  }
  //La méthode egale permet de regarder si le plateau est égal avec un autre plateau donné en argument.
  public boolean egale(Plateau plat){
    boolean terrainegal = true;
    //On commence par regarder pour chaque case du terrain si elle est égale à la case du plateau donnée, pour cela nous faisons une boucle sur toutes les cases d'un terrain, et on utilise la méthode egale de notre classe case (méthode expliquée dans cette classe)
    for(int x = 0; x < 16; x++){
      for(int y = 0; y < 16; y++){
        if(!(this.terrain[x][y].egale(plat.getCase(x,y)))){
          terrainegal = false;
        }
      }
    }
    boolean objectifegal = true;
    boolean objectifegaleaux = false;
    // On vérifie aussi si notre liste d'objectif est identique, pour cela on commence tout simplement par regarder si elle fait la même taille
    if(this.listeObjectif.size() == plat.getListObjectif().size()){
      // Ensuite on regarde pour chaque élément d'une liste, si au moins un de ces éléments est identique au premier de l'autre liste, puis au deuxième etc.
      for(int i = 0;i<this.listeObjectif.size();i++){
        for(int j = 0; j < this.listeObjectif.size();j++){
          if(this.listeObjectif.get(i).egale(plat.getListObjectif().get(j))){
            objectifegaleaux = true;
          }
        }
        // Si jamais la variable objectifegaleaux est a false, alors aucun des éléments d'une liste ne correspond à un élément de l'autre, donc elle n'est forcément pas identique.
        if(!(objectifegaleaux)){
          objectifegal = false;
          break; // C'est inutile de continuer la boucle, donc on l'a stop;
        }
      }
    }
    else{
      objectifegal = false;
    }
    //On fait la même chose que pour la liste d'objectifs, mais cette fois-ci avec la liste d'obstacle
    boolean obstacleegal = true;
    boolean obstacleegalaux = false;
    if(this.listeObstacle.size() == plat.getListObstacle().size()){
      for(int i = 0;i<this.listeObstacle.size();i++){
        for(int j = 0; j < this.listeObstacle.size();j++){
          if(this.listeObstacle.get(i).egale(plat.getListObstacle().get(j))){
            obstacleegalaux = true;
          }
        }
        if(!(obstacleegalaux)){
          obstacleegal = false;
          break;
        }
      }
    }
    else{
      obstacleegal = false;
    }
    // Au final on retourne le "et logique" entre chaque test de toutes nos valeurs d'un plateau, si l'un n'est pas true alors le plateau n'est pas identique.
    return terrainegal && objectifegal /*&& obstacleegal*/;
  }

  // Cette méthode primordiale est lancée à chaque création de partie de jeu permet de générer un plateau dont l'emplacement des murs, des objectifs et des obstacles sont prédéfinis.
  // Il est bien sûr possible de faire une autre méthode permettant de créer un autre plateau de jeu.
  public void buildPlateau(){
    // On commence par initialiser toutes les cases avec seulement les bords du plateau comportant les murs.
    for(int x = 0; x < 16; x ++){
        for(int y = 0; y < 16; y++){
            this.terrain[x][y] = new Case(x,y,false,false,false,false);


            // Les bords / coins du plateau
            if(x == 0){
              this.terrain[x][y].addWalls(true,false,false,false);
            }
            if(x == 15){
              this.terrain[x][y].addWalls(false,true,false,false);
            }
            if(y == 0){
              this.terrain[x][y].addWalls(false,false,false,true);
            }
            if(y == 15){
              this.terrain[x][y].addWalls(false,false,true,false);
            }
          }
        }


        // Ajout des bords du carré central du Plateau
        this.terrain[6][7].addWalls(false,true,false,false);
        this.terrain[6][8].addWalls(false,true,false,false);
        this.terrain[7][6].addWalls(false,false,true,false);
        this.terrain[7][9].addWalls(false,false,false,true);
        this.terrain[8][6].addWalls(false,false,true,false);
        this.terrain[8][9].addWalls(false,false,false,true);
        this.terrain[9][7].addWalls(true,false,false,false);
        this.terrain[9][8].addWalls(true,false,false,false);

        //bord-nord
        this.terrain[0][5].addWalls(true,false,true,false);
        this.terrain[0][6].addWalls(true,false,false,true);
        this.terrain[0][8].addWalls(true,false,true,false);
        this.terrain[0][9].addWalls(true,false,false,true);

        //bord-west
        this.terrain[5][0].addWalls(false,true,false,true);
        this.terrain[6][0].addWalls(true,false,false,true);
        this.terrain[12][0].addWalls(false,true,false,true);
        this.terrain[13][0].addWalls(true,false,false,true);

        //bord-est
        this.terrain[4][15].addWalls(false,true,true,false);
        this.terrain[5][15].addWalls(true,false,true,false);
        this.terrain[12][15].addWalls(false,true,true,false);
        this.terrain[13][15].addWalls(true,false,true,false);

        //bord-sud
        this.terrain[15][6].addWalls(false,true,true,false);
        this.terrain[15][7].addWalls(false,true,false,true);
        this.terrain[15][10].addWalls(false,true,true,false);
        this.terrain[15][11].addWalls(false,true,false,true);

        // Les murs des objectifs

        // Les murs de l'objectif en position 1 10
        this.terrain[1][10].addWalls(false,true,false,true);
        this.terrain[1][9].addWalls(false,false,true,false);
        this.terrain[2][10].addWalls(true,false,false,false);
        // Les murs de l'objectif en position 2 12
        this.terrain[2][12].addWalls(false,true,true,false);
        this.terrain[2][13].addWalls(false,false,false,true);
        // Les murs de l'objectif en position 3 1
        this.terrain[3][1].addWalls(true,false,false,true);
        this.terrain[2][1].addWalls(false,true,false,false);
        this.terrain[3][0].addWalls(false,false,true,false);
        // Les murs de l'objectif en position 3 12
        this.terrain[3][12].addWalls(true,false,false,true);
        this.terrain[3][11].addWalls(false,false,true,false);
        // Les murs de l'objectif en position 4 6
        this.terrain[4][6].addWalls(false,true,true,false);
        this.terrain[5][6].addWalls(true,false,false,false);
        this.terrain[4][7].addWalls(false,false,false,true);
        // Les murs de l'objectif en position 6 2
        this.terrain[6][2].addWalls(true,false,true,false);
        this.terrain[5][2].addWalls(false,true,false,false);
        // Les murs de l'objectif en position 6 3
        this.terrain[6][3].addWalls(false,true,false,true);
        this.terrain[7][3].addWalls(true,false,false,false);
        // Les murs de l'objectif en position 6 13
        this.terrain[6][13].addWalls(true,false,true,false);
        this.terrain[5][13].addWalls(false,true,false,false);
        this.terrain[6][14].addWalls(false,false,false,true);
        // Les murs de l'objectif en position 9 2
        this.terrain[9][2].addWalls(false,true,false,true);
        this.terrain[9][1].addWalls(false,false,true,false);
        // Les murs de l'objectif en position 9 10
        this.terrain[9][10].addWalls(false,true,true,false);
        this.terrain[10][10].addWalls(true,false,false,false);
        this.terrain[9][11].addWalls(false,false,false,true);
        // Les murs de l'objectif en position 10 2
        this.terrain[10][2].addWalls(true,false,true,false);
        this.terrain[10][3].addWalls(false,false,false,true);
        // Les murs de l'objectif en position 11 7
        this.terrain[11][7].addWalls(true,false,false,true);
        this.terrain[10][7].addWalls(false,true,false,false);
        this.terrain[11][6].addWalls(false,false,true,false);
        // Les murs de l'objectif en position 11 12
        this.terrain[11][12].addWalls(true,false,true,false);
        this.terrain[10][12].addWalls(false,true,false,false);
        // Les murs de l'objectif en position 11 13
        this.terrain[11][13].addWalls(false,true,false,true);
        this.terrain[12][13].addWalls(true,false,false,false);
        // Les murs de l'objectif en position 13 9
        this.terrain[13][9].addWalls(true,false,false,true);
        this.terrain[12][9].addWalls(false,true,false,false);
        this.terrain[13][8].addWalls(false,false,true,false);
        // Les murs de l'objectif en position 14 5
        this.terrain[14][5].addWalls(false,true,true,false);
        this.terrain[15][5].addWalls(true,false,false,false);
        this.terrain[14][6].addWalls(false,false,false,true);

        //Diposition des Objectifs

        Random random = new Random(); //Nous allons placer la couleur et le numéro des objectifs aléatoirement, nous utilisons donc la bibliothèque Random de Java.
        ArrayList<int[]> positionObjectif = new ArrayList<>();
        // La liste ci-dessous définit la position de chaque objectif.
        int[][] positionObjectifAux = {{1,10},{2,12},{3,1},{3,12},{4,6},{6,2},{6,3},{6,13},{9,2},{9,10},{10,2},{11,7},{11,12},{11,13},{13,9},{14,5}};
        for(int[] i : positionObjectifAux){
          positionObjectif.add(i);
        }
        // Nous bouclons sur tous les objectifs possibles, donc les quatre couleurs, et les quatre numéros d'objectif.
        for(int couleurObjectif = 0; couleurObjectif < 4; couleurObjectif++){
          for(int numeroObjectif = 0; numeroObjectif < 4; numeroObjectif++){
            int nb = random.nextInt(positionObjectif.size()); // On définit aléatoirement l'objectif à prendre dans la liste
            // On crée donc notre objectif, il aura ainsi une couleur et un numéro aléatoire.
            Objectif placement = new Objectif(positionObjectif.get(nb)[0],positionObjectif.get(nb)[1],this.terrain[positionObjectif.get(nb)[0]][positionObjectif.get(nb)[1]].getWalls()[0],this.terrain[positionObjectif.get(nb)[0]][positionObjectif.get(nb)[1]].getWalls()[1], this.terrain[positionObjectif.get(nb)[0]][positionObjectif.get(nb)[1]].getWalls()[2], this.terrain[positionObjectif.get(nb)[0]][positionObjectif.get(nb)[1]].getWalls()[3],numeroObjectif,couleurObjectif);
            this.terrain[placement.getPosition()[0]][placement.getPosition()[1]] = placement; // On place l'objectif sur le terrain
            positionObjectif.remove(nb); // L'objectif numéro nb étant déjà positionné, nous le supprimons de la liste pour ne pas le replacer
            this.listeObjectif.set(couleurObjectif * 4 + numeroObjectif,placement); // On place l'objectif dans la liste d'objectifs du plateau
          }
        }

        //Diposition des Obstacle
        // Le principe de fonctionnement est le même que pour la disposition des objectifs.
        /*
        ArrayList<int[]> positionObstacle = new ArrayList<>();
        int[][] positionObstacleAux = {{13,1,0},{1,4,1},{8,4,1},{7,5,0},{3,9,1},{12,9,0},{14,11,0},{2,14,1}};
        for(int[] i : positionObstacleAux){
          positionObstacle.add(i);
        }
        for(int couleurObstacle = 0; couleurObstacle < 4; couleurObstacle++){
          for(int range = 0; range < 2; range++){
            int nb = random.nextInt(positionObstacle.size());
            int directionObstacle = positionObstacle.get(nb)[2];
            Obstacle placement = new Obstacle(positionObstacle.get(nb)[0],positionObstacle.get(nb)[1],this.terrain[positionObstacle.get(nb)[0]][positionObstacle.get(nb)[1]].getWalls()[0],this.terrain[positionObstacle.get(nb)[0]][positionObstacle.get(nb)[1]].getWalls()[1],this.terrain[positionObstacle.get(nb)[0]][positionObstacle.get(nb)[1]].getWalls()[2],this.terrain[positionObstacle.get(nb)[0]][positionObstacle.get(nb)[1]].getWalls()[3],couleurObstacle,directionObstacle);
            this.terrain[placement.getPosition()[0]][placement.getPosition()[1]] = placement;
            positionObstacle.remove(nb);
            this.listeObstacle.set(range + 2 * couleurObstacle,placement);
          }
        }
        */
    }
}
