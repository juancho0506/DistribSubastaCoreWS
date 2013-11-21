/**
 * 
 */
package poligran.jpa.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import poligran.jpa.entities.Articulo;
import poligran.jpa.entities.Usuario;

/**
 * @author Bosz2013
 *
 */
public interface UsuarioDAO {

	public List<Usuario> loadAll()throws PersistenceException;
	
	public Usuario getUsuario(int id)throws PersistenceException;
	
	public void registrarUsuario(Usuario u) throws PersistenceException;
	
	public void actualizarUsuario(Usuario u) throws PersistenceException;
}
