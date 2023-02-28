/**
 * 
 */
package tipoCifrado;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.PaddedBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

/**
 * CLASE SIMETRICO
 * Implementa todos los métodos necesarios para generar clave, cifrar y descifrar con algoritmo Twofish simétrico.
 * 
 * @author Salvador Fdez
 * @author Ivan Perez
 */
public class Simetrico {
	// VARIABLES DE CLAVE
	int keySize = 256; 	// Tamaño de la clave en bits
	// VARIABLES DEL CIFRADO
	int blockSize = 16; 	// 16 bytes == 256 bits
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public Simetrico () {}
	
	/**
	 * METODO GENERAR CLAVE
	 * Sirve para generar la clave twofish de tamaño 256
	 * 
	 * @param rutaFicheroClave Nombre del fichero en el que se guarda la clave
	 * @return String en el que se devuelve la clave en formato hexadecimal
	 * @throws FileNotFoundException,IOException 
	 * @throws Exception 
	 * @throws IOException
	 */
	public boolean generarClave(String rutaFicheroClave){
		
			// 1. Crear objeto generador
			CipherKeyGenerator	keyGen = new CipherKeyGenerator();
			// 2. Inicializar objeto generador
			KeyGenerationParameters param = new KeyGenerationParameters(new SecureRandom(), keySize);
			keyGen.init(param);
			// 3. Generar clave
			byte[] key = keyGen.generateKey();
			// 4. Convertir clave a hexadecimal
			byte[] hexKey = Hex.encode(key);
			// 5. Almacenar clave en fichero
			escribirFichero(rutaFicheroClave,hexKey);
			return true;
	}

	public String cifrar(String rutaFicheroClave, String rutaMensaje,String rutaFicheroCifrado){
		
		// 1. Leer clave y decodificar Hex a bin
		byte[] hexKey = leerFichero(rutaFicheroClave);
		byte[] key = Hex.decode(hexKey);
		
		// 2. Generar parámetros y cargar la clave
		KeyParameter param = new KeyParameter(key);
		
		
		// 3. Crear motor de cifrado
		PaddedBufferedBlockCipher pbc = new PaddedBufferedBlockCipher(new TwofishEngine(), new PKCS7Padding());
		
		// 4. Iniciar motor de cifrado con params
		pbc.init(true,param);
		
		// 5. Crear flujos E/S ficheros
		BufferedInputStream filtroOrigen;
		int leidos;
		try {
			filtroOrigen = new BufferedInputStream(new FileInputStream(rutaMensaje));
			byte[] almacen = new byte[blockSize];
			do {
				leidos = filtroOrigen.readNBytes(almacen, 0, blockSize);
			} while(leidos != -1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String descifrar(String mensajeCifrado,String clave){
		String mensajeEnClaro="";
		return mensajeEnClaro;
	}
	
	/**
	 * METODO ESCRIBIR FICHERO
	 * Este método se encarga de escribir un Fichero
	 * @param rutaFichero 	Path del fichero que se quiere escribir
	 * @param mensaje		Array de bytes que se quiere escribir
	 * @return				Retorna true si todo ha ido bien y false si no se ha escrito correctamente
	 */
	private boolean escribirFichero(String rutaFichero,byte[] mensaje) {
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
	private byte[] leerFichero(String rutaFichero) {
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
