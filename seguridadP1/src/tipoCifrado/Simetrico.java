/**
 * 
 */
package tipoCifrado;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.util.encoders.Hex;

/**
 * @author Salvador
 *
 */
public class Simetrico {
	// Variables del algoritmo
	int keySize = 256; 	// Tamaño de la clave en bits
	
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
	public boolean generarClave(String rutaFicheroClave) throws FileNotFoundException,IOException{
		
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
			escribirFichero(rutaFicheroClave,key);
			return true;
	}
	private boolean escribirFichero(String rutaFichero,byte[] mensaje) {
		FileOutputStream salida= null;
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

	public String cifrar(String rutaFicheroClave, String rutaMensaje,String rutaFicheroCifrado){
		
		// Motor cifrado simétrico
		
		//PaddedBufferedBlockCipher = new PaddedBlockCipher(new TwofishEngine(), new PKCS7Padding())
		return null;
	}
	public String descifrar(String mensajeCifrado,String clave){
		String mensajeEnClaro="";
		return mensajeEnClaro;
	}
}
