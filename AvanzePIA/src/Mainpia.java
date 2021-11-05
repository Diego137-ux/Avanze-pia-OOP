//PIA Mercado de abastos


//Asterisco para asignar todo lo que tiene la libreria "util"
//Se abren librerias para lectura y escritura de archivos
import java.util.*; 
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

//  MAIN
public class Mainpia { 
	//se crea el scanner para poder leer variables
    static Scanner entrada = new Scanner(System.in); 
  //Declaramos las variables globales del programa
    static String nom, numt;
    static double precio;         
    static int opcion,stock,codigo;
    
    public static void main(String[] args) {
        // se inician las clases Productos y clientes, Vector y se invocan los metodos
        Producto p = null;//
        Vector productos = new Vector(); 
        Cliente c = null;
        Vector clientes = new Vector();
        //Hacemos do-while para entrar al ciclo del menu
        do{
        	//MENU
            System.out.print("\nMENU\n 1. Registrar Producto\n 2. Registrar venta y cliente\n 3. Buscar producto\n 4. Reporte\n 5.Salir  ");
          //leemos la opcion del menu
            opcion = entrada.nextInt(); 
          //Hacemos un switch para cada caso del menu
            switch(opcion){  
            
                         //Opcion 1
                case 1:
                     System.out.print("Favor ingresar codigo de producto: ");
                   //Leemos cada variable
                     codigo = entrada.nextInt(); 
                     System.out.print("Favor ingresar nombre del producto: ");
                     nom = entrada.next();
                     System.out.print("Favor ingresar precio: ");
                     precio = entrada.nextDouble();
                     System.out.print("Favor ingresar stock: ");
                     stock = entrada.nextInt();
                   //La funcion busqueda hace que comparemos el codigo con todos los productos
                     p = busqueda(codigo, productos);                      
                     if (p==null) {
                    	//si no se encuentra el codigo se agrega un nuevo producto
                        productos.addElement(new Producto(codigo,nom,precio,stock));                         
                        System.out.print("Producto agregado");
                     }
                     else
                    	//si se encuentra digita ese comentario
                         System.out.print("ya existe este producto");                          
                    break;
                    
                        //Opcion 2
                case 2: 
                	// creamos variables auxiliares
                	String a = "s"; 
                	String op = new String();
                	int i = 0;
                    int x = 0;
                    boolean s = false;
                	double total = 0;
                	System.out.print("Favor de digitar su nombre: ");
                    nom = entrada.next();
                    System.out.print("Favor de digitar su numero telefonico: ");
                    numt = entrada.next();
                	do {
                     System.out.print("Favor ingresar codigo: ");
                     codigo = entrada.nextInt();
                   //Buscamos el codigo
                     p = busqueda(codigo, productos); 
                   //Si  se encuentra el codigo entra al if de compra
                     if(p!=null){
                    	//si el stock es mayor a 0 sige 
                         if(p.getStock() > 0){ 
                        	//ciclo para ver si la cantidad del producto que queremos comprar es menor o igual al stock exsistente
                        	 do { 
                        		 System.out.print("Cuanto producto quiere comprar: ");
                        	x = entrada.nextInt();
                        	/*Si sabemos que la cantidad es menor o 
                    		igual a la que hay en stock se suma a la variable i para saber cantidad que hemos comprado*/
                        	if(x <= p.getStock()) { 
                        		i += x;
                        		// se resta el stock
                            p.setStock(p.getStock() - x); 
                            System.out.print("Venta realizada");
                          //mostramos el resultado
                            p.mostrarResultado();  
                          //Multiplicamos la cantidad de producto que compramos y se lo sumamos al total
                            total+=(p.getPrecio())* x;               
                              s = true;
                        	}
                        	 }while(s == false);
                         }
                         else
                             System.out.print("Sin stock");
                     }
                     else {
                        System.out.print("Producto inexistente");
                	}
                  // si la respuesta es no se suman los precios para saber el total
                     System.out.print("Deseas comprar otro producto:  s/n");                       
                        op = entrada.next(); 
                   //Hacemos un .euquals para comparar el string de la variable de a.
                	}while(op.equals(a));      
                	// Se agrega un nuevo cliente
                        clientes.addElement(new Cliente(nom,numt,i,total));                        
                    System.out.print("Cliente agregado\n");
                	System.out.print("Su total es: " + total);
                    break;
                            //Opcion 3
                case 3:
                     System.out.print("Favor ingresar codigo: ");
                     codigo = entrada.nextInt();
                  // Busca el codigo
                     p = busqueda(codigo, productos);   
                  // si existe muestra los resultados
                     if(p!=null) 
                        p.mostrarResultado(); 
                     else 
                    	//si no el producto no existe                 
                        System.out.print("Producto inexistente");                        
                     break;
                            //Opcion 4
                case 4:
                	System.out.print("Reporte: ");
                	CrearArchivo(productos, clientes);
            		// Leer archivo generado.
                    LeerArchivo();
                    // Asignar lectura de archivo a un nuevo objeto.
                    break;
                    
                default: System.out.println("favor ingresar opcion de menu");
            }            
         // si es 5 se sale del ciclo
        }while(opcion!= 5); 
    }    
     
    //////////
    
            // Funcion para busqueda
 // Verificar que el codigo que digitamos exista en el objeto-producto
    static Producto busqueda(int codigo, Vector productos){        
    	boolean band = false;
    	//si return no es igual a null nos regresara el codigo
        Producto p,retornP = null; 
      //For para comparar todos los codigos del vector que digitamos
        for(int x = 0; x < productos.size(); x++){              
        	p = (Producto) productos.elementAt(x);
        	//si el codigo es el mismo regresamos el producto que encontramos
            if(p.getCodigo() == codigo)      
            	retornP = p;
        }
        return retornP;
    }
    
             //Funcion para crear archivo
    
  //Funcion de tipo void la cual le pasamos los clientes y productos
    static void CrearArchivo(Vector productos, Vector clientes){  
    	try{ //iniciamos el try y si no funciona se hace el catch
            BufferedWriter bw = new BufferedWriter(
            		//Iniciamos el archivo adentro de bw que es un objeto.
            new FileWriter("reporte.txt")              );
            //creamos un producto auxiliar
            Producto p;    
            //mientas existan productos se escribe lo siguiente en el archivo
            for(int x = 0; x < productos.size(); x++){   
            	//impresiones de nombre,codigo,stock,precio en el archivo
            	p = (Producto) productos.elementAt(x);
                bw.write("Nombre del producto: " + p.getNombre() + "\n");          
                bw.write("Codigo del producto: " + p.getCodigo() + "\n");
                bw.write("Tamaño del stock: " + p.getStock() + "\n");
                bw.write("Precio: " + p.getPrecio() + "\n");
                
               
            }
            //creamos un cliente auxiliar
            Cliente c;
            //mientas existan clientes se escribe lo siguiente en el archivo
            for(int x = 0; x < clientes.size(); x++){                 
            	c = (Cliente) clientes.elementAt(x);
            	//impresiones de nombre,telefono,cantidad y total en el archivo
                bw.write("Nombre del cliente: " + c.getname() + "\n");
                bw.write("Numero de telefono: " + c.getTelefono() + "\n"); 
                bw.write("Cantidad de compra: " + c.getCantidad() + "\n");
                bw.write("Total de compra: " + c.getTotal() + "\n");
                
               
            }
           //  Cerrar archivo al finalizar
            bw.close();
            System.out.println("¡Archivo creado con éxito!");
          //si no funciona imprime el error
        } catch(Exception ex){ 
            System.out.println(ex);
        }
    }
    
                    //Funcion para leer archivo
    static void LeerArchivo(){   
    	//iniciamos el try y si no funciona se hace el catch

        try{        
        	//se crea un objeto br con el archivo de txt
        	BufferedReader br = new BufferedReader(           
        	new FileReader("reporte.txt")
            );
        String s;
      //Mientras haya una linea de texto se imprime
            while((s = br.readLine()) != null){ 
            System.out.println(s);
            }
          //se cierra el archivo de texto
            br.close(); 
        } catch(Exception ex){
        	//si no funciona se imprime el error
            System.out.println(ex); 
        }
    }
}
