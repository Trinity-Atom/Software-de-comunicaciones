package cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import CalculadoraGUI.ICalculadora;
import rmiCalculadora.CalculadoraExcepcion;
import rmiCalculadora.ICalculadoraRMI;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {

	private ICalculadoraRMI stubCliente = null;
	
	public AdaptadorOperacionesCalculadoraGUI() {
		try {
			final Registry registry = LocateRegistry.getRegistry("localhost",1099);
			stubCliente = (ICalculadoraRMI) registry.lookup("Calculadora");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}	
	}
	
	public double sumar(double operando1, double operando2) {
		try {
			return stubCliente.sumar(operando1, operando2);
		} catch (RemoteException e) {
			return 0;
		}
	}
	
	public double restar(double operando1, double operando2) {
		try {
			return stubCliente.restar(operando1, operando2);
		} catch (RemoteException e) {
			return 0;
		}
	}
	
	public double multiplicar(double operando1, double operando2) {
		try {
			return stubCliente.multiplicar(operando1, operando2);
		} catch (RemoteException e) {
			return 0;
		}
	}

	public double dividir(double dividendo, double divisor) throws Exception {
		try {
			return stubCliente.dividir(dividendo, divisor);
		} catch (CalculadoraExcepcion e){
			throw new CalculadoraExcepcion (e.getMessage());
		} catch (RemoteException e) {
			return 0;
		}
	}
	
	public double obtenerUltimoResultado() {
		try {
			return stubCliente.obtenerUltimoResultado();
		} catch (RemoteException e) {
			return 0;
		}
	}

	public void memoriaLimpiar() {
		try {
			stubCliente.memoriaLimpiar();
		} catch (RemoteException e) {
			
		}	
	}
	
	public void memoriaAniadir() {
		try {
			stubCliente.memoriaAniadir();
		} catch (RemoteException e) {
			
		}
	}

	public double memoriaObtener(){

		try {
			return stubCliente.memoriaObtener();
		} catch (RemoteException e) {
			return -1;
		}
	}

	public double elevarAlCuadrado(double operando) {
		try {
			return stubCliente.elevarAlCuadrado(operando);
		} catch (RemoteException e) {
			return -1;
		}
	}
}
