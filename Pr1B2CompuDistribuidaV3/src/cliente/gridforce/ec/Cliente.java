package cliente.gridforce.ec;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;

import common.gridforce.ec.SensorToMonitor;
import guis.gridforce.ec.ClientGUI;

public class Cliente extends Thread {
	
	static SensorToMonitor servidor = null;
	static int tiempo = 0;
	static Thread s;
	static Thread t;
	private static HiloTemperatura hiloTemp;
	private static HiloSensor hiloSens;

	private static ClientGUI clientGUI;
	public Cliente() throws RemoteException {
		super();
	}
		
	public static void main(String args[]) throws InterruptedException, UnknownHostException, RemoteException, MalformedURLException {
		try {
			System.out.println("Buscando el servidor... ");
			servidor = (SensorToMonitor) Naming.lookup("rmi://localhost/Prueba");
			clientGUI = new ClientGUI(new Cliente());
			hiloTemp = new HiloTemperatura(servidor,clientGUI,2000);
			hiloSens = new HiloSensor(servidor, 1,clientGUI);	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void iniciar(){
		t = new Thread(hiloSens);
		s = new Thread(hiloTemp);
		t.start();
		s.start();
	}

	public void finalizar(){
		hiloTemp.salidaPersona();
		s.suspend();
		t.suspend();
	}
	
	public void humo(boolean b) {
		hiloSens.provocarHumo(b);
		hiloTemp.humo(b);
	}
}
