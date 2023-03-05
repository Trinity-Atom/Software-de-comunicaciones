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
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import ioManagement.FileManager;

/**
 * CLASE SIMETRICO
 * Implementa todos los métodos necesarios para generar clave, cifrar y descifrar con algoritmo Twofish simétrico.
 * 
 * @author Salvador Fdez
 * @author Ivan Perez
 */
public class Simetrico {
	FileManager manager;
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
	public Simetrico () {
		manager = new FileManager();
	}
	
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
			manager.escribirFichero(rutaFicheroClave,hexKey);
			return true;
	}

	public String cifrar(String rutaFicheroClave, String rutaMensaje,String rutaFicheroCifrado){
		
		// 1. Leer clave y decodificar Hex a bin
		byte[] hexKey = manager.leerFichero(rutaFicheroClave);
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
}
