package servidor.gridforce.ec;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import common.gridforce.ec.DatosSensor;
import common.gridforce.ec.DatosTemperatura;
import common.gridforce.ec.SensorToMonitor;
import guis.gridforce.ec.RegistroGUI;
import guis.gridforce.ec.ServidorGUI;

public class Servidor extends UnicastRemoteObject implements SensorToMonitor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServidorGUI serverGUI;
	private static RegistroGUI registroGUI;
	private static String[] row;
	private static String[] rowSmoke;
	private static int tiempo = 0;

	public Servidor() throws RemoteException {
		super();
	}

	private static boolean isRunning = false;
    
	public static void main(String args[]) {
		try {	
			System.setProperty("java.rmi.server.hostname", "192.168.43.64");
			LocateRegistry.createRegistry(1104);
			Naming.rebind("Prueba", new Servidor());
			System.out.println("Servidor encendido");
			serverGUI = new ServidorGUI();
			registroGUI=new RegistroGUI();
			registroGUI.run();
		}catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }catch (Exception e) {
            System.err.println("Excepcion en ServidorEco:");
            e.printStackTrace();
            System.exit(1);
        }
	}

	@Override
	public void comenzarSupervision(DatosTemperatura dh) throws RemoteException {
		isRunning = dh.isEstadoHabitacion();
		Calendar now = Calendar.getInstance();
		
		System.out.println("Ingrese al comerzar supervision");
		if(isRunning){
           System.out.println("Iniciando supervicion...");
           System.out.println(dh.toString());
           row = new String[]{Integer.toString(dh.getNumHabitacion()), now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND), Integer.toString(dh.getTemperatura()),Boolean.toString(dh.isEstadoAire()) };
           serverGUI.addRowWithData(row);
        }else{
            System.out.println("Deteniendo supervicion...");
        }
	}

	@Override
	public boolean prenderApagarAire(DatosTemperatura dh) throws RemoteException {
		System.out.println("Estoy en el prender Apagar");
		if(dh.getTemperatura() >= 25) {
			System.out.println("Prendiendo aire acondicionado...");
			return true;
		}
		
		if(dh.getTemperatura() <= 22) {
			System.out.println("Apagando aire acondicionado...");			
			return false;
		}
		return false;		
	}

    @Override
    public void notificarIncendio(DatosSensor dh) throws RemoteException {
        System.out.println("Estoy en el Incendio");
        Calendar now = Calendar.getInstance();
        if(dh.isEstadoHumo() == true) {
            System.out.println(dh.toString());
            dh.setMsgHumo("Danger!");
            System.out.println(dh.getMsgHumo());
            System.out.println("Apagando aire acondicionado...");
            rowSmoke = new String[] {now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND),dh.getMsgHumo(),Integer.toString(dh.getNumHabitacion())};
            serverGUI.addRowWithDataSmoke(rowSmoke);
        }else {
        	dh.setMsgHumo("Ok!");
        	System.out.println(dh.getMsgHumo());
        	rowSmoke = new String[] {now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND),dh.getMsgHumo(),Integer.toString(dh.getNumHabitacion())};
            serverGUI.addRowWithDataSmoke(rowSmoke);
		}
        
    }

	@Override
	public void setTiempo(int temp) throws RemoteException {
		this.tiempo = temp;
	}

	@Override
	public int modificarTiempo(DatosTemperatura dh) throws RemoteException {
		if(tiempo == 2){
			dh.setNumHabitacion(2);
			return 2;
		}
		if(tiempo == 5){
			dh.setNumHabitacion(5);
			return 5;
		}
		if(tiempo == 10){
			dh.setNumHabitacion(10);
			return 10;
		}
		return 0;
	}


}
