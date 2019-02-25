/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.reportes.vo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author cgarcia
 */
public class ExamenReporteVO {

    private String pregunta;
    private List<Map<String, String>> respuesta;
    private int linea;
    private int noCampos;
    
    public ExamenReporteVO() {
        
    }

    
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<Map<String, String>> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<Map<String, String>> respuesta) {
        this.respuesta = respuesta;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getNoCampos() {
        return noCampos;
    }

    public void setNoCampos(int noCampos) {
        this.noCampos = noCampos;
    }
    
    

}
