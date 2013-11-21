package poligran.jpa.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import poligran.jpa.entities.Articulo;
import poligran.jpa.entities.Subasta;
import poligran.jpa.entities.Vendedor;

public interface SubastaDAO {
	
	public List<Subasta> loadBySeller(Vendedor v)throws PersistenceException;
	
	public List<Subasta> loadByProduct(Articulo a)throws PersistenceException;
	
	public Subasta getSubasta(int id)throws PersistenceException;
	
	public void registrarSubasta(Subasta s) throws PersistenceException;
	
	public void actualizarSubasta(Subasta s) throws PersistenceException;
}
