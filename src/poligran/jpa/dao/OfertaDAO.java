package poligran.jpa.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import poligran.jpa.entities.Oferta;
import poligran.jpa.entities.Subasta;
import poligran.jpa.entities.Usuario;

public interface OfertaDAO {
	
	public List<Oferta> loadAllByAuction(Subasta s)throws PersistenceException;
	
	public Oferta loadByAuctionUser(Usuario u, Subasta s) throws PersistenceException;
	
	public Oferta getOferta(int id)throws PersistenceException;
	
	public void registrarOferta(Oferta a) throws PersistenceException;
	
	public void actualizarOferta(Oferta a) throws PersistenceException;
}
