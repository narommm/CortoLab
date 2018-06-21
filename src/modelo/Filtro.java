/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author María Lourdes
 */
public class Filtro {
    private String afp;
    private String nombre;
    private String profesion;
    private int edad;
    private boolean estado;
    
    public Filtro(){}

    public Filtro(String text, String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
  
    public Filtro(String afp, String nombre, String profesion, int edad, boolean estado) {
        this.afp = afp;
        this.nombre = nombre;
        this.profesion = profesion;
        this.edad = edad;
        this.estado = estado;
    }
    
    public Filtro(String nombre, String profesion, int edad, boolean estado) {
        this.nombre = nombre;
        this.profesion = profesion;
        this.edad = edad;
        this.estado = estado;
    }

    public Filtro(String profesion, int edad, boolean estado) {
        this.profesion = profesion;
        this.edad = edad;
        this.estado = estado;
    }
}
