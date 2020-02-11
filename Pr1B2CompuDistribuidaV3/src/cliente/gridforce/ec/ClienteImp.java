package cliente.gridforce.ec;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.gridforce.ec.DatosTemperatura;
import common.gridforce.ec.Sensores;

import javax.swing.*;

public class ClienteImp extends UnicastRemoteObject implements Sensores {
		
	
	public ClienteImp() throws RemoteException {
		super();
	}

	@Override
	public void prenderAire() throws RemoteException {
		HiloTemperatura.estadoAire = true;	
	}

	@Override
	public void apagarAire() throws RemoteException {
		HiloTemperatura.estadoAire = false;
		HiloSensor.estadoAire = false;
	}

}
