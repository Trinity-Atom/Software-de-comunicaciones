package calculadora;

import thriftStubs.CalculadoraExcepcion;

/** Esta clase contiene la implementación final de cada operación disponible en la calculadora.
 * Debe contener un método público de instancia por cada método de la interfaz CalculadoraGUI.ICalculadora.
 * Para evitar confusiones, se recomienda que cada uno de los métodos citados anteponga 'implementacion' al
 * nombre del método de CalculadoraGUI.ICalculadora. Por ejemplo: si se desea crear el método que implementa
 * la suma, su nombre en esta clase será 'implementacionSumar'.
 */
public class OperacionesCalculadora {
	private double ultimoResultado = 0;
	private double memoriaAcumuladora = 0;

	// Escribir los métodos.
	public double implementacionSumar(double operando1, double operando2) {
		ultimoResultado = operando1 + operando2;
		return ultimoResultado;
	}
	
	public double implementacionRestar(double operando1, double operando2) {
		ultimoResultado = operando1 - operando2;
		return ultimoResultado;
	}
	
	public double implementacionMultiplicar(double operando1, double operando2) {
		ultimoResultado = operando1 * operando2;
		return ultimoResultado;
	}
	
	public double implementacionDividir(double operando1, double operando2) throws CalculadoraExcepcion {
		if(operando1 == 0 && operando2 == 0) {
			throw new CalculadoraExcepcion("Indeterminación");
		} else if(operando1 != 0 && operando2 == 0) {
			throw new CalculadoraExcepcion("No se puede dividir entre 0");
		} else {
			ultimoResultado = operando1/operando2;
			return ultimoResultado;
		}
	}
	
	public double implementacionUR() {
		return ultimoResultado;
	}
	
	public void implementacionML() {
		ultimoResultado = 0;
	}
	
	public void implementacionMA() {
		memoriaAcumuladora = memoriaAcumuladora + ultimoResultado;
	}
	public double implementacionMO() {
		return memoriaAcumuladora;
	}
	
	public double implementacionCuadrado(double operando) {
		ultimoResultado = operando * operando;
		return ultimoResultado;
	}
}
