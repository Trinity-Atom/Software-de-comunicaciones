// Clase: EjemploClienteRMI
// Esta es la clase principal que implementa el stub de cliente.

package cliente;

import CalculadoraGUI.CalculadoraGUI;

public class ClienteCalculadora {

	public static void main(String[] args) {
		new CalculadoraGUI(new AdaptadorOperacionesCalculadoraGUI());
	}
}