/**
 * 
 */
package tipoCifrado;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.encoders.Hex;

import ioManagement.FileManager;
import ioManagement.GuardarFormatoPEM;

/**
 * CLASE ASIMETRICO
 * Implementa todos los métodos necesarios para generar clave, cifrar-descifrar y firmar con algoritmo RSA asimétrico.
 *  
 * @author Salvador Fdez
 * @author Ivan Perez
 */
public class Asimetrico {
	FileManager fmanager;
	public Asimetrico() {
		fmanager = new FileManager();
	}

	/**
	 * METODO GENERAR CLAVES
	 * Genera las claves RSA pública y privada y las guarda en un archivo PEM
	 * 
	 * @param rutaKs	Ruta del fichero que contiene la clave privada
	 * @param rutaKp	Ruta del fichero que contiene la clave publica
	 */
	public void generarClaves(String rutaKs, String rutaKp) {
		// 1. Parámetros para el método init de generadorClaves
		RSAKeyGenerationParameters parametros = new RSAKeyGenerationParameters(BigInteger.valueOf(3), new SecureRandom(), 2048, 10);
		
		// 2. Instanciar generador de claves
		RSAKeyPairGenerator keygen = new RSAKeyPairGenerator();
		
		// 3. Inicializarlo. Clase AsymmetricCipherKeyPair
		keygen.init(parametros);
		AsymmetricCipherKeyPair claves;
		
		// 4. Generar claves
		claves = keygen.generateKeyPair();
		
		// 5. Obtener clave privada y pública
		RSAKeyParameters cpublica = (RSAKeyParameters) claves.getPublic();
		RSAKeyParameters cprivada = (RSAKeyParameters) claves.getPrivate();
		
		// 6. Guardar en formato PEM
		//GuardarFormatoPEM formato = new GuardarFormatoPEM();
		//formato.guardarClavesPEM(cpublica, cprivada);
		
		// 7. Guardar cada clave en un fichero
		try {
			PrintWriter ficheroPrivada = new PrintWriter(new FileWriter(rutaKs));
			ficheroPrivada.println(new String (Hex.encode(cprivada.getModulus().toByteArray())));
			ficheroPrivada.print(new String (Hex.encode(cprivada.getExponent().toByteArray())));
			ficheroPrivada.close();
			PrintWriter ficheroPublica = new PrintWriter(new FileWriter(rutaKp));
			ficheroPublica.println(new String (Hex.encode(cpublica.getModulus().toByteArray())));
			ficheroPublica.print(new String (Hex.encode(cpublica.getExponent().toByteArray())));
			ficheroPublica.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * MÉTODO CIFRAR
	 * Este método cifra un archivo contenido en rutaMensaje con la clave contenida en rutaFicheroClave
	 * 
	 * @param rutaFicheroClave		Ruta del fichero que contiene la clave
	 * @param rutaMensaje			Path del fichero que contiene el mensaje	
	 * @param rutaFicheroCifrado	Path del fichero que contiene el mensaje cifrado
	 * @param usesPrivateKey		Cifra con la privada = true. Cifra con la publica = false
	 * @return						Array de bytes que contiene el mensaje cifrado
	 */
	public byte[] cifrar(String rutaFicheroClave, String rutaMensaje, String rutaFicheroCifrado,String tipo) {
		byte[] datosCifrados = null;
		try {
			// 1. Leer el modulo y el exponente de la clave
			BufferedReader lectorClave = new BufferedReader(new FileReader(rutaFicheroClave));
			BigInteger modulo = new BigInteger(Hex.decode(lectorClave.readLine()));
			BigInteger exponente = new BigInteger(Hex.decode(lectorClave.readLine()));
			lectorClave.close();
			
			// 2. Generación parámetros para inicializar el cifrador
			RSAKeyParameters parametros = new RSAKeyParameters(tipo.equals("privada"), modulo, exponente);
			
			// 3. Instanciar el cifrador
			AsymmetricBlockCipher cifrador = new PKCS1Encoding(new RSAEngine());
			
			// 4. Inicializarlo
			cifrador.init(true,parametros);
			
			// 5. Leer bloques del fichero a cifrar e ir cifrando
			byte[] datosLeidos = fmanager.leerFichero(rutaMensaje);
			datosCifrados = cifrador.processBlock(datosLeidos, 0, datosLeidos.length);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
		}
		return datosCifrados;
	}

	/**
	 * MÉTODO DESCIFRAR
	 * Este método descifra un archivo contenido en rutaMensaje con la clave contenida en rutaFicheroClave
	 * 
	 * @param rutaFicheroClave		Ruta del fichero que contiene la clave
	 * @param rutaMensaje			Path del fichero que contiene el mensaje	
	 * @param rutaFicheroCifrado	Path del fichero que contiene el mensaje cifrado
	 * @param usesPrivateKey		Descifra con la privada = true. Cifra con la publica = false
	 * @return						Array de bytes que contiene el mensaje descifrado
	 */
	public byte[] descifrar(String rutaFicheroClave, String rutaMensaje, String rutaFicheroCifrado,String tipo) {
		byte[] datosCifrados = null;
		try {
			// 1. Leer el modulo y el exponente de la clave
			BufferedReader lectorClave = new BufferedReader(new FileReader(rutaFicheroClave));
			BigInteger modulo = new BigInteger(Hex.decode(lectorClave.readLine()));
			BigInteger exponente = new BigInteger(Hex.decode(lectorClave.readLine()));
			lectorClave.close();
			
			// 2. Generación parámetros para inicializar el cifrador
			RSAKeyParameters parametros = new RSAKeyParameters(tipo.equals("privada"), modulo, exponente);
			
			// 3. Instanciar el cifrador
			AsymmetricBlockCipher cifrador = new PKCS1Encoding(new RSAEngine());
			
			// 4. Inicializarlo false
			cifrador.init(false,parametros);
			
			// 5. Leer bloques del fichero a cifrar e ir cifrando
			byte[] datosLeidos = fmanager.leerFichero(rutaMensaje);
			datosCifrados = cifrador.processBlock(datosLeidos, 0, datosLeidos.length);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
		}
		return datosCifrados;
	}

	public void firmar(String rutaClavePrivada, String rutaFicheroAFirmar, String rutaFicheroFirmado) {
		// 1. Instanciar la clase para generar el resumen
		Digest resumen = new SHA3Digest();
		
		// 2. Generar el resumen: los bloques de lectura son del mismo tamaño que el resumen
		//Bucle de lectura de bloques del fichero:
			BufferedInputStream filtroOrigen;
		int leidos;
		try {
			filtroOrigen = new BufferedInputStream(new FileInputStream(rutaFicheroAFirmar));
			byte[] almacen = new byte[blockSize];
			do {
				leidos = filtroOrigen.readNBytes(almacen, 0, blockSize);
			} while(leidos != -1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			//Método update (a partir de cada bloque leído va
			resumen.update(byte[] in, int inOff, int len);
			//actualizando el resumen)
			//Método doFinal (fuera del bucle. Genera resumen final)
			//Escribir el resumen en el fichero

		// 3. cifrar el fichero que contiene el resumen
		this.cifrar(rutaFicheroKp, "resumen.txt", rutaFicheroFirma, "privada");
	}

	public boolean verificarFirma(String ficheroKp, String ficheroMensaje, String ficheroFirmado) {
		// 1.
		this.descifrar(ficheroKp, ficheroFirma, "resumenDescifrado.txt", "publica");
		// 2. generar resumen del mensaje en claro
	}
	
	
}
