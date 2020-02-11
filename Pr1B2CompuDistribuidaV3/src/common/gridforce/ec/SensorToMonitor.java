package common.gridforce.ec;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorToMonitor extends Remote{
	void comenzarSupervision(DatosTemperatura dh) throws RemoteException;
	boolean prenderApagarAire(DatosTemperatura dh) throws RemoteException;
	void notificarIncendio(DatosSensor dh) throws RemoteException;
	void setTiempo(int temp) throws RemoteException;
	int modificarTiempo(DatosTemperatura dh) throws RemoteException;
}
