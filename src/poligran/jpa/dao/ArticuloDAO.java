/**
 * 
 */
package poligran.jpa.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import poligran.jpa.entities.Articulo;

/**
 * @author Bosz2013
 *
 */
public interface ArticuloDAO {
	
	public List<Articulo> loadAll()throws PersistenceException;
	
	public Articulo getArticulo(int id)throws PersistenceException;
	
	public void registrarArticulo(Articulo a) throws PersistenceException;
	
	public void actualizarArticulo(Articulo a) throws PersistenceException;
}
