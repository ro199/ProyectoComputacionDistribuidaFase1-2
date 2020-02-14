package cliente.gridforce.ec;

import java.rmi.RemoteException;

import common.gridforce.ec.DatosTemperatura;
import common.gridforce.ec.SensorToMonitor;
import guis.gridforce.ec.ClientGUI;

import javax.swing.*;

public class HiloTemperatura implements Runnable{

	static boolean estadoAire = false;
	static boolean personaHabitacion = false;
	static int temperatura = 0;
	private static DatosTemperatura dh;
	private static SensorToMonitor servidor;
	private static int temp;
	private static int igualador;
	private int n;
	private static ClientGUI cli;
	private static boolean flag = false;

	public HiloTemperatura(SensorToMonitor servidor, ClientGUI cli, int temp) {
		super();
		this.n = n;
		this.servidor = servidor;
		this.cli = cli;
		HiloTemperatura.temp = temp;
	}

	/*public void setTiempo(int t){
		this.temp = t;
		JOptionPane.showMessageDialog(null, "Tiemposss " + HiloTemperatura.temp);
		igualador = t - 2000;
		try {
			Thread.sleep(igualador);
		} catch (InterruptedException exc) {
			System.out.println("Hilo principal interrumpido.");
		}
	}*/

	public static void setTemp(int temp) {
		HiloTemperatura.temp = temp;
	}

	public void run() {
		ingresaPersona();
		for (;;){
			System.out.println("***Inicia el hilo temperatura***");
			enviarDatostoServidor(generarTemperatura());
			try {
				Thread.sleep(temp);
			} catch (InterruptedException exc) {
				System.out.println("Hilo principal interrumpido.");
			}
			System.out.println("***Termina el hilo temperatura***");
		}

	}

	public static void ingresaPersona() {
		System.out.println("Ha ingresado alguien a la habitacion");
		personaHabitacion = true;
		estadoAire = true;
	}

	public static void enviarDatostoServidor(int temp){

		boolean bandera = false;
		dh = new DatosTemperatura(1, personaHabitacion, temp, estadoAire);
		try {
			dh.setEstadoAire(estadoAire);
			//servidor.comenzarSupervision(dh);
			ClienteImp c = new ClienteImp();
			bandera = servidor.prenderApagarAire(dh);
			if(flag) {
				dh.setEstadoAire(false);
			}else {
				if(bandera == true) {
					c.prenderAire();
					dh.setEstadoAire(estadoAire);
				}else {
					c.apagarAire();
					dh.setEstadoAire(estadoAire);
				}
			}
			igualador = servidor.modificarTiempo(dh);
			System.out.println("dh comenzar supervision: "+dh.toString());
			servidor.comenzarSupervision(dh);
			if(igualador == 2) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException exc) {
					System.out.println("Hilo principal interrumpido.");
				}
			}
			
			if(igualador == 5){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException exc) {
					System.out.println("Hilo principal interrumpido.");
				}
			}

			if(igualador == 10){
				try {
					Thread.sleep(8000);
				} catch (InterruptedException exc) {
					System.out.println("Hilo principal interrumpido.");
				}
			}
			//c.tiempo(dh);
			cli.changeValues(dh, estadoAire);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void salidaPersona() {
		System.out.println("La persona esta saliendo de la habitacion");
		personaHabitacion = false;
		estadoAire = false;
		dh = new DatosTemperatura(1, personaHabitacion, 0, estadoAire);
		System.out.println("Fin");
		try {
			servidor.comenzarSupervision(dh);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static int generarTemperatura() {
		return temperatura = (int)(Math.random()*(30-20+1)+20);
	}

	public void humo(boolean b) {
		flag = b;
		System.out.println("value of b:"+b);
	}

}
