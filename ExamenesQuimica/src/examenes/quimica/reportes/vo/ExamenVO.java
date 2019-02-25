/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.reportes.vo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cgarcia
 */
public class ExamenVO implements Serializable {

    private static final long serialVersionUID = 6285284174497096810L;

    private String instrucciones;
    private List<ExamenReporteVO> preguntas;
    private int linea;

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public List<ExamenReporteVO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<ExamenReporteVO> preguntas) {
        this.preguntas = preguntas;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    
}
