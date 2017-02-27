import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel; //Para el JPanel
import javax.swing.JButton; //Para el JButton
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent; //ActionEvent
import java.awt.event.ActionListener; //ActionListener

//Esta clase es para las opciones de Configuracion Principal

public class Panel_Control extends JPanel implements ActionListener {
	//Atributos
	//Definir un Juego y 4 Botones
	private Juego juego;
    private JButton btnNJ 	  = new JButton(new ImageIcon("NJ01.png"));
    private JButton btnHscr = new JButton(new ImageIcon("HSCR01.png"));
    private JButton btnSalir  = new JButton(new ImageIcon("SALIR01.png"));
    private JButton btnCred = new JButton(new ImageIcon("CRED01.png"));
    
    //Metodos
    //Constructor
    public Panel_Control(Juego j){
        this.juego = j;
        //Para agregarle color
        setBackground(Color.WHITE);
        //Boton nuevojuego
        add(btnNJ);
        btnNJ.addActionListener(this);
        btnNJ.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNJ.setRolloverIcon(new ImageIcon("NJ02.png")); 
        btnNJ.setBorderPainted(false);
        btnNJ.setContentAreaFilled(false);
        //Boton HighScore
        add(btnHscr);
        btnHscr.addActionListener(this);
        btnHscr.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHscr.setRolloverIcon(new ImageIcon("HSCR02.png"));
        btnHscr.setBorderPainted(false);
        btnHscr.setContentAreaFilled(false);
        //BotonSalir
        add(btnSalir);
        btnSalir.addActionListener(this);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setRolloverIcon(new ImageIcon("SALIR02.png")); 
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        //Boton Creditos
        add(btnCred);
        btnCred.addActionListener(this);
        btnCred.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCred.setRolloverIcon(new ImageIcon("CRED02.png")); 
        btnCred.setBorderPainted(false);
        btnCred.setContentAreaFilled(false);
    }
    //Metodo para los botones del panel de control
    //Boton Nuevo Juego
    //Boton Creditos
    //Boton Salir
    
    public void actionPerformed(ActionEvent e){
    	//Accion sobre el Boton Nuevo Juego
      if (e.getSource().equals(btnNJ)){
            juego.iniciarJuego(); 
      }   
      	//Accion sobre el boton Creditos
      if (e.getSource().equals(btnCred)){ 
    	  JOptionPane.showMessageDialog(null, "Alumno: Cristian Luco R.", "Creditos", JOptionPane.PLAIN_MESSAGE);
      }
      	//Accion sobre el boton HighScore
      if (e.getSource().equals(btnHscr)){
    	  //Muestra una Barra de Puntajes almacenados en la lista HighScore
    	  	JTextArea areaTexto = new JTextArea("Aqui deberian ir los puntajes, asociados al nombre del jugador al perder en el juego"); // para crear el cuadro de la lista
			JScrollPane PanelScroll = new JScrollPane(areaTexto);  
			areaTexto.setLineWrap(true);  
			areaTexto.setWrapStyleWord(true); 
			areaTexto.setEditable(false); // para que no se pueda editar la lista
			PanelScroll.setPreferredSize(new Dimension(400, 400));// dimension del cuadro de lista
			JOptionPane.showMessageDialog(null, PanelScroll, "HighScore", JOptionPane.PLAIN_MESSAGE);
      }
      //Accion sobre el boton Salir
      if (e.getSource().equals(btnSalir)){
    	  int confirmacion;
    	  confirmacion = JOptionPane.showConfirmDialog(null,"¿Esta seguro que quiere Salir?", "Salir", JOptionPane.YES_NO_OPTION);
    	  if(confirmacion == JOptionPane.YES_OPTION) {
    	  System.exit(0);
    	  }
      }
   }
}
