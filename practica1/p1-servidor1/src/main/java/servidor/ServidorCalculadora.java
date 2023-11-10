package servidor;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import thriftStubs.ServicioCalculadora.Iface;

import thriftStubs.ServicioCalculadora;

public class ServidorCalculadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TServerSocket svrTrans = new TServerSocket(8585);
		    ServicioCalculadora.Iface manejador = new AdaptadorOperacionesCalculadora();
			TProcessor processor = new ServicioCalculadora.Processor<Iface>(manejador);
		    TServer server = new TSimpleServer(new TSimpleServer.Args(svrTrans).processor(processor));
		    server.serve(); 
		} catch (TException e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}
	}

}
