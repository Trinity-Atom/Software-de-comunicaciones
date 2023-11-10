package calculadora;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import thriftStubs.CalculadoraExcepcion;

/** Esta clase contiene la implementación final de cada operación disponible en la calculadora.
 * Debe contener un método público de instancia por cada método de la interfaz CalculadoraGUI.ICalculadora.
 * Para evitar confusiones, se recomienda que cada uno de los métodos citados anteponga 'implementacion' al
 * nombre del método de CalculadoraGUI.ICalculadora. Por ejemplo: si se desea crear el método que implementa
 * la suma, su nombre en esta clase será 'implementacionSumar'.
 */
public class OperacionesCalculadora {
	private ReentrantReadWriteLock cerrojo = new ReentrantReadWriteLock();
	private Map<Long, Double> ultimoResultado = new ConcurrentHashMap<>();
	private Map<Long, Double> memoriaAcumuladora = new ConcurrentHashMap<>();

	// Escribir los métodos.
	public double implementacionSumar(double operando1, double operando2) {
		cerrojo.writeLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			ultimoResultado.put(pid, operando1 + operando2);
			return ultimoResultado.get(pid);
		} finally{
			cerrojo.writeLock().unlock();
		}
	}
	
	public double implementacionRestar(double operando1, double operando2) {
		cerrojo.writeLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			ultimoResultado.put(pid, operando1 - operando2);
			return ultimoResultado.get(pid);
		} finally{
			cerrojo.writeLock().unlock();
		}
	}
	
	public double implementacionMultiplicar(double operando1, double operando2) {
		cerrojo.writeLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			ultimoResultado.put(pid, operando1 * operando2);
			return ultimoResultado.get(pid);
		} finally{
			cerrojo.writeLock().unlock();
		}
	}
	
	public double implementacionDividir(double operando1, double operando2) throws CalculadoraExcepcion {
		cerrojo.writeLock().lock();
		try {
			if(operando1 == 0 && operando2 == 0) {
				throw new CalculadoraExcepcion("Indeterminación");
			} else if(operando1 != 0 && operando2 == 0) {
				throw new CalculadoraExcepcion("No se puede dividir entre 0");
			} else {
				long pid = Thread.currentThread().getId();
				ultimoResultado.put(pid, operando1/operando2);
				return ultimoResultado.get(pid);
			}
		} finally{
			cerrojo.writeLock().unlock();
		}
	}
	
	public double implementacionUR() {
		cerrojo.readLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			return ultimoResultado.get(pid);
		} finally {
			cerrojo.readLock().unlock();
		}
	}
	
	public void implementacionML() {
		cerrojo.writeLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			ultimoResultado.put(pid,(double) 0);
		} finally {
			cerrojo.writeLock().unlock();
		}
		
	}
	
	public void implementacionMA() {
		cerrojo.writeLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			double operacion = memoriaAcumuladora.get(pid) + ultimoResultado.get(pid);
			memoriaAcumuladora.put(pid, operacion);
		} finally {
			cerrojo.writeLock().unlock();
		}
	}
	public double implementacionMO() {
		cerrojo.readLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			return memoriaAcumuladora.get(pid);
		} finally {
			cerrojo.readLock().unlock();
		}
	}
	
	public double implementacionCuadrado(double operando) {
		cerrojo.writeLock().lock();
		try {
			long pid = Thread.currentThread().getId();
			ultimoResultado.put(pid, operando * operando);
			return ultimoResultado.get(pid);
		} finally {
			cerrojo.writeLock().unlock();
		}
	}
}
