/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examenes.quimica.db;

import examenes.quimica.excepciones.ExamenesQuimicaException;
import examenes.quimica.modelo.CatMateria;
import examenes.quimica.modelo.CatRespuesta;
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
public class CatalogosDAO extends BaseDAO {

    public CatalogosDAO() {
        super();
    }
    
    public CatalogosDAO(Connection con) {
        super(con);
    }
    
    public void guardarMateria(CatMateria materia) throws ExamenesQuimicaException {
        try {
            PreparedStatement ps = null;
            if (materia.getId() == 0) {
                validarMateria(materia.getNombre().toUpperCase());
                ps =  getConnection().prepareStatement("insert into cat_materias (nombre) values (?)");
                ps.setString(1, materia.getNombre().toUpperCase());
            } else {
                ps = getConnection().prepareStatement("update cat_materias set nombre = ? where id = ?");
                ps.setString(1, materia.getNombre().toUpperCase());
                ps.setInt(2, materia.getId());
            }
            ps.execute();
        } catch (SQLException ex) {
            throw new ExamenesQuimicaException("No fue posible guardar la materia debido a: " + ex.getMessage());
        }             
    }
    
    private void validarMateria(String nombre) throws ExamenesQuimicaException, SQLException {
        String query = "select count(*) from cat_materias where nombre = '" + nombre + "'";
        ResultSet rs = getConnection().prepareStatement(query).executeQuery();
        rs.next();
        if (rs.getInt(1) > 0) {
            throw new ExamenesQuimicaException("La materia " + nombre.toUpperCase() + " ya existe!!!");
        }
    }
    
    public List<CatMateria> buscarMateria(String nombre) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select id, nombre from cat_materias ");
            if (nombre != null && !nombre.trim().isEmpty()) {
                sb.append("where nombre like '")
                        .append(nombre.toUpperCase())
                        .append("%' ");
            }
            sb.append("order by nombre");
            
            ResultSet rs = getConnection().prepareStatement(sb.toString()).executeQuery();
            List<CatMateria> materias = new ArrayList<>();
            while (rs.next()) {
                materias.add(new CatMateria(rs.getInt("id"), rs.getString("nombre")));
            }
            return materias;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void guardarRespuesta(CatRespuesta respuesta) throws ExamenesQuimicaException {
        try {
            PreparedStatement ps = null;
            if (respuesta.getId() == 0) {
                ps =  getConnection().prepareStatement("insert into cat_respuestas (nombre) values (?)");
                ps.setString(1, respuesta.getNombre().toUpperCase());
            } else {
                ps = getConnection().prepareStatement("update cat_respuestas set nombre = ? where id = ?");
                ps.setString(1, respuesta.getNombre().toUpperCase());
                ps.setInt(2, respuesta.getId());
            }
            ps.execute();
        } catch (SQLException ex) {
            throw new ExamenesQuimicaException("No fue posible guardar la materia debido a: " + ex.getMessage());
        }  
    }
    
    public List<CatRespuesta> buscarRespuesta(String nombre) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select id, nombre from cat_respuestas ");
            if (nombre != null && !nombre.trim().isEmpty()) {
                sb.append("where nombre like '")
                        .append(nombre.toUpperCase())
                        .append("%' ");
            }
            sb.append("order by nombre");
            
            ResultSet rs = getConnection().prepareStatement(sb.toString()).executeQuery();
            List<CatRespuesta> res = new ArrayList<>();
            while (rs.next()) {
                res.add(new CatRespuesta(rs.getInt("id"), rs.getString("nombre")));
            }
            return res;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
