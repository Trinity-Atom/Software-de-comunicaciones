// Clase: ServidorCalculadora
// Clase principal que implementa el modelo general de un servidor que registra
// y sirve un objeto remoto

package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmiCalculadora.ICalculadoraRMI;

public class ServidorCalculadora {

	public static void main(String[] args) {
		try {
			ICalculadoraRMI operaciones = new AdaptadorOperacionesCalculadoraRMI();
			ICalculadoraRMI remoteStub = (ICalculadoraRMI) UnicastRemoteObject.exportObject(operaciones, 0);
//			Registry registry = LocateRegistry.getRegistry();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Calculadora", remoteStub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
