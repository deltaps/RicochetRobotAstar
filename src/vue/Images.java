package vue;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Classe qui permet de stocker les images dans une variable, de type BufferedImage.
public class Images {

    public static BufferedImage imageCase;
    public static String DELIMITEUR_LINUX = "/";
    public static String DELIMITEUR_WINDOWS = "\\";
    public static String delimiteur;
    public static BufferedImage imageMurNord;
    public static BufferedImage imageMurSud;
    public static BufferedImage imageMurEst;
    public static BufferedImage imageMurOuest;

    public static BufferedImage robotBleu;
    public static BufferedImage robotRouge;
    public static BufferedImage robotJaune;
    public static BufferedImage robotVert;

    public static BufferedImage objectifBleu1;
    public static BufferedImage objectifBleu2;
    public static BufferedImage objectifBleu3;
    public static BufferedImage objectifBleu4;

    public static BufferedImage objectifRouge1;
    public static BufferedImage objectifRouge2;
    public static BufferedImage objectifRouge3;
    public static BufferedImage objectifRouge4;

    public static BufferedImage objectifJaune1;
    public static BufferedImage objectifJaune2;
    public static BufferedImage objectifJaune3;
    public static BufferedImage objectifJaune4;

    public static BufferedImage objectifVert1;
    public static BufferedImage objectifVert2;
    public static BufferedImage objectifVert3;
    public static BufferedImage objectifVert4;

    static {
      if (System.getProperty("os.name").equals("Windows")) {
        delimiteur = DELIMITEUR_WINDOWS;
      } else {
        delimiteur = DELIMITEUR_LINUX;
      }
        try {
            // Lis l'image (dans l'exemple de la ligne en dessous l'image de la case) dans le ficher.
            imageCase = ImageIO.read(new File("./images" + delimiteur + "imageCase.png"));

            imageMurNord = ImageIO.read(new File("./images" + delimiteur + "imageMurNord.png"));
            imageMurSud = ImageIO.read(new File("./images" + delimiteur + "imageMurSud.png"));
            imageMurEst = ImageIO.read(new File("./images" + delimiteur + "imageMurEst.png"));
            imageMurOuest = ImageIO.read(new File("./images" + delimiteur + "imageMurOuest.png"));

            robotBleu = ImageIO.read(new File("./images" + delimiteur + "robotBleu.png"));
            robotRouge = ImageIO.read(new File("./images" + delimiteur + "robotRouge.png"));
            robotJaune = ImageIO.read(new File("./images" + delimiteur + "robotJaune.png"));
            robotVert = ImageIO.read(new File("./images" + delimiteur + "robotVert.png"));

            objectifBleu1 = ImageIO.read(new File("./images" + delimiteur + "objectifBleu1.png"));
            objectifBleu2 = ImageIO.read(new File("./images" + delimiteur + "objectifBleu2.png"));
            objectifBleu3 = ImageIO.read(new File("./images" + delimiteur + "objectifBleu3.png"));
            objectifBleu4 = ImageIO.read(new File("./images" + delimiteur + "objectifBleu4.png"));

            objectifRouge1 = ImageIO.read(new File("./images" + delimiteur + "objectifRouge1.png"));
            objectifRouge2 = ImageIO.read(new File("./images" + delimiteur + "objectifRouge2.png"));
            objectifRouge3 = ImageIO.read(new File("./images" + delimiteur + "objectifRouge3.png"));
            objectifRouge4 = ImageIO.read(new File("./images" + delimiteur + "objectifRouge4.png"));

            objectifJaune1 = ImageIO.read(new File("./images" + delimiteur + "objectifJaune1.png"));
            objectifJaune2 = ImageIO.read(new File("./images" + delimiteur + "objectifJaune2.png"));
            objectifJaune3 = ImageIO.read(new File("./images" + delimiteur + "objectifJaune3.png"));
            objectifJaune4 = ImageIO.read(new File("./images" + delimiteur + "objectifJaune4.png"));

            objectifVert1 = ImageIO.read(new File("./images" + delimiteur + "objectifVert1.png"));
            objectifVert2 = ImageIO.read(new File("./images" + delimiteur + "objectifVert2.png"));
            objectifVert3 = ImageIO.read(new File("./images" + delimiteur + "objectifVert3.png"));
            objectifVert4 = ImageIO.read(new File("./images" + delimiteur + "objectifVert4.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
