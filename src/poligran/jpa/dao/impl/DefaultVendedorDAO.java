package poligran.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import poligran.jpa.dao.VendedorDAO;
import poligran.jpa.entities.Usuario;
import poligran.jpa.entities.Vendedor;

public class DefaultVendedorDAO implements VendedorDAO {
	
	private static final String PERSISTENCE_UNIT_NAME = "DistribSubastaCoreEM";
	private EntityManagerFactory entityFactory;
	
	private EntityManager em;

	public DefaultVendedorDAO() {
		entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = entityFactory.createEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		em.getEntityManagerFactory().getCache().evictAll();
	}

	@Override
	public List<Vendedor> loadAll() throws PersistenceException {
		return em.createNamedQuery("vendedor.loadAll", Vendedor.class).getResultList();
	}

	@Override
	public Vendedor getVendedor(int id) throws PersistenceException {
		return em.find(Vendedor.class, id);
	}

	@Override
	public void registrarVendedor(Vendedor v) throws PersistenceException {
		em.getTransaction().begin();
		em.persist(v);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void actualizarVendedor(Vendedor v) throws PersistenceException {
		em.getTransaction().begin();
		em.merge(v);
		em.flush();
		em.getTransaction().commit();
	}

}
