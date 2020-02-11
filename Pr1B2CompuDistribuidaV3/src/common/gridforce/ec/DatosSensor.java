package common.gridforce.ec;

import java.io.Serializable;

public class DatosSensor implements Serializable {

    private int numHabitacion;
    private boolean estadoHumo;
    private boolean estadoHabitacion;
    private String msgHumo;

    public DatosSensor(int numHabitacion, boolean estadoHabitacion , boolean estadoHumo, String msgHumo) {
        this.numHabitacion = numHabitacion;
        this.estadoHumo = estadoHumo;
        this.estadoHabitacion = estadoHabitacion;
        this.msgHumo = msgHumo;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public boolean isEstadoHumo() {
        return estadoHumo;
    }

    public void setEstadoHumo(boolean estadoHumo) {
        this.estadoHumo = estadoHumo;
    }

    public boolean isEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(boolean estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public String getMsgHumo() {
        return msgHumo;
    }

    public void setMsgHumo(String msgHumo) {
        this.msgHumo = msgHumo;
    }

    @Override
    public String toString() {
        return "DatosSensor{" +
                "numHabitacion=" + numHabitacion +
                ", estadoHumo=" + estadoHumo +
                ", estadoHabitacion=" + estadoHabitacion +
                ", msgHumo='" + msgHumo + '\'' +
                '}';
    }
}
