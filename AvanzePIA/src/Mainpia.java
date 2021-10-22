//PIA Mercado de abastos

import java.util.*; //Asterisco para asignar todo lo que tiene la libreria "util"
import java.io.BufferedReader; //Se abren librerias para lectura y escritura de archivos
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

//  MAIN
public class Mainpia { 
    static Scanner entrada = new Scanner(System.in); //se crea el scanner para poder leer variables
    static String nom, numt;
    static double precio;         //Declaramos las variables globales del programa
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
            opcion = entrada.nextInt(); //leemos la opcion del menu
            
            switch(opcion){  //Hacemos un switch para cada caso del menu
            
                         //Opcion 1
                case 1:
                     System.out.print("Favor ingresar codigo de producto: ");
                     codigo = entrada.nextInt(); //Leemos cada variable
                     System.out.print("Favor ingresar nombre del producto: ");
                     nom = entrada.next();
                     System.out.print("Favor ingresar precio: ");
                     precio = entrada.nextDouble();
                     System.out.print("Favor ingresar stock: ");
                     stock = entrada.nextInt();
                     p = busqueda(codigo, productos); //La funcion busqueda hace que comparemos el codigo con todos los productos
                     if (p==null) {
                        productos.addElement(new Producto(codigo,nom,precio,stock)); //si no se encuentra el codigo se agrega un nuevo producto
                        System.out.print("Producto agregado");
                     }
                     else
                         System.out.print("ya existe este producto"); // si se encuentra digita ese comentario
                         
                    break;
                    
                        //Opcion 2
                case 2: 
                	String a = "s"; // creamos variables auxiliares
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
                     p = busqueda(codigo, productos); //Buscamos el codigo
                     if(p!=null){//Si  se encuentra el codigo entra al if de compra
                         if(p.getStock() > 0){ //si el stock es mayor a 0 sige 
                        	 do { //ciclo para ver si la cantidad del producto que queremos comprar es menor o igual al stock exsistente
                        	System.out.print("Cuanto producto quiere comprar: ");
                        	x = entrada.nextInt();
                        	if(x <= p.getStock()) { /*Si sabemos que la cantidad es menor o 
                        		igual a la que hay en syock se suma a la variable i para saber cantidad que hemos comprado*/
                        		i += x;
                            p.setStock(p.getStock() - x); // se resta el stock
                            System.out.print("Venta realizada");
                            p.mostrarResultado();    //mostramos el resultado
                            total+=(p.getPrecio())* x; //Multiplicamos la cantidad de producto que compramos y se lo sumamos al total
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
                     System.out.print("Deseas comprar otro producto:  s/n");  // si la respuesta es no se suman los precios para saber el total
                     op = entrada.next(); 
                     
                	}while(op.equals(a));  //Hacemos un .euquals para comparar el string de la variable de a.
                	 
                        clientes.addElement(new Cliente(nom,numt,i,total));// Se agrega un nuevo cliente
                        System.out.print("Cliente agregado\n");
                     
                	System.out.print("Su total es: " + total);
                    break;
                            //Opcion 3
                case 3:
                     System.out.print("Favor ingresar codigo: ");
                     codigo = entrada.nextInt();
                     p = busqueda(codigo, productos);   // Busca el codigo
                     if(p!=null) // si existe muestra los resultados
                        p.mostrarResultado(); 
                     else 
                        System.out.print("Producto inexistente");    //si no el producto no existe                 
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
        }while(opcion!= 5); // si es 5 se sale del ciclo
    }    
     
    //////////
    
            // Funcion para busqueda
    static Producto busqueda(int codigo, Vector productos){ // Verificar que el codigo que digitamos exista en el objeto-producto
        boolean band = false;
        Producto p,retornP = null; //si return no es igual a null nos regresara el codigo
        for(int x = 0; x < productos.size(); x++){  //For para comparar todos los codigos del vector que digitamos
            p = (Producto) productos.elementAt(x);
            if(p.getCodigo() == codigo) //si el codigo es el mismo regresamos el producto que encontramos
                retornP = p;
        }
        return retornP;
    }
    
             //Funcion para crear archivo
    static void CrearArchivo(Vector productos, Vector clientes){  //Funcion de tipo void la cual le pasamos los clientes y productos
        try{ //iniciamos el try y si no funciona se hace el catch
            BufferedWriter bw = new BufferedWriter(
            new FileWriter("reporte.txt")  //Iniciamos el archivo adentro de bw que es un objeto.
            );
            
            Producto p;    //creamos un producto auxiliar
            for(int x = 0; x < productos.size(); x++){   //mientas existan productos se escribe lo siguiente en el archivo
                p = (Producto) productos.elementAt(x);
                bw.write("Nombre del producto: " + p.getNombre() + "\n");  //impresiones de nombre,codigo,stock,precio en el archivo
                bw.write("Codigo del producto: " + p.getCodigo() + "\n");
                bw.write("Tamaño del stock: " + p.getStock() + "\n");
                bw.write("Precio: " + p.getPrecio() + "\n");
                
               
            }
            Cliente c;//creamos un cliente auxiliar
            for(int x = 0; x < clientes.size(); x++){  //mientas existan clientes se escribe lo siguiente en el archivo
                c = (Cliente) clientes.elementAt(x);
                bw.write("Nombre del cliente: " + c.getname() + "\n");
                bw.write("Numero de telefono: " + c.getTelefono() + "\n"); //impresiones de nombre,telefono,cantidad y total en el archivo
                bw.write("Cantidad de compra: " + c.getCantidad() + "\n");
                bw.write("Total de compra: " + c.getTotal() + "\n");
                
               
            }
           
            bw.close();//  Cerrar archivo al finalizar
            System.out.println("¡Archivo creado con éxito!");
        } catch(Exception ex){ //si no funciona imprime el error
            System.out.println(ex);
        }
    }
    
                    //Funcion para leer archivo
    static void LeerArchivo(){    
        try{//iniciamos el try y si no funciona se hace el catch
        BufferedReader br = new BufferedReader(//se crea un objeto br con el archivo de txt
                new FileReader("reporte.txt")
            );
        String s;
            while((s = br.readLine()) != null){ //Mientras haya una linea de texto se imprime
                System.out.println(s);
            }
            br.close(); //se cierra el archivo de tecto
        } catch(Exception ex){
            System.out.println(ex); //si no funciona se imprime el error
        }
    }
}