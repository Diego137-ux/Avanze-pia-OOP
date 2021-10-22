
public class Cliente {
	
	private String n;
	private String t;  //inicializamos  variables globales
	private int c;
	private double to;
	
	Cliente(){
		
	}
	Cliente( String name, String telefono, int cantidad, double total){ //creamos clase
		
	    n = name;
		t = telefono; 
		c = cantidad;
		to = total;
		
	}
	
	
	public String getname() {
		return n;
	}
	public String getTelefono() {
		return t;
	}
	
	public int getCantidad() {
		return c;
	}
	
	
	public double getTotal() {
		return to;
	}
	
	
}

	


