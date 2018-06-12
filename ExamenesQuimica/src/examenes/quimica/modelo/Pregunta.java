/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.modelo;

/**
 *
 * @author cgarcia
 */
public class Pregunta {

    private int id;
    private CatRespuesta tipoRespuesta;
    private String pregunta;
    private String opciones;
    private CatMateria materia;
    private int unidad;

    public Pregunta() {
        this.tipoRespuesta = new CatRespuesta();
        this.materia = new CatMateria();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CatRespuesta getTipoRespuesta() {
        return tipoRespuesta;
    }

    public void setTipoRespuesta(CatRespuesta tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
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
    
    
}
