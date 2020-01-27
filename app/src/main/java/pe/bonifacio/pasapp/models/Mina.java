package pe.bonifacio.pasapp.models;

public class Mina {

    private Long mina_id;
    private String nombre_min;
    private String lectura_horometro;
    private String placa;
    private String observacion;
    private String serie_motor;
    private String fecha_inicio;

    private Long minproyecto;

    public Long getMina_id() {
        return mina_id;
    }

    public void setMina_id(Long mina_id) {
        this.mina_id = mina_id;
    }

    public String getNombre_min() {
        return nombre_min;
    }

    public void setNombre_min(String nombre_min) {
        this.nombre_min = nombre_min;
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

    public Long getMinproyecto() {
        return minproyecto;
    }

    public void setMinproyecto(Long minproyecto) {
        this.minproyecto = minproyecto;
    }
}
