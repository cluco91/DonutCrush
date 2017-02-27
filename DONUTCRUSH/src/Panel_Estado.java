import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

//Clase que muestra el Status del Juego
public class Panel_Estado extends JPanel {
	//Atributos
	//JLabel, textos
    private JLabel Puntaje_Texto = new JLabel("Puntaje"); 
    private JLabel puntaje  = new JLabel("0",JLabel.CENTER);  
    private JLabel Fila_Texto = new JLabel("Fila",JLabel.CENTER);
    private JLabel fila = new JLabel("--",JLabel.CENTER); 
    private JLabel Columna_Texto = new JLabel("Columna", JLabel.CENTER); 
    private JLabel columna = new JLabel("--",JLabel.CENTER);
    
    //Metodos
    //Constructor
    public Panel_Estado(){
        setLayout(new GridLayout(6,22));
        setBackground(Color.WHITE);
        //agrego los JLabel
        add(Puntaje_Texto);
        add(puntaje);
        add(Fila_Texto);
        add(fila);
        add(Columna_Texto);
        add(columna);
    }
    //Metodo de setear puntaje
    public void setearPuntaje(int p){
        this.puntaje.setText(Integer.toString(p));
    }
    
  //Metodo de setear fila
    public void setearFila(int f){
        this.fila.setText(Integer.toString(f));
    }
  //Metodo de setear columna
    public void setearColumna(int c){
        this.columna.setText(Integer.toString(c));
    }
}
