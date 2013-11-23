package poligran.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import poligran.jpa.dao.ArticuloDAO;
import poligran.jpa.entities.Articulo;
import poligran.security.Watchdog;

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
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		List<Articulo> l = em.createNamedQuery("articulo.loadAll", Articulo.class).getResultList();
		watchdog.stop();
		return l;
	}

	@Override
	public Articulo getArticulo(int id) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		Articulo a = em.find(Articulo.class, id);
		watchdog.stop();
		return a;
	}

	@Override
	public void registrarArticulo(Articulo a) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		em.getTransaction().begin();
		em.persist(a);
		em.flush();
		em.getTransaction().commit();
		watchdog.stop();
	}

	@Override
	public void actualizarArticulo(Articulo a) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		em.getTransaction().begin();
		em.merge(a);
		em.flush();
		em.getTransaction().commit();
		watchdog.stop();
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
