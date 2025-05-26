package model;

public class Servicio {

    private String fecha;
    private String tipoServicio;
    private String comentario;
    private String urgencia;
    private double precio; // Nuevo campo para el precio



    public Servicio(String fecha, String tipoServicio, String comentario, String urgencia, double precio) { // Modificar el constructor

        this.fecha = fecha;
        this.tipoServicio = tipoServicio;
        this.comentario = comentario;
        this.urgencia = urgencia;
        this.precio = precio;

    }

    public Servicio(int id, String nombre, String apellidos, int edad, String pais, String correo, String celular) {

    }



    public String getFecha() {

        return fecha;

    }



    public void setFecha(String fecha) {

        this.fecha = fecha;

    }



    public String getTipoServicio() {

        return tipoServicio;

    }



    public void setTipoServicio(String tipoServicio) {

        this.tipoServicio = tipoServicio;

    }



    public String getComentario() {

        return comentario;

    }



    public void setComentario(String comentario) {

        this.comentario = comentario;

    }



    public String getUrgencia() {

        return urgencia;

    }



    public void setUrgencia(String urgencia) {

        this.urgencia = urgencia;

    }



    public double getPrecio() {

        return precio;

    }



    public void setPrecio(double precio) {

        this.precio = precio;

    }

}