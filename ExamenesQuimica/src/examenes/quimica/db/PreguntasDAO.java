/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.db;

import examenes.quimica.excepciones.ExamenesQuimicaException;
import examenes.quimica.modelo.CatMateria;
import examenes.quimica.modelo.CatRespuesta;
import examenes.quimica.modelo.Pregunta;
import examenes.quimica.util.ConstantesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgarcia
 */
public class PreguntasDAO extends BaseDAO {

    public PreguntasDAO(Connection con) {
        super(con);
    }
    
    public void guardar(Pregunta pregunta) throws ExamenesQuimicaException {
        String sb = "";
        PreparedStatement ps = null;
        try {
            if (pregunta.getId() == 0) {
                sb = "insert into preguntas(pregunta, tipo_respuesta, opciones, materia, unidad) values(?,?,?,?,?)";
            } else {
                sb = "update preguntas set pregunta = ?, tipo_respuesta = ?, opciones = ?, materia = ?, unidad = ? where id = ?";
            }
            ps = getConnection().prepareStatement(sb);
            ps.setString(1, pregunta.getPregunta());
            ps.setInt(2, pregunta.getTipoRespuesta().getId());
            if (pregunta.getOpciones() != null && !pregunta.getOpciones().isEmpty()) {
                ps.setString(3, pregunta.getOpciones());
            } else {
                ps.setNull(3, Types.VARCHAR);
            }
            ps.setInt(4, pregunta.getMateria().getId());
            ps.setInt(5, pregunta.getUnidad());
            if (pregunta.getId() > 0) {
                ps.setInt(6, pregunta.getId());
            }
            
            ps.execute();
        } catch (SQLException ex) {
            throw new ExamenesQuimicaException("No fue posible guardar la pregunta debido a: " + ex.getMessage());
        }
    }
    
    public List<Pregunta> consultaPreguntas(Pregunta filtros) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select p.id, p.pregunta, p.opciones, p.unidad, ")
                    .append("tr.id id_respuesta, tr.nombre nombre_respuesta, ")
                    .append("m.id id_materia, m.nombre nombre_materia ")
                    .append("from preguntas p inner join cat_respuestas tr on tr.id = p.tipo_respuesta ")
                    .append("inner join cat_materias m on m.id = p.materia ");
            if (filtros != null) {
                if (filtros.getMateria().getId() > 0) {
                    sb.append("where p.materia = ")
                            .append(filtros.getMateria().getId())
                            .append(" ");
                }
                if (filtros.getUnidad() > 0 && filtros.getUnidad() < ConstantesUtil.UNIDAD_ORDINARIO) {
                    sb.append(sb.toString().contains("where") ? "and " : "where ")
                            .append("p.unidad = ")
                            .append(filtros.getUnidad())
                            .append(" ");
                }
                if (filtros.getTipoRespuesta().getId() > 0) {
                    sb.append(sb.toString().contains("where") ? "and " : "where ")
                            .append("p.tipo_respuesta = ")
                            .append(filtros.getTipoRespuesta().getId())
                            .append(" ");
                }
                if (filtros.getPregunta() != null && !filtros.getPregunta().isEmpty()) {
                    sb.append(sb.toString().contains("where") ? "and " : "where ")
                            .append("p.pregunta like '%")
                            .append(filtros.getPregunta())
                            .append("%' ");
                }
            }
            sb.append("order by nombre_materia, unidad, pregunta");
            ResultSet rs = getConnection().prepareStatement(sb.toString()).executeQuery();
            return preguntasMapper(rs);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Pregunta consultaPreguntaId(int id) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select p.id, p.pregunta, p.opciones, p.unidad, ")
                    .append("tr.id id_respuesta, tr.nombre nombre_respuesta, ")
                    .append("m.id id_materia, m.nombre nombre_materia ")
                    .append("from preguntas p inner join cat_respuestas tr on tr.id = p.tipo_respuesta ")
                    .append("inner join cat_materias m on m.id = p.materia where p.id = ?");
            PreparedStatement ps = getConnection().prepareStatement(sb.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Pregunta> preguntas = preguntasMapper(rs);
            return preguntas.get(0);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    private List<Pregunta> preguntasMapper(ResultSet rs) throws SQLException {
        List<Pregunta> preguntas = new ArrayList<>();
        while (rs.next()) {
            Pregunta p = new Pregunta();
            p.setId(rs.getInt("id"));
            p.setPregunta(rs.getString("pregunta"));
            p.setOpciones(rs.getString("opciones"));
            p.setUnidad(rs.getInt("unidad"));
            p.setTipoRespuesta(new CatRespuesta(rs.getInt("id_respuesta"), rs.getString("nombre_respuesta")));
            p.setMateria(new CatMateria(rs.getInt("id_materia"), rs.getString("nombre_materia")));
            preguntas.add(p);
        }
        return preguntas;
    }
}
