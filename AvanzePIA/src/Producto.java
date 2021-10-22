
 public class Producto {
	 
	int c,s;  //inicializamos variables gloales
    double p;
    String n;
    
    Producto(int codigo, String nom, double precio, int stock){ //Creamos clase
        c = codigo;
        n = nom;
        p = precio;
        s = stock;
    }
    public void setStock(int stock){
        s = stock;
    }
    public int getCodigo(){  //Hacemos los returns ya que en el main los necesitaremos
        return c;
    }
    public int getStock(){
        return s;
    }
    public double getPrecio(){
        return p;
    }
    public String getNombre(){
        return n;
    }
    public void mostrarResultado(){
        System.out.println("\nNombre = " + n + "\t  Precio = "+ p + "\t  Stock = "+ s);
    }
    public void mostrarResultadoGrafico(){  //Funcion de mostrar resultados
        javax.swing.JOptionPane.showMessageDialog(null,"\nNombre = " + n + "\t  Precio = "+ p + "\t  Stock = "+ s); 
    }
}

