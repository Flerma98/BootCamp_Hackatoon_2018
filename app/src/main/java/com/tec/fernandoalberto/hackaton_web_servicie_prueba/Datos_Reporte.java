package com.tec.fernandoalberto.hackaton_web_servicie_prueba;

public class Datos_Reporte {
    String Humedad;
    String RevolucionesMinuto;
    String Fecha;
    String ID;

    public Datos_Reporte() {
    }

    public Datos_Reporte(String humedad, String revolucionesMinuto, String fecha, String ID) {
        Humedad = humedad;
        RevolucionesMinuto = revolucionesMinuto;
        Fecha = fecha;
        this.ID = ID;
    }

    public String getHumedad() {
        return Humedad;
    }

    public void setHumedad(String humedad) {
        Humedad = humedad;
    }

    public String getRevolucionesMinuto() {
        return RevolucionesMinuto;
    }

    public void setRevolucionesMinuto(String revolucionesMinuto) {
        RevolucionesMinuto = revolucionesMinuto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
