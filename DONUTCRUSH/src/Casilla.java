import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
//Esta Clase tiene todo lo asociado a las Casillas que serán
//referenciadas en la matriz del juego
public class Casilla {
	
	//Atributos
    protected static enum IDCasilla { DGS,DCC,DCS,DCD,DGR,DGC,DSM,SELECCION,ELIMINADO } 
    protected IDCasilla ID;     
    protected ImageIcon icono_dona;    
    protected int Fila;
    protected int Columna;
    protected int Anima_Fila;
    protected int Anima_Columna;
    protected boolean seleccionado;
    protected boolean caera;
    protected int antes_caida;
    protected int distancia_caida;
  
    //Metodos
    //Constructor
    public Casilla(IDCasilla id,int f,int c){
           this.ID = id; //identificador
           this.Fila = f; //fila
           this.Columna = c; //columna
           //esto define como van a ser dibujadas las figuras sobre la imagen tablero
           this.Anima_Fila = f*65+40; 
           this.Anima_Columna = c*65+240;
           //variables referenciadas e inicializadas
           this.antes_caida = 0;
           this.distancia_caida = 0;
           this.caera = false;
           this.seleccionado   = false;
           //si el ID es distinto al IDCasilla ELIMINADO, entonces se le asigna un nuevo Icono de Imagen, 
           //el cual se obtiene desde la clase Graficos
           if (this.ID != IDCasilla.ELIMINADO){
               this.icono_dona = new ImageIcon();
               this.icono_dona.setImage(Juego.lib_imagenes.obtenerImagen(id)); 
           }
    }
    //Metodo  de es Vecino
    //Verifica si una casilla es vecina de otra
    public boolean esVecino(Casilla otra) {
    	//Recibe para comparar otra casilla
    	//si la resta de la fila de una casilla con la otra es 1 y las columnas 0
    	//implica que la otra casilla es vecina de la referenciada
        if (Math.abs(Fila-otra.Fila) == 1 && Math.abs(Columna-otra.Columna) == 0){
            return true;
        }
    	//si la resta de la fila de una casilla con la otra es 0 y las columnas 1
    	//implica que la otra casilla es vecina de la referenciada
        else if (Math.abs(Fila-otra.Fila) == 0 && Math.abs(Columna-otra.Columna) == 1){
            return true;
        }
        //si no ocurre ninguno de los casos anteriores, no son vecinas
        else {return false;}
    }
    //Metodo dibujar
    public void dibujar(Graphics g){
    	//Defino instancia g2 de clase Graphics2D
        Graphics2D g2 = (Graphics2D)g; //Se le aplica un cast de Graphics2D a la variable g de la clase Graphics
        if (!ID.equals(Casilla.IDCasilla.ELIMINADO)) // si el ID no equivale al IDCasilla ELIMINADO
          icono_dona.paintIcon(null, g, Anima_Columna, Anima_Fila); //El icono de la dona se pinta
        //Si esta seleccionado
        if (seleccionado){
        	//Agrega el icono del cs, es decir, casilla seleccion
            ImageIcon csIcon = new ImageIcon();
            csIcon.setImage(Juego.lib_imagenes.obtenerImagen(Casilla.IDCasilla.SELECCION));
            csIcon.paintIcon(null,g,Anima_Columna,Anima_Fila); //es para que se vea, porque se debe pintar
        }    
    }
    //Metodo de mover Filas
    public void moverFilas(int paso,int direccion){
        Anima_Fila += paso*direccion;
    }
    //Metodo de mover Columnas
    public void moverColumnas(int paso,int direccion) {
        Anima_Columna += paso*direccion;
    }
    //Metodo de setear Anima Fila
    public void setearAnimFila(int f){
        this.Fila = f;
        this.Anima_Fila = f*65+40;
    }
  //Metodo de setear Anima Columna
    public void setearAnimColumna(int c){ 
        this.Columna = c;
        this.Anima_Columna = c*65+240;
    }
}
