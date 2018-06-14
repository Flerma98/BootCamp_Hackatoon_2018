package com.tec.fernandoalberto.hackaton_web_servicie_prueba;

public class Datos_Reporte {
    String Humedad;
    String RevolucionesMinuto;
    String Fecha;
    String Hora;

    public Datos_Reporte() {
    }

    public Datos_Reporte(String humedad, String revolucionesMinuto, String fecha, String hora) {
        Humedad = humedad;
        RevolucionesMinuto = revolucionesMinuto;
        Fecha = fecha;
        Hora = hora;
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

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }
}
