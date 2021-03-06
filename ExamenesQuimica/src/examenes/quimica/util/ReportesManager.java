/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.util;

import examenes.quimica.excepciones.ExamenesQuimicaException;
import examenes.quimica.reportes.vo.ExamenReporteVO;
import examenes.quimica.reportes.vo.ExamenVO;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;

/**
 * Descripcion:
 * @author cgarcia
 */
public class ReportesManager {
    
    private static final String URL_REPORTES = ConstantesUtil.UNIDAD_RAIZ;
    private static final String RUTA_REPORTES = "/examenes/quimica/reportes/";
    private static final String REPORTE_EXAMEN = "Examen.jasper";
    

    public ReportesManager(Connection con) {
    }
    
    public void generarExamenInstrucciones(String materia, String nombre, List<ExamenVO> examenReporte) throws ExamenesQuimicaException {
        Map<String, Object> parametros = new HashMap<>();
        getLogos(parametros);
        parametros.put("nombre", nombre);
        parametros.put("materia", materia);
        String nombrePdf = nombre + ".pdf";
        exportar(RUTA_REPORTES + REPORTE_EXAMEN, parametros, nombrePdf, examenReporte);
        //abrirPdf(nombrePdf);
    }
    
    private void getLogos(Map<String, Object> parametros) {
            parametros.put("logo", ReportesManager.class.getResourceAsStream(RUTA_REPORTES + "logo_uaz.jpg"));
            parametros.put("escudo", ReportesManager.class.getResourceAsStream(RUTA_REPORTES + "logo_uap.jpg"));
    }
    
    private void abrirPdf(String nombrePdf) {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + nombrePdf);
        } catch(IOException ex) {
            
        }
    }
    
    private void exportar(String nombreReporte, Map<String, Object> parametros, String nombrePdf, List detalle) throws ExamenesQuimicaException {
        try {
            InputStream reporte = ReportesManager.class.getResourceAsStream(nombreReporte);
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(detalle));
            JasperViewer jv = new JasperViewer(jp, false);
            Field jrViewerField = jv.getClass().getDeclaredField("viewer");
            jrViewerField.setAccessible(true);
            JRViewer jrViewer = (JRViewer) jrViewerField.get(jv);
            jrViewer.setSaveContributors(new JRSaveContributor[]{new JRPdfSaveContributor(Locale.getDefault(), null)});
            jv.setTitle(nombrePdf);
            jv.setVisible(true);
            /*File archivo = new File(nombrePdf);
            archivo.createNewFile();
            JasperExportManager.exportReportToPdfFile(jp, nombrePdf);*/
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExamenesQuimicaException("No se pudo generar el reporte debido a: " + ex.getMessage());
        }
    }
    
}
