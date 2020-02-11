package common.gridforce.ec;

import java.io.Serializable;

public class DatosTemperatura implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numHabitacion;
	private int temperatura;
	private boolean estadoAire;
	private boolean estadoHabitacion;

	public DatosTemperatura(int numHabitacion, boolean estadoHabitacion, int temperatura, boolean estadoAire) {
		super();
		this.numHabitacion = numHabitacion;
		this.temperatura = temperatura;
		this.estadoAire = estadoAire;
		this.estadoHabitacion = estadoHabitacion;
	}
	
	public int getNumHabitacion() {
		return numHabitacion;
	}
	public void setNumHabitacion(int numHabitacion) {
		this.numHabitacion = numHabitacion;
	}
	public int getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	public boolean isEstadoAire() {
		return estadoAire;
	}
	public void setEstadoAire(boolean estadoAire) {
		this.estadoAire = estadoAire;
	}
	public boolean isEstadoHabitacion() {
		return estadoHabitacion;
	}
	public void setEstadoHabitacion(boolean estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}

	@Override
	public String toString() {
		return "DatosHabitacion{" +
				"numHabitacion=" + numHabitacion +
				", temperatura=" + temperatura +
				", estadoAire=" + estadoAire +
				", estadoHabitacion=" + estadoHabitacion +
				'}';
	}
}
