// Clase: CalculadoraExcepcion
// Define la excepción que se produce cuando hay algún error de cálculo en la calculadora.

package rmiCalculadora;

public class CalculadoraExcepcion extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;
	public CalculadoraExcepcion(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
