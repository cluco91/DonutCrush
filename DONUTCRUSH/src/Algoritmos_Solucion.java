import java.util.ArrayList;

//Esta clase tiene los algoritmos de solucion por debajo del juego
//Toda la logica requerida para normalizar y estabilizar el tablero y el juego
public class Algoritmos_Solucion {
	//Atributos
    private ArrayList<ArrayList<Casilla>> matches;
    private Juego juego;
    private Tablero juegoTablero;
    //Metodos
    //Constructor
    public Algoritmos_Solucion(Tablero jt,Juego j){
        this.juegoTablero = jt;
        this.juego = j;
        this.matches = new ArrayList();
    }
    //Metodo para verificar si el tablero de referencia es estable
    //para poder equilibrarlo
    public boolean esEstable(){
        chequearFilas();
        chequearColumnas();
        return matches.isEmpty(); //retorna verdadero cuando el arraylist esta vacio
    }
    //Metodo para normalizar
    public void normaliza() {
    	//En orden para equilibrar el tablero del juego
        matchsBorrados();
        calcularCaida();
        aplicarCaida();
        llenarVacios();
        terminarCascada();
    }
    //Metodo que elimina los matches encontrados y en consecuencia genera ganancia de puntaje      
    public void matchsBorrados(){
       int puntaje = 0; //inicializo variable entera puntaje
       for (ArrayList<Casilla> cadena: matches){ //Itera elemento de cadena para cada matches
           //Itera el ArrayList que guarda elementos de la clase Casilla denominado cadena
    	   //con el arraylist matches
           puntaje += cadena.size()*10;
         //Itera casilla de la clase Casilla con el ArrayList que guarda elementos 
          //de la clase Casilla denominado cadena
           for (Casilla casilla: cadena){ //Itera casilla para cada elemento de cadena
        	   //se le asigna al ID de la casilla el IDCasilla ELIMINADO
               casilla.ID = Casilla.IDCasilla.ELIMINADO;
           }
       }  
       matches.clear(); //vacia el arraylist matches
       juego.ganaPuntaje(puntaje); //gana puntaje
    }
    //Metodo que calcula donde las cadenas deben caer
    public void calcularCaida(){
        int f,c,temp; //fila,columna,temporal
        for (c=0;c<8;c++){
            for (f=7;f>=0;f--){ //debo obtener las casillas del fondo del tablero
            	//casilla del fondo obtiene las correspondientes del tablero de juego
                Casilla fondo = juegoTablero.obtenerCasilla(f, c);
                fondo.antes_caida = f; //asigna f al valor antes_caida de la casilla fondo
                //Si el ID de la casilla fondo equivale al IDCasilla ELIMINADO
                if(fondo.ID.equals(Casilla.IDCasilla.ELIMINADO)){
                   for(temp = f-1; temp >= 0;temp--){
                	   //Casilla superior obtiene la casilla del tablero correspondiente
                	   //recibiendo como fila el valor temporal en el recorrido
                        Casilla superior = juegoTablero.obtenerCasilla(temp, c);
                        superior.caera = true; //cambia estado de caera de la casilla superior a verdadero
                        superior.distancia_caida++; //aumenta la distancia caida de la casilla superior
                    }
                }
            }
        }
    }
    //Metodo para modificar la estructura de datos del tablero para aplicar los
    //datos reunidos desde el metodo calcularCaida
    public void aplicarCaida() {
        int f,c; //fila,columna
        for(c = 0; c < 8; ++c){
           for(f = 7; f >= 0; --f){
        	 //casilla obtiene las correspondientes del tablero de juego
             Casilla casilla = juegoTablero.obtenerCasilla(f, c);
             //si la casilla caera y el ID de la casilla equivale al IDCasilla ELIMINADO
             if(casilla.caera && !casilla.ID.equals(Casilla.IDCasilla.ELIMINADO)){
            	 //coloco la casilla en el tablero de juego recibiendo como fila
            	 //la fila correspondiente más la distancia_caida de la casilla correspondiente
                juegoTablero.colocarCasilla(f+casilla.distancia_caida, c, casilla);
                //setea Animacion de columna de la casilla
                casilla.setearAnimColumna(c);
                //coloco la casilla en el tablero de juego donde la casilla recibida como parametro
                //es una nueva casilla cuyo IDCasilla es ELIMINADO
                juegoTablero.colocarCasilla(f, c, new Casilla(Casilla.IDCasilla.ELIMINADO,f,c));
             }
           }
        }
    }
    //Metodo para modificar el tablero para llenar todas las celdas vacias
    public void llenarVacios() {
        int f,c; //fila,columna
        for (f=0;f<8;f++){
            for (c=0;c<8;c++){
            	//casilla obtiene las correspondientes del tablero de juego
                Casilla casilla = juegoTablero.obtenerCasilla(f, c);
                //si el ID de la casilla equivale al IDCasilla ELIMINADO
                if (casilla.ID.equals(Casilla.IDCasilla.ELIMINADO)){
                	//instancia rc (randomcasilla) se le asigna el valor random
                	//obtenido por el metodo randomCasilla invocado por juegoTablero
                   Casilla rc = juegoTablero.randomCasilla(f,c); //rc es casilla random
                   //colocamos la casilla correspondiente en el tablero de juego
                   juegoTablero.colocarCasilla(f, c, rc);
                }
            }
        }
    }
    //Metodo para re-establecer el estado original de la casilla despues de la animacion
    public void terminarCascada() {
        int f,c; //fila, columna
        for (f=0;f<8;f++){
            for (c=0;c<8;c++){
            	//casilla obtiene las correspondientes del tablero de juego
                Casilla casilla = juegoTablero.obtenerCasilla(f, c); 
                //el atributo antes_caida de la casilla se la asigna la columna c
                casilla.antes_caida = c;
                //la distancia_caida de la casilla queda como 0
                casilla.distancia_caida = 0;
                //el estado caera de la casilla se torna falso
                casilla.caera = false; 
            }
        }
    }
    //Metodo para chequear las Filas
    private void chequearFilas() {
        int f,c,temp; //fila,columna,temporal
        for(f = 0; f < 8; f++)
        {
          for(c= 0; c < 6; c++)
          {
        	//casilla inicio obtiene las correspondientes del tablero de juego
            Casilla inicio = juegoTablero.obtenerCasilla(f, c);
            //se crea instancia cadena de ArrayList con tamaño 5
            ArrayList cadena = new ArrayList(5);
            //se le agrega al ArrayList la casilla inicio 
            cadena.add(inicio);
            for (temp=(c+1);temp<8;temp++){
            	//casilla siguiente obtiene las correspondientes del tablero de juego
            	// a partir de la fila, y la columna como temporal
                Casilla siguiente = juegoTablero.obtenerCasilla(f, temp);
                //si el ID de la casilla siguiente equivale al ID de la casilla inicio
                if (siguiente.ID.equals(inicio.ID)){
                	//se agrega al ArrayList la casilla siguiente
                    cadena.add(siguiente);
                }
                else {
                	//sino se detiene
                    break;
                }
            }
            //si el tamaño de la cadena es mayor a 2
            if (cadena.size() > 2)
            	//entonces se agrega al ArrayList matches la cadena
                this.matches.add(cadena);
            //a la columna se le asigna la disminucion del temporal en 1
            c = temp - 1;
          }
        }
    }
    //Metodo para chequear las Columnas
    private void chequearColumnas() {
        int f,c,temp; //fila, columna, temporal
        for(c = 0; c < 8; c++)
        {
          for(f= 0; f < 6; f++)
          {
        	//casilla inicio obtiene las correspondientes del tablero de juego  
            Casilla inicio = juegoTablero.obtenerCasilla(f, c);
          //se crea instancia cadena de ArrayList con tamaño 3
            ArrayList cadena = new ArrayList(3);
          //se le agrega al ArrayList la casilla inicio 
            cadena.add(inicio);
            for (temp=(f+1);temp<8;temp++){
            	//casilla siguiente obtiene las correspondientes del tablero de juego
            	// a partir de la fila como temporal, y la columna tal cual
                Casilla siguiente = juegoTablero.obtenerCasilla(temp,c);
              //si el ID de la casilla siguiente equivale al ID de la casilla inicio
                if (siguiente.ID.equals(inicio.ID)){
                	//se agrega al ArrayList la casilla siguiente
                    cadena.add(siguiente);
                }
                else {
                	//sino se detiene
                    break;
                }
            }
            //si el tamaño de la cadena es mayor a 2
            if (cadena.size() > 2)
            	//entonces se agrega al ArrayList matches la cadena
                this.matches.add(cadena);
          //a la fila se le asigna la disminucion del temporal en 1
            f = temp - 1;
          }
        }
    }
}