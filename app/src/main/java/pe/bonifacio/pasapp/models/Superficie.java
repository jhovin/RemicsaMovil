package pe.bonifacio.pasapp.models;

import java.util.List;

public class Superficie {

    private Long superficie_id;
    private String nombre_sup;
    private String lectura_horometro;
    private String placa;
    private String observacion;
    private String serie_motor;
    private String fecha_inicio;

    private Long supproyecto;



    public Long getSuperficie_id() {
        return superficie_id;
    }

    public void setSuperficie_id(Long superficie_id) {
        this.superficie_id = superficie_id;
    }

    public String getNombre_sup() {
        return nombre_sup;
    }

    public void setNombre_sup(String nombre_sup) {
        this.nombre_sup = nombre_sup;
    }

    public String getLectura_horometro() {
        return lectura_horometro;
    }

    public void setLectura_horometro(String lectura_horometro) {
        this.lectura_horometro = lectura_horometro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getSerie_motor() {
        return serie_motor;
    }

    public void setSerie_motor(String serie_motor) {
        this.serie_motor = serie_motor;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Long getSupproyecto() {
        return supproyecto;
    }

    public void setSupproyecto(Long supproyecto) {
        this.supproyecto = supproyecto;
    }
}
