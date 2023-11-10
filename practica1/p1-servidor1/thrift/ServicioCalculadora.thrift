namespace java thriftStubs

exception CalculadoraExcepcion{
	1: string descripcion
}

service ServicioCalculadora{
	double sumar(1: double operando1, 2: double operando2)
	double restar(1: double operando1, 2: double operando2)
	double multiplicar(1: double operando1, 2: double operando2)
	double dividir(1: double operando1, 2: double operando2) throws(1: CalculadoraExcepcion e)
	double obtenerUltimoResultado()
	void memoriaLimpiar()
	void memoriaAniadir()
	double memoriaObtener()
	double elevarAlCuadrado(1: double operando)
}