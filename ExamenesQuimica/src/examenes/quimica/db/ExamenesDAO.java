/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.db;

import examenes.quimica.excepciones.ExamenesQuimicaException;
import examenes.quimica.modelo.CatMateria;
import examenes.quimica.modelo.Examen;
import examenes.quimica.modelo.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgarcia
 */
public class ExamenesDAO extends BaseDAO {
    
    private final PreguntasDAO preguntasDAO;

    public ExamenesDAO(Connection con) {
        super(con);
        preguntasDAO = new PreguntasDAO(con);
    }
    
    public void guardarExamen(Examen examen) throws ExamenesQuimicaException {
        String sb = "insert into examenes (materia, unidad, nombre) values (?,?,?)";
        try {
            PreparedStatement psInsert = getConnection().prepareStatement(sb);
            psInsert.setInt(1, examen.getMateria().getId());
            psInsert.setInt(2, examen.getUnidad());
            psInsert.setString(3, examen.getNombre());
            psInsert.executeUpdate();
            ResultSet gk = psInsert.getGeneratedKeys();
            gk.next();
            int id = gk.getInt(1);
            
            PreparedStatement psrel = getConnection().prepareStatement("insert into rel_examenes_preguntas(id_examen, id_pregunta) values(?,?)");
            for (Pregunta p : examen.getPreguntas()) {
                psrel.setInt(1, id);
                psrel.setInt(2, p.getId());
                psrel.addBatch();
            }
            
            psrel.executeBatch();
        } catch (SQLException ex) {
            throw new ExamenesQuimicaException("No fue posible guardar el examen debido a: " + ex.getMessage());
        }
    }
    
    public void actualizarExamen(Examen examen) throws ExamenesQuimicaException {
        String sb = "update examenes set materia = ?, unidad = ?, nombre = ? where id = ?";
        try {
            PreparedStatement psInsert = getConnection().prepareStatement(sb);
            psInsert.setInt(1, examen.getMateria().getId());
            psInsert.setInt(2, examen.getUnidad());
            psInsert.setString(3, examen.getNombre());
            psInsert.setInt(4, examen.getId());
            
            psInsert.executeUpdate();
            
            PreparedStatement psDel = getConnection().prepareStatement("delete from rel_examenes_preguntas where id_examen = ?");
            psDel.setInt(1, examen.getId());
            psDel.execute();
            
            PreparedStatement psrel = getConnection().prepareStatement("insert into rel_examenes_preguntas(id_examen, id_pregunta) values(?,?)");
            for (Pregunta p : examen.getPreguntas()) {
                psrel.setInt(1, examen.getId());
                psrel.setInt(2, p.getId());
                psrel.addBatch();
            }
            
            psrel.executeBatch();
        } catch (SQLException ex) {
            throw new ExamenesQuimicaException("No fue posible guardar el examen debido a: " + ex.getMessage());
        }
    }
    
    public List<Examen> buscarExamen(int materia, int unidad, String nombre) {
        StringBuilder sb = new StringBuilder();
        sb.append("Select e.id, m.id id_materia, m.nombre nombre_materia, ")
                .append("e.unidad, e.nombre ")
                .append("from examenes e inner join cat_materias m on m.id = e.materia ");
        if (materia > 0) {
            sb.append("where e.materia = ")
                    .append(materia)
                    .append(" ");
        }
        if (unidad > 0) {
            sb.append((sb.toString().contains("where") ? "and " : "where "))
                    .append("e.unidad = ")
                    .append(unidad)
                    .append(" ");
        }
        if (nombre != null && !nombre.isEmpty()) {
            sb.append((sb.toString().contains("where") ? "and " : "where "))
                    .append("lower(e.nombre) like '")
                    .append(nombre.toLowerCase())
                    .append("%' ");
        }
        sb.append("order by nombre_materia, unidad, e.nombre");
        try {
            PreparedStatement ps = getConnection().prepareStatement(sb.toString());
            ResultSet rs = ps.executeQuery();
            
            List<Examen> examenes = new ArrayList<>();
            while (rs.next()) {
                examenes.add(examenMapper(rs));
            }
            return examenes;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    private Examen examenMapper(ResultSet rs) throws SQLException {
        Examen e = new Examen();
        e.setId(rs.getInt("id"));
        e.setMateria(new CatMateria(rs.getInt("id_materia"), rs.getString("nombre_materia")));
        e.setUnidad(rs.getInt("unidad"));
        e.setNombre(rs.getString("nombre"));
        return e;
    }
    
    public Examen buscarPorId(int idExamen) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Select e.id, m.id id_materia, m.nombre nombre_materia, ")
                    .append("e.unidad, e.nombre ")
                    .append("from examenes e inner join cat_materias m on m.id = e.materia ")
                    .append("where e.id = ")
                    .append(idExamen);
            
            ResultSet rs = getConnection().prepareStatement(sb.toString()).executeQuery();
            rs.next();
            Examen examen = examenMapper(rs);
            
            ResultSet rsp = getConnection().prepareStatement("select id_pregunta from rel_examenes_preguntas where id_examen = " + examen.getId()).executeQuery();
            List<Pregunta> preguntas = new ArrayList<>();
            while (rsp.next()) {
                preguntas.add(preguntasDAO.consultaPreguntaId(rsp.getInt("id_pregunta")));
            }
            examen.setPreguntas(preguntas);
            return examen;
        } catch (SQLException ex) {
            return null;
        }
    }
}
