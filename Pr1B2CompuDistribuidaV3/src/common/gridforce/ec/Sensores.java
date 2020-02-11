package common.gridforce.ec;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sensores extends Remote{
	void prenderAire() throws RemoteException;
	void apagarAire() throws RemoteException;
}