
import javax.imageio.*;
import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.awt.image.BufferedImage;
//Libreria de Imagenes
public class Graficos {
	//Atributos
	//HashMap<K,V>, Key - Value
	//Referencia como clave el IDCasilla de la casilla y el valor es la imagen correspondiente
    private HashMap<Casilla.IDCasilla,BufferedImage> imagenes;
    //Metodos
    //Constructor
    public Graficos() {
        imagenes = new HashMap(8); //8 imagenes en total
        BufferedImage Img; //Defino un BufferedImage como Img
        try {
        //Imagen de Dona Glaciada Simple	
        Img = ImageIO.read(new File("donaGlacS.png"));
        imagenes.put(Casilla.IDCasilla.DGS, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        //Imagen de Dona Chocolate Caramelo	
        Img = ImageIO.read(new File("donaChocC.png"));
        imagenes.put(Casilla.IDCasilla.DCC, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        //Imagen de Dona Chocolate Simple	
        Img = ImageIO.read(new File("donaChocS.png"));
        imagenes.put(Casilla.IDCasilla.DCS, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        //Imagen de Dona Chocolate Doble
        Img = ImageIO.read(new File("donaChocD.png"));
        imagenes.put(Casilla.IDCasilla.DCD, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        //Imagen de Dona Glaceada Rosa
        Img = ImageIO.read(new File("donaGlacR.png"));
        imagenes.put(Casilla.IDCasilla.DGR, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        //Imagen de Dona Glaciada Caramelo
        Img = ImageIO.read(new File("donaGlacC.png"));
        imagenes.put(Casilla.IDCasilla.DGC, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        //Imagen de Dona Simple con Maple	
        Img = ImageIO.read(new File("donaSimpM.png"));
        imagenes.put(Casilla.IDCasilla.DSM, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        //Imagen casilla selector
        Img = ImageIO.read(new File("cs.png"));
        imagenes.put(Casilla.IDCasilla.SELECCION, Img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
    }
    //Metodo para obtener imagen
    BufferedImage obtenerImagen(Casilla.IDCasilla id) {
        return imagenes.get(id);
    }
}