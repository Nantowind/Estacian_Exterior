package Entities;

public class Familias {

    private Integer id_familia;
    private String nombre;
    private Integer edad_minima;
    private Integer edad_maxima;
    private Integer num_hijos;
    private String email;
    private Integer id_casa_familia;

    public Familias() {
    }

    public Integer getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad_minima() {
        return edad_minima;
    }

    public void setEdad_minima(int edad_minima) {
        this.edad_minima = edad_minima;
    }

    public Integer getEdad_maxima() {
        return edad_maxima;
    }

    public void setEdad_maxima(int edad_maxima) {
        this.edad_maxima = edad_maxima;
    }

    public Integer getNum_hijos() {
        return num_hijos;
    }

    public void setNum_hijos(int num_hijos) {
        this.num_hijos = num_hijos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId_casa_familia() {
        return id_casa_familia;
    }

    public void setId_casa_familia(int id_casa_familia) {
        this.id_casa_familia = id_casa_familia;
    }


    @Override
    public String toString() {
        return "Familias{" +
                "id_familia=" + id_familia +
                ", nombre='" + nombre + '\'' +
                ", edad_minima=" + edad_minima +
                ", edad_maxima=" + edad_maxima +
                ", num_hijos=" + num_hijos +
                ", email='" + email + '\'' +
                ", id_casa_familia=" + id_casa_familia +
                '}';
    }
}
