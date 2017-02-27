import javax.swing.JFrame;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//La clase Principal del Proyecto
public class Principal extends JFrame{	
     public static void main(String[] args){
         new Principal();
         //Lleno la lista HighScore por defecto para demostrar que funciona
         HighScore h = new HighScore("--",0);
         h.insertar("nombre1", 1500);
         h.insertar("nombre2", 250);
         h.insertar("nombre3", 400);
         h.insertar("nombre4", 3000);
         h.mostrar(h);
         //Para que se reproduzca musica de fondo
         musica("C:\\Users\\user\\workspace\\Donut_Crushv2.5\\Donut_Crush.mp3");
     }
     //Metodos
     //Constructor
     public Principal(){
    	//Ayuda a cerrar el programa, entre otras funciones
         Configuar_Ventana();
         //Configuracion del Panel de Contenido
         Container contenido = getContentPane();
         Panel_Estado pe = new Panel_Estado();
         Juego nj = new Juego(pe);
         Panel_Control pc = new Panel_Control(nj);
         //Posicion de los paneles y botonoes
         contenido.add(nj,BorderLayout.CENTER);
         contenido.add(pe,BorderLayout.WEST);
         contenido.add(pc,BorderLayout.SOUTH); 
         //-----------------------------------
         pack();
         setVisible(true);
     }
     //Metodo de Configuracion de la Ventana del Programa
     private void Configuar_Ventana(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension Tamaño_Ventana = kit.getScreenSize();
        int Pantalla_Altura = Tamaño_Ventana.height;
        int Pantalla_Ancho  = Tamaño_Ventana.width;
        setSize(Pantalla_Ancho,Pantalla_Altura);
        setLocationByPlatform(true);
        setTitle("Donut Crush v2.5"); //El titulo del juego
        setLayout(new BorderLayout());
        setDefaultCloseOperation (EXIT_ON_CLOSE);  //Para finalizar el programa 
    }
 	//Metodo del audio de fondo
     //Requiere el jar externo Jlayer1.01 para funcionar
 	public static void musica (String path){
 		try {
             FileInputStream fis;
             Player player;
             fis = new FileInputStream(path);
             BufferedInputStream bis = new BufferedInputStream(fis);
             player = new Player(bis); 
             player.play();             
         } catch (JavaLayerException e) {
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
 	}
}
