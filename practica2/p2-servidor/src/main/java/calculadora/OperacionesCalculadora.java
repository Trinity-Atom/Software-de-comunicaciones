// Clase: OperacionesCalculadora
// Esta clase implementa las operaciones de la calculadora.

package calculadora;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/** Esta clase contiene la implementación final de cada operación disponible en la calculadora.
 * Debe contener un método público de instancia por cada método de la interfaz CalculadoraGUI.ICalculadora.
 * Para evitar confusiones, se recomienda que cada uno de los métodos citados anteponga 'implementacion' al
 * nombre del método de CalculadoraGUI.ICalculadora. Por ejemplo: si se desea crear el método que implementa
 * la suma, su nombre en esta clase será 'implementacionSumar'.
 */
public class OperacionesCalculadora {
	private ReentrantReadWriteLock cerrojo = new ReentrantReadWriteLock();
	private double ultimoResultado = 0;
	private double memoriaAcumuladora = 0;
	
	public double implementacionSumar(double operando1, double operando2) {
		cerrojo.writeLock().lock();
		try {
			ultimoResultado = operando1 + operando2;
			return ultimoResultado;
		} finally{
			cerrojo.writeLock().unlock();
		}
	}
	
	public double implementacionRestar(double operando1, double operando2) {
		cerrojo.writeLock().lock();
		try {
			ultimoResultado = operando1 - operando2;
			return ultimoResultado;
		} finally{
			cerrojo.writeLock().unlock();
		}
	}
	
	public double implementacionMultiplicar(double operando1, double operando2) {
		cerrojo.writeLock().lock();
		try {
			ultimoResultado = operando1 * operando2;
			return ultimoResultado;
		} finally{
			cerrojo.writeLock().unlock();
		}
	}

	public double implementacionDividir(double dividendo, double divisor) throws Exception {
		if(dividendo == 0 && divisor == 0) throw new Exception("indeterminación");
		if(divisor == 0) throw new Exception("infinito");
		cerrojo.writeLock().lock();
		try {
			ultimoResultado = dividendo / divisor;
			return ultimoResultado;
		} finally{
			cerrojo.writeLock().unlock();
		}
	}

	public double implementacionUR() {
		cerrojo.readLock().lock();
		try {
			return ultimoResultado;
		} finally {
			cerrojo.readLock().unlock();
		}
	}
	
	public void implementacionML() {
		cerrojo.writeLock().lock();
		try {
			ultimoResultado = 0;
		} finally {
			cerrojo.writeLock().unlock();
		}
	}
	
	public void implementacionMA() {
		cerrojo.writeLock().lock();
		try {
			memoriaAcumuladora = memoriaAcumuladora + ultimoResultado;
		} finally {
			cerrojo.writeLock().unlock();
		}
	}
	
	public double implementacionMO() {
		cerrojo.readLock().lock();
		try {
			return memoriaAcumuladora;
		} finally {
			cerrojo.readLock().unlock();
		}
	}
	
	public double implementacionCuadrado(double operando) {
		cerrojo.writeLock().lock();
		try {
			ultimoResultado = operando * operando;
			return ultimoResultado;
		} finally {
			cerrojo.writeLock().unlock();
		}
	}
}
