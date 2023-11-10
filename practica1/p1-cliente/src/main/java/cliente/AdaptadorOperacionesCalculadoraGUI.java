package cliente;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import CalculadoraGUI.ICalculadora;
import thriftStubs.CalculadoraExcepcion;
import thriftStubs.ServicioCalculadora;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {

	private ServicioCalculadora.Iface stubCliente = null;
	
	public AdaptadorOperacionesCalculadoraGUI() {
		try {
			final TTransport transport = new TSocket("localhost", 8585);
			transport.open();
			final TProtocol protocol = new TBinaryProtocol(transport);
			stubCliente = new ServicioCalculadora.Client(protocol);
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	
	public double sumar(double operando1, double operando2) {
		try {
			return stubCliente.sumar(operando1, operando2);
		} catch (TException e) {
			return 0;
		}
	}
	
	public double restar(double operando1, double operando2) {
		try {
			return stubCliente.restar(operando1, operando2);
		} catch (TException e) {
			return 0;
		}
	}
	
	public double multiplicar(double operando1, double operando2) {
		try {
			return stubCliente.multiplicar(operando1, operando2);
		} catch (TException e) {
			return 0;
		}
	}

	public double dividir(double dividendo, double divisor) throws Exception {
		try {
			return stubCliente.dividir(dividendo, divisor);
		} catch (CalculadoraExcepcion e){
			throw new Exception (e.getDescripcion());
		} catch (TException e) {
			return 0;
		}
	}
	
	public double obtenerUltimoResultado() {
		try {
			return stubCliente.obtenerUltimoResultado();
		} catch (TException e) {
			return 0;
		}
	}

	public void memoriaLimpiar() {
		try {
			stubCliente.memoriaLimpiar();
		} catch (TException e) {
			
		}	
	}
	
	public void memoriaAniadir() {
		try {
			stubCliente.memoriaAniadir();
		} catch (TException e) {
			
		}
	}

	public double memoriaObtener(){

		try {
			return stubCliente.memoriaObtener();
		} catch (TException e) {
			return -1;
		}
	}

	public double elevarAlCuadrado(double operando) {
		try {
			return stubCliente.elevarAlCuadrado(operando);
		} catch (TException e) {
			return -1;
		}
	}

	

	

	

}
