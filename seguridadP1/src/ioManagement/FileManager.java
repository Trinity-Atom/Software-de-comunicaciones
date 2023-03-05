/**
 * 
 */
package ioManagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Salvador Fernandez
 *
 */
public class FileManager {
	
	public FileManager() {}
	
	/**
	 * METODO ESCRIBIR FICHERO
	 * Este método se encarga de escribir un Fichero
	 * @param rutaFichero 	Path del fichero que se quiere escribir
	 * @param mensaje		Array de bytes que se quiere escribir
	 * @return				Retorna true si todo ha ido bien y false si no se ha escrito correctamente
	 */
	public boolean escribirFichero(String rutaFichero,byte[] mensaje) {
		FileOutputStream salida = null;
		try {
			salida = new FileOutputStream(rutaFichero);
			salida.write(mensaje);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (salida != null)
				try {
					salida.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
		}
	}
	
	/**
	 * METODO LEER FICHERO
	 * Este método se encarga de leer el contenido de un fichero.
	 * 
	 * @param rutaFichero	Path del fichero que se quiere leer
	 * @return				Retorna el contenido del fichero (array de bytes) o null si este está vacío o se produce una excepcion.
	 */
	public byte[] leerFichero(String rutaFichero) {
		try {
			FileInputStream entrada = new FileInputStream(rutaFichero);
			byte [] contenidoFichero = entrada.readAllBytes();
			entrada.close();
			return contenidoFichero;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
