package servidor;

import org.apache.thrift.TException;

import calculadora.OperacionesCalculadora;
import thriftStubs.CalculadoraExcepcion;
import thriftStubs.ServicioCalculadora.Iface;

public class AdaptadorOperacionesCalculadora extends OperacionesCalculadora implements Iface{

	public AdaptadorOperacionesCalculadora() {
		super();
	
	}

	@Override
	public double sumar(double operando1, double operando2) throws TException {
		return this.implementacionSumar(operando1, operando2);
	}

	@Override
	public double restar(double operando1, double operando2) throws TException {
		return this.implementacionRestar(operando1, operando2);
	}

	@Override
	public double multiplicar(double operando1, double operando2) throws TException {
		return this.implementacionMultiplicar(operando1, operando2);
	}

	@Override
	public double dividir(double operando1, double operando2) throws CalculadoraExcepcion, TException {
		return this.implementacionDividir(operando1, operando2);
	}

	@Override
	public double obtenerUltimoResultado() throws TException {
		return this.implementacionUR();
	}

	@Override
	public void memoriaLimpiar() throws TException {
		this.implementacionML();
	}

	@Override
	public void memoriaAniadir() throws TException {
		this.implementacionMA();
	}

	@Override
	public double memoriaObtener() throws TException {
		return this.implementacionMO();
	}

	@Override
	public double elevarAlCuadrado(double operando) throws TException {
		return this.implementacionCuadrado(operando);
	}

}
