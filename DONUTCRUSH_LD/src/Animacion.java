import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Aqui se implementan las animaciones
public class Animacion implements ActionListener {
	//Atributos
    private int frame;
    private Timer timer;
    private Casilla C1;
    private Casilla C2;
    private ArrayList<Casilla> CasillasCayendo;
    private Juego juegoPanel;
    private Tablero juegoTablero;
    private animType Tipo;
    public static enum animType {INTERCAMBIA, CASCADA};
    
    //Metodos
    //constructor
    public Animacion(Juego jp,Tablero jt,animType t){
        this.juegoTablero = jt;
        this.juegoPanel = jp;
        this.Tipo = t;
        C1 = null;
        C2 = null;
        CasillasCayendo = null;
        frame = 0;
        timer = new Timer(10,this);
    }
    //Metodo setear Tipo de animacion
    public void setearTipo(animType tipo) {
    	this.Tipo = tipo;
    }
    //Metodo para obtener el frame de la animacion
    //El frame es un fotograma, a medida que crece avanza la animacion hasta llegar
    //a un limite establecido
    public int obtenerFrame() { 
    	return frame;
    }
    //Metodo que se debe aplicar cuando termina el Intercambio
    public void terminoIntercambio(){
        timer.stop(); //La animacion de intercambio termina
        frame = 0;	  //establece el frame como 0
        juegoTablero.intercambiaCasilla(C1, C2); //intercambia las casillas
        juegoPanel.repaint(); //repinta el tablero
        juegoPanel.actualizarJuego(); //actualiza el juego
    }
    //Metodo de animar Intercambio
    public void animarIntercambio(Casilla c1, Casilla c2) {    
         this.C1 = c1; //establece la casilla C1 como c1
         this.C2 = c2; //establece la casilla C2 como c2
         timer.start(); //inicia la animacion de intercambio
    }
    //Metodo de animar Cascada
    public void animarCascada() {
        CasillasCayendo = new ArrayList(); //Se establece un nuevo ArrayList a CasillasCayendo
        juegoTablero.colectarCasillascaidas(CasillasCayendo); //CasillasCayendo se guarda en el colector
        timer.start(); //inicia la animacion de cascada   
    }
  //Metodo que se debe aplicar cuando termina la Cascada
    public void terminarCascada() {
        timer.stop(); //La animacion de cascada termina
        frame = 0;    //establece el frame como 0
        juegoPanel.limpiarTablero();  //el tablero de juego se limpia
        juegoPanel.repaint();         //repinta el tablero
        juegoPanel.actualizarJuego(); //actualiza el juego	
    }
   //Metodo del mouse
    public void actionPerformed(ActionEvent e){
    	//Si el tipo de animacion es la de INTERCAMBIA
         if (Tipo.equals(Animacion.animType.INTERCAMBIA)){
           //el frame comienza a aumentar
        	frame++;
        	//si supera los 32, el intercambio termina
           if (frame > 32) {terminoIntercambio();}
           else {
        	   //sino la direccion toma el valor de 1
               int direccion = 1;
               // si las columnas de las dos casillas son iguales
               if (C1.Columna == C2.Columna){
            	   //y ademas la fila de la casilla 1 es menor a la de la casilla 2
                   if (C1.Fila < C2.Fila) { direccion = 1;} //direccion toma el valor 1
                   else { direccion = -1;} // sino toma el valor -1
                   //Para mover las filas de las casillas correspondientes
                   C1.moverFilas(2,direccion); 
                   C2.moverFilas(2,-direccion);
               }
               else {                
            	   //En caso de que las columnas de las dos casillas no sean iguales
            	   //si la columna de la casilla 1 es menor a la de la casilla 2
                   if (C1.Columna < C2.Columna) { direccion = 1;} //direccion toma el valor 1
                   else { direccion = -1;} //sino toma el valor -1
                   //Para mover las columnas de las casillas correspondientes
                   C1.moverColumnas(2,direccion);
                   C2.moverColumnas(2, -direccion);              
               }
               juegoPanel.repaint(); //repinta el juego
           }
         }
         //Si el tipo de animacion es la de CASCADA
         else if (Tipo.equals(Animacion.animType.CASCADA)){
        	//el frame comienza a aumentar
             frame++;
           //si supera los 32, la cascada termina
             if(frame > 32) {terminarCascada();}
             else {
            	 //sino la casilla de la clase Casilla itera a CasillasCayendo (un arraylist)
                 for (Casilla casilla: CasillasCayendo){
                	 //casilla usa el metodo de mover filas, y utiliza
                	 //el doble de la distancia de la caida correspondiente 
                	 //de la casilla 
                     casilla.moverFilas(casilla.distancia_caida*2,1);
                 }
                 juegoPanel.repaint(); //repinta el juego
             }
         }
    }
}
