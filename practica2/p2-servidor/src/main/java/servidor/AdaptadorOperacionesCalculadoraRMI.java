// Clase: AdaptadorOperacionesCalculadoraRMI
// Esta clase implementa el adaptador a la interfaz del objeto remoto y utiliza la
// implementación de OperacionesCalculadora como clase sirviente

package servidor;

import java.rmi.RemoteException;

import calculadora.OperacionesCalculadora;
import rmiCalculadora.CalculadoraExcepcion;
import rmiCalculadora.ICalculadoraRMI;

public class AdaptadorOperacionesCalculadoraRMI implements ICalculadoraRMI{
	
	private OperacionesCalculadora servant;
	
	AdaptadorOperacionesCalculadoraRMI() {
		servant = new OperacionesCalculadora();
	}
	
	public double sumar(double operando1, double operando2) throws RemoteException {
		return servant.implementacionSumar(operando1, operando2);
	}
	
	public double restar(double operando1, double operando2) throws RemoteException{
		return servant.implementacionRestar(operando1, operando2);
	}
	
	public double multiplicar(double operando1, double operando2) throws RemoteException{
		return servant.implementacionMultiplicar(operando1, operando2);
	}
	
	public double dividir(double dividendo, double divisor) throws RemoteException, CalculadoraExcepcion {
		try {
			return servant.implementacionDividir(dividendo, divisor);
		} catch (Exception e) {
			throw new CalculadoraExcepcion(e.getMessage());
		}
	}
	
	public double obtenerUltimoResultado() throws RemoteException{
		return servant.implementacionUR();
	}
	
	public void memoriaLimpiar() throws RemoteException{
		servant.implementacionML();
	}
	
	public void memoriaAniadir() throws RemoteException{
		servant.implementacionMA();
	}
	
	public double memoriaObtener() throws RemoteException{
		return servant.implementacionMO();
	}
	
	public double elevarAlCuadrado(double operando) throws RemoteException{
		return servant.implementacionCuadrado(operando);
	}
}
