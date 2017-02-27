import java.awt.Color;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JComponent;

//Esta clase guarda el estado del juego
//Sera extendido para soportar la animacion
//y los diversos estados del juego 

public final class Juego extends JComponent{
	//Atributos
    private Panel_Estado pnl_es;
    private Tablero juegoTablero;
    private Algoritmos_Solucion solucionador;
    private Animacion animacion;
    private Casilla seleccion;
    private boolean iniciado;
    private int puntaje; 
    public static Graficos lib_imagenes = new Graficos();
    public BufferedImage tableroImagenes;
    public ImageIcon tableroIconos;
    
    //Metodos
    //Constructor
    public Juego(Panel_Estado pe){
        try {
        	//Recibe la imagen board.png; del tablero
           tableroImagenes = ImageIO.read(new File("Tablero.png")); 
        } catch (IOException e) { System.out.println(e.getMessage());}
        this.tableroIconos = new ImageIcon(); //nueva ImagenIcon
        this.tableroIconos.setImage(this.tableroImagenes); //setear la imagen del tablero en tableroIconos
        iniciado = false; //Estado de iniciado como falso
        this.pnl_es = pe; //pe es panel estado
        this.setBackground(Color.WHITE); //Color de fondo como Blanco
        this.setPreferredSize(new Dimension(800,600)); //El tamaño de la ventana de juego
        this.addMouseListener(new InteraccionMouse(this)); //Agregar la interaccion del mouse con la ventana de juego
    }
    //Metodo para iniciar el Juego
    public void iniciarJuego(){
        juegoTablero = new Tablero(this); //nuevo tablero de juego
        solucionador = new Algoritmos_Solucion(juegoTablero,this); //nuevo solucionador
        animacion = new Animacion(this,juegoTablero,null);//nueva animacion
        // Inicializo el juego
        juegoTablero.LlenarTablero(); //esto crea la matriz por debajo, llena el tablero
        while(!solucionador.esEstable()) {
        	//Mientras el solucionador no sea estable
        	//Aplica el metodo de normalizar hasta que sea estable nuevamente
            solucionador.normaliza();
        }
        //Esto configura el estado de la informacion
        iniciado = true;
        seleccion = null;
        puntaje = 0;
        pnl_es.setearPuntaje(puntaje);
        pnl_es.setearFila(-1);
        pnl_es.setearColumna(-1);
        repaint();
    }
    //Metodo para actualizar el juego
    public void actualizarJuego() {
    	//Si el solucionador no es estable
        if (!solucionador.esEstable()) {
        	//Aplica metodos de matchsBorrados, calcularCaida
        	//setear el tipo de animacion como CASCADA
        	//Y aplica el metodo de animar cascada
            solucionador.matchsBorrados();
            solucionador.calcularCaida();
            animacion.setearTipo(Animacion.animType.CASCADA);
            animacion.animarCascada();
        }
    }
    //Metodo para limpiar el tablero de juego
    public void limpiarTablero() {
    	//Aplica caida de donas, llenas los vacios correspondientes y termina la cascada
    	//de las donas
        solucionador.aplicarCaida();
        solucionador.llenarVacios();
        solucionador.terminarCascada();
    }
    //Metodo para ganar puntaje y aplicarlo al panel de estatus
    public void ganaPuntaje(int puntos){
           this.puntaje += puntos;
           pnl_es.setearPuntaje(puntaje);
    }
    //Metodo referente al click sobre la zona del tablero del juego
    public void hagoClick(int click_x,int click_y) {
        pnl_es.setearColumna(click_x);
        pnl_es.setearFila(click_y);
        //Defino casilla clickeada, la que obtendre del tablero de juego
        Casilla clickeada = juegoTablero.obtenerCasilla(click_y, click_x);
        if (seleccion == null) {
        	//si no tenia seleccionada casilla al presionarla se vuelve clickeada
            seleccion = clickeada;
            //el estado de la casilla clickeada como seleccionado se vuelve verdadero
            clickeada.seleccionado = true;
        }
        else {
        	//si la casilla ya estaba seleccionada, y le hago click
            if (seleccion.equals(clickeada)) {
            	//el estado de la casilla clickeada como seleccionado se vuelve falso
                clickeada.seleccionado = false;
                //la casilla seleccion queda nulo (Deseleccionada)
                seleccion = null;
            }
            else {
            	//si la casilla que presiono despues de la seleccion es vecina de ésta
                if(seleccion.esVecino(clickeada)){
                	//el estado de la casilla seleccion como seleccionado se vuelve falso
                    seleccion.seleccionado = false;
                    //intercambio la casilla seleccionada con la clickeada
                    intercambiocasillas(seleccion,clickeada);
                    //seleccion vuelve a estado null (Deseleccionada)
                    seleccion = null;
                }
                else {
                	//si la casilla que presiono despues de la seleccion no es vecina de ésta
                    seleccion.seleccionado = false; //el estado de la casilla seleccion como seleccionado se vuelve falso
                    seleccion = clickeada; //la nueva casilla presionada se convierte en la clickeada  
                    clickeada.seleccionado = true; // el estado de la casilla clickeada como seleccionado se vuelve verdadero
                }
            }
        }
    }
    //Metodo de intercambio de dos casillas
    private void intercambiocasillas(Casilla C1,Casilla C2){
    	//escoge el tipo de animacion como INTERCAMBIA
        animacion.setearTipo(Animacion.animType.INTERCAMBIA);
        //animacion aplica el metodo de animarIntercambio de casillas C1 y C2 recibidas como parametro del metodo
        animacion.animarIntercambio(C1, C2);
    }
    //Metodo para dibujar las donas cuando el estado iniciado del juego sea verdadero
    public void paintComponent(Graphics g){
        this.tableroIconos.paintIcon(null, g, 0, 0);
        if(iniciado)
        dibujarDonas(g);
    }
    //Metodo para dibujarDonas de acuerdo a las casillas obtenidas del tablero de juego
    private void dibujarDonas(Graphics g){
        int f,c;//filas,columnas
        for (f=0;f<8;f++){
            for (c=0;c<8;c++){
                Casilla casilla = juegoTablero.obtenerCasilla(f, c);
                casilla.dibujar(g);
            }
        }
    }  
}
