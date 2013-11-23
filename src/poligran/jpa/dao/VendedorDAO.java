/**
 * 
 */
package poligran.jpa.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import poligran.jpa.entities.Vendedor;

/**
 * @author Bosz2013
 *
 */
public interface VendedorDAO {
	
	public List<Vendedor> loadAll()throws PersistenceException;
	
	public Vendedor getVendedor(int id)throws PersistenceException;
	
	public void registrarVendedor(Vendedor v) throws PersistenceException;
	
	public void actualizarVendedor(Vendedor v) throws PersistenceException;
}
