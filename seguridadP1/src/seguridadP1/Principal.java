package seguridadP1;

/**Fichero: Principal.java
 * Clase para comprobar el funcionamiento de las otras clases del paquete.
 * Asignatura: SEG
 * @author Profesores de la asignatura
 * @version 1.0
 */

import java.util.Scanner;

import tipoCifrado.Asimetrico;
import tipoCifrado.Simetrico;

public class Principal {

	public static void main (String [ ] args) {
		int menu1;
		int menu2;
		Scanner sc = new Scanner(System.in);
		
		/* completar declaracion de variables e instanciación de objetos */
		// VARIABLES COMPARTIDAS
		String nombreFicheroClave = null;
		String mensaje = null;
		// SIMETRICO
		Simetrico simetrico = new Simetrico();
		String nombreFicheroCifrado;
		String nombreFicheroDescifrado;
		// ASIMETRICO
		Asimetrico asimetrico = new Asimetrico(args[0]);
		String ficheroKp = null;
		String ficheroKs = null;
		String ficheroMensaje = null;
		String ficheroFirmado = null;
		String ficheroCifrado = null;
		String ficheroDescifrado = null;
		String respuesta = null;
		
		do {
			System.out.println("¿Qué tipo de criptografía desea utilizar?");
			System.out.println("1. Simétrico.");
			System.out.println("2. Asimétrico.");
			System.out.println("3. Salir.");
			menu1 = sc.nextInt();
		
			switch(menu1){
				case 1:
					do{
						System.out.println("Elija una opción para CRIPTOGRAFIA SIMÉTRICA:");
						System.out.println("0. Volver al menú anterior.");
						System.out.println("1. Generar clave.");
						System.out.println("2. Cifrado.");
						System.out.println("3. Descifrado.");
						menu2 = sc.nextInt();
						
						switch(menu2){
							case 1:
								/*completar acciones*/
								
								// Leer nombre fichero
								System.out.println("Introduzca el nombre del fichero donde quieres guardar la clave: ");
								nombreFicheroClave = sc.next();
								
								// Generar clave
									simetrico.generarClave(args[0]+"\\"+nombreFicheroClave);
								break;
								
							case 2:
								/*completar acciones*/
								
								// Leer nombre fichero clave
								System.out.println("Introduzca el nombre del fichero que contiene la clave: ");
								nombreFicheroClave = sc.next();
								
								// Leer nombre del fichero a cifrar
								System.out.println("Introduzca el nombre del fichero que quieres cifrar: ");
								mensaje = sc.next();
								
								// Leer nombre del fichero cifrado
								System.out.println("Introduzca el nombre del fichero donde quieres guardar el mensaje cifrado: ");
								nombreFicheroCifrado = sc.next();
								
								// Cifrar
								simetrico.cifrar(args[0]+"\\"+nombreFicheroClave, args[0]+"\\"+mensaje, args[0]+"\\"+nombreFicheroCifrado);
								break;
							case 3:
								/*completar acciones*/
							break;
						}
					} while(menu2 != 0);
				break;
				case 2:
					do{
						System.out.println("Elija una opción para CRIPTOGRAFIA ASIMÉTRICA:");
						System.out.println("0. Volver al menú anterior.");
						System.out.println("1. Generar clave.");
						System.out.println("2. Cifrado.");
						System.out.println("3. Descifrado.");
						System.out.println("4. Firmar digitalmente.");
						System.out.println("5. Verificar firma digital.");
						menu2 = sc.nextInt();
				
						switch(menu2){
							case 1:
								/*completar acciones*/
								// Leer nombre fichero
								System.out.println("Introduzca el nombre del fichero donde quieres guardar la clave publica: ");
								ficheroKp = sc.next();
								System.out.println("Introduzca el nombre del fichero donde quieres guardar la clave privada: ");
								ficheroKs = sc.next();
								asimetrico.generarClaves(args[0]+"\\"+ficheroKs,args[0]+"\\"+ficheroKp);
							break;
							case 2:
								/*completar acciones*/
								System.out.println("Escribe la clave con la que quieres cifrar [privada/publica]");
								respuesta = sc.next();
								asimetrico.cifrar(args[0]+"\\"+nombreFicheroClave, args[0]+"\\"+mensaje, args[0]+"\\"+ficheroCifrado,respuesta);
							break;
							case 3:
								/*completar acciones*/
								System.out.println("Escribe la clave con la que quieres cifrar [privada/publica]");
								respuesta = sc.next();
								asimetrico.descifrar(args[0]+"\\"+nombreFicheroClave, args[0]+"\\"+mensaje, args[0]+"\\"+ficheroDescifrado,respuesta);
							break;
							case 4:
								/*completar acciones*/
								asimetrico.firmar(args[0]+"\\"+ficheroKs, args[0]+"\\"+ficheroMensaje, args[0]+"\\"+ficheroFirmado);
							break;
							case 5:
								/*completar acciones*/
								asimetrico.verificarFirma(args[0]+"\\"+ficheroKp, args[0]+"\\"+ficheroMensaje, args[0]+"\\"+ficheroFirmado);
							break;
						}
					} while(menu2 != 0);
				break;
			}			
		} while(menu1 != 3);
		sc.close();
	}
}