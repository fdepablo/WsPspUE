package _01_simetrica;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/*
 * caracter�sticas del algoritmo DES.

    1- El algoritmo DES divide el mensaje original en bloques de 64 bits.

    2- La clave generada tambi�n es de 64 bits.

    3- Cada bloque de 64 bits sufre una transformaci�n inicial, denominada permutaci�n.

    4- Tras la primera transformaci�n, cada bloque sufrir� otras 16 transformaciones a 
    trav�s de las denominadas "Rondas Feistel". Cada transformaci�n utilizar� su propia 
    clave generada a partir de la clave inicial.

    5- Una vez finalizadas las 16 rondas, cada bloque sufrir� una transformaci�n final.
 */
public class _01_MainDES {
	public static void main(String[] args) {
		System.out.println("Probando sistema de encriptaci�n con algoritmo DES");
		try {
			//generador de esc�talas espartanas
			KeyGenerator generador = KeyGenerator.getInstance("DES");
			System.out.println("Paso 1: Se ha obtenido el generador de claves");
			
			//Generamos la clave simetrica. (La esc�tala espartana)
			SecretKey paloEspartano = generador.generateKey();
			System.out.println("Paso 2: Se ha obtenido la clave");

			//objeto que nos permitira encriptar o desencriptar a a partir de un
			//palo espartano
			Cipher espartano = Cipher.getInstance("DES");
			System.out.println("Paso 3: Hemos obtenido el cifrador/descifrador");
			
			//ahora el cifrador lo configuramos para que use la clave simetrica
			//para encriptar
			espartano.init(Cipher.ENCRYPT_MODE, paloEspartano);
			System.out.println("Paso 4: Hemos configurado el cifrador");
						
			String mensajeOriginal = "Felix va a carrilear a sus alumnos :) :)";
			//el cifrador trabaja con bytes
			byte[] bytesMensajeOriginal = mensajeOriginal.getBytes();
			//el cifrador devuelve una cadena de bytes
			//ahora el mensaje esta cifrado
			byte[] bytesMensajeCifrado = espartano.doFinal(bytesMensajeOriginal);
			
			String mensajeCifrado = new String(bytesMensajeCifrado);
			System.out.println("Mensaje Original: " + mensajeOriginal);
			System.out.println("Mensaje Cifrado: " + mensajeCifrado);
			
			//Ahora desciframos
			System.out.println("Desciframos el mensaje cifrado para comprobar que comprueba "
					+ "con el original");
			//ahora el cifrador lo configuramos para que use la clave simetrica
			//para desencriptar. Debemos de usar el mismo palo espartano para
			//descifrar
			Cipher espartanoQueLee = Cipher.getInstance("DES");
			paloEspartano = generador.generateKey();//Genero otro palo
			espartanoQueLee.init(Cipher.DECRYPT_MODE, paloEspartano);
			byte[] bytesMensajeDescifrado = espartanoQueLee.doFinal(bytesMensajeCifrado);
			System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
			
			//Todas estas excepciones derivan de GeneralSecurityException
			//por lo que se puede simplificar simplemente capturando esa excepci�n
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			//NoSuchAlgorithmException: se produce cuando se especifica un algoritmo de cifrado 
			//que no existe.
			//NoSuchPaddingException: cuando la clave no tiene la configuraci�n correcta
			//InvalidKeyException: la clave es inv�lida (codificaci�n incorrecta, 
			//longitud incorrecta o no est� inicializada)
			System.out.println("Error al crear y configurar el descifrador");
			System.out.println(e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			//IllegalBlockSizeException: longitud incorrecta de alguno de los bloques de 
			//cifrado por un error durante el algoritmo.
			//BadPaddingException: cuando la clave no tiene la configuraci�n correcta
			System.out.println("Error al cifrar el mensaje");
			System.out.println(e.getMessage());
		}
	}
}
