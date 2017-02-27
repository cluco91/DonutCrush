import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

//El tablero o matriz por debajo del juego
public final class Tablero {
	//Atributos
    private Casilla donas[][];
    public static final int DIMENSION = 8; //Debe ser 8, porque seran 64 casillas en total
    
    //Metodos
    //Constructor
    public Tablero(Juego juegoPanel){
    	//defino el arreglo de casillas denominado donas con una dimension
    	//de DIMENSION
        donas = new Casilla[DIMENSION][DIMENSION];
        //Lleno el Tablero
        LlenarTablero();
    }
    
    //Metodo para obtener Casilla
    public Casilla obtenerCasilla(int f,int c) {
        return donas[f][c]; //retorna una casilla de la matriz donas dada una fila y una columna
    }
    
    //Metodo para colocar Casilla
    public void colocarCasilla(int f,int c,Casilla casilla){
        casilla.setearAnimColumna(c); //setea la animacion de la columna de la casilla
        casilla.setearAnimFila(f);    //setea la animacion de la fila de la casilla
        donas[f][c] = casilla;        //la casilla se asigna a una posicion de la matriz donas
        //la cual recibe una fila y una columna
    }
    
    //Metodo de LlenarTablero
    //Aqui creamos la matriz
    public void LlenarTablero(){
        int f,c; //fila y columna
        for (f=0;f<DIMENSION;f++){
            for (c=0;c<DIMENSION;c++){
                donas[f][c] = randomCasilla(f,c); //la matriz se llena de forma random
            }
        }
    }
    
    //Metodo para asociar id de las casillas a algun valor del 0 al 7 generado al azar
    public Casilla randomCasilla(int f,int c){ 
        Random r = new Random();  //r es de random
        int NumCasilla = r.nextInt(7); //NumCasilla toma valores del 0 al 7 de forma random
        Casilla casilla = null; //casilla se inicializa
        switch (NumCasilla) {
        case 0:
        	//NumCasilla = 0
        	//Corresponde a la Dona Glaseada Simple
            casilla = new Casilla(Casilla.IDCasilla.DGS,f,c);
            break;
        case 1:
        	//NumCasilla = 1
        	//Corresponde a la Dona Chocolate Caramelo
            casilla = new Casilla(Casilla.IDCasilla.DCC,f,c);
            break;
        case 2:
        	//NumCasilla = 2
        	//Corresponde a la Dona Chocolate Simple
            casilla = new Casilla(Casilla.IDCasilla.DCS,f,c);
            break;
        case 3:
        	//NumCasilla = 3
        	//Corresponde a la Dona Chocolate Doble
            casilla = new Casilla(Casilla.IDCasilla.DCD,f,c);
            break;
        case 4:
        	//NumCasilla = 4
        	//Corresponde a la Dona Glaseada Rosa
            casilla = new Casilla(Casilla.IDCasilla.DGR,f,c);
            break;
        case 5: 
        	//NumCasilla = 5
        	//Corresponde a la Dona Glaseada Caramelo
            casilla = new Casilla(Casilla.IDCasilla.DGC,f,c);
            break;
        case 6:
        	//NumCasilla = 6
        	//Corresponde a la Dona Simple con Maple
            casilla = new Casilla(Casilla.IDCasilla.DSM,f,c);
            break;
        }
        return casilla; //retorna la casilla correspondiente
    }
    
    //Metodo de intercambiar Casillas
       public void intercambiaCasilla(Casilla C1,Casilla C2) {
    	//Asignar a columna1, columna2, fila1 y fila2 las columnas y filas de las
    	//casillas correspondientes
        int c1 = C1.Columna;
        int c2 = C2.Columna;
        int f1 = C1.Fila;
        int f2 = C2.Fila;
        //Estos son los de animacion
        //Setear animaciones correspondientes
        C1.setearAnimColumna(c2);
        C1.setearAnimFila(f2);
        C2.setearAnimColumna(c1);
        C2.setearAnimFila(f1);
        //Cambio
        donas[f1][c1] = C2;
        donas[f2][c2] = C1;
      }      
       //Metodo de colectar las casillas que caen
      public void colectarCasillascaidas(ArrayList<Casilla> coleccion){
          for (Casilla casillas[] : donas){ //arreglo de casillas de la clase Casilla itera a matriz donas
              for (Casilla casilla: casillas){ //casilla de la clase Casilla itera al arreglo casillas
                  if (casilla.caera && !casilla.ID.equals(Casilla.IDCasilla.ELIMINADO)) 
                	  //Si la casilla caera y el ID de la casilla no equivale a ELIMINADO
                	  //Entonces agregar al arraylist de Casilla denominado coleccion la casilla correspondiente
                      coleccion.add(casilla);
              }
          }
      }    
}
