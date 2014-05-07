package engine;
import java.util.Vector;

public class Objeto {

	private Vector<Elemento> almacenamiento = new Vector<Elemento>();
	
	public Objeto (Vector<Elemento> entrada){
		this.setAlmacenamiento(entrada);
	}

	public Vector<Elemento> getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(Vector<Elemento> almacenamiento) {
		this.almacenamiento = almacenamiento;
	}
		
}
