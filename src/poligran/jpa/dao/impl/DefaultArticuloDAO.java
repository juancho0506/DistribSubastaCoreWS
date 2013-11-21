package poligran.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import poligran.jpa.dao.ArticuloDAO;
import poligran.jpa.entities.Articulo;

public class DefaultArticuloDAO implements ArticuloDAO {
	
	private static final String PERSISTENCE_UNIT_NAME = "DistribSubastaCoreEM";
	private EntityManagerFactory entityFactory;
	
	private EntityManager em;
	
	public DefaultArticuloDAO(){
		entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = entityFactory.createEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		em.getEntityManagerFactory().getCache().evictAll();
	}

	@Override
	public List<Articulo> loadAll() throws PersistenceException {
		return em.createNamedQuery("articulo.loadAll", Articulo.class).getResultList();
	}

	@Override
	public Articulo getArticulo(int id) throws PersistenceException {
		return em.find(Articulo.class, id);
	}

	@Override
	public void registrarArticulo(Articulo a) throws PersistenceException {
		em.getTransaction().begin();
		em.persist(a);
		em.flush();
		em.getTransaction().commit();

	}

	@Override
	public void actualizarArticulo(Articulo a) throws PersistenceException {
		em.getTransaction().begin();
		em.merge(a);
		em.flush();
		em.getTransaction().commit();
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
