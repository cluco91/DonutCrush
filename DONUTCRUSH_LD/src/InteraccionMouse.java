import javax.swing.event.MouseInputAdapter;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
//Esta clase monitorea la entrada del usuario en el juego

public class InteraccionMouse extends MouseInputAdapter {
	//Atributos
	//Definir un nuevo juego panel
    private Juego juegoPanel;
    
    //Metodos
    //Constructor
    public InteraccionMouse(Juego jp){
    	this.juegoPanel = jp;
    }
    //Metodo del MouseClicked
    public void mouseClicked (MouseEvent e){
    	
    	//c es columna y f es fila
      int c = (e.getX()-240)/65;
      int f = (e.getY()-40)/65;
      
      //setear los limites
      if (c < 0) c = 0;
      if (c > 7) c = 7;
      if (f < 0) f = 0;
      if (f > 7) f = 7;
      
      //agregado para cambiar el cursor
      juegoPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
      //la instancia juegoPanel usa metodo hagoClick de la clase Juego
      juegoPanel.hagoClick(c,f);
      //hace repaint
      juegoPanel.repaint();
    }
}
