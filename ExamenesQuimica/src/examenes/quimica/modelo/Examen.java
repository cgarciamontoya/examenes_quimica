/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cgarcia
 */
public class Examen implements Serializable {

    private static final long serialVersionUID = 3015655630483650640L;

    private int id;
    private String nombre;
    private CatMateria materia;
    private int unidad;
    private List<Pregunta> preguntas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CatMateria getMateria() {
        return materia;
    }

    public void setMateria(CatMateria materia) {
        this.materia = materia;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
    
}
