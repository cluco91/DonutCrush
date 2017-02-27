
//Lista que guarda los puntajes
public class HighScore {
	
	//Atributos
	String nombre; //El nombre del jugador
	int puntaje; //El puntaje obtenido
	HighScore sig; //Siguiente de la lista de HighScore 
	static int numeroDeCasillas = 0; //Inicializar numeroDeCasillas
	
	//Metodos
	//Constructor	
	HighScore(String n, int p)
	{
		nombre = n;
		puntaje = p;
		sig = null;
		numeroDeCasillas++;
	}
	
	//Metodo para insertar nombres con su respectivo puntaje
	public HighScore insertar(String n, int p)
	{
		//Inserta y ordena los puntajes de menor a mayor
		HighScore P = this;
		if (P == null) return new HighScore (n,p);
		if (p == P.puntaje) return P; //Así no ingresará el mismo Puntaje
		if (p < P.puntaje){ 		
			HighScore Q = new HighScore (n,p);
			Q.sig = P;
			return Q;	
		}
		if (P.sig == null)
			P.sig = new HighScore (n,p);
		else
			P.sig =P.sig.insertar(n,p);
		return P;
	}
	
	//Metodo para mostrar los nombres con sus puntajes del HighScore
	public void mostrar(HighScore H)
	{
		if (H!=null){
			System.out.println("Nombre: "+H.nombre+ " ; " + "Puntaje: "+H.puntaje);
			mostrar(H.sig);
		}
	}
}
