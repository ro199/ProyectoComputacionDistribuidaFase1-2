package cliente.gridforce.ec;

import java.rmi.RemoteException;

import common.gridforce.ec.DatosSensor;
import common.gridforce.ec.SensorToMonitor;
import guis.gridforce.ec.ClientGUI;

public class HiloSensor implements Runnable {

    private static DatosSensor dh;
    static SensorToMonitor servidor = null;
    static Thread s;
    private int n;
    private static boolean flag = false;
    private static ClientGUI cli;
    static boolean estadoAire = false;

    public HiloSensor(SensorToMonitor servidor, int n, ClientGUI cli) {
        super();
        this.n = n;
        this.servidor = servidor;
        this.cli = cli;
    }

    @Override
    public void run() {
        for (;;){
        	System.out.println("***Inicia el hilo Sensor***" );
        	enviarDatostoServidor();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException exc) {
                System.out.println("Hilo principal interrumpido.");
            }
            System.out.println("***Termina el hilo Sensor***" );
        }
    }

    public static void enviarDatostoServidor(){
        dh = new DatosSensor(2, true,false,"OK");
        
        try {
            ClienteImp c = new ClienteImp();
            System.out.println("Hay humo en la habitacion?:"+flag);
            if(flag) {
            	dh.setEstadoHumo(true);
            	c.apagarAire();
            }else {
            	dh.setEstadoHumo(false);	
            }
            servidor.notificarIncendio(dh);
            
            cli.changeValues(dh);
           
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("*******	Datos sensor humo"+dh.toString());
    }

	public void provocarHumo(boolean b) {
		flag = b;
		System.out.println("value of b:"+b);
	}
}
