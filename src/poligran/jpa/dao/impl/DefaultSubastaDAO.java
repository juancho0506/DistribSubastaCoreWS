/**
 * 
 */
package poligran.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import poligran.jpa.dao.SubastaDAO;
import poligran.jpa.entities.Articulo;
import poligran.jpa.entities.Subasta;
import poligran.jpa.entities.Vendedor;
import poligran.security.Watchdog;

/**
 * @author Rodrigo
 *
 */
public class DefaultSubastaDAO implements SubastaDAO{
	
	private static final String PERSISTENCE_UNIT_NAME = "DistribSubastaCoreEM";
	private EntityManagerFactory entityFactory;
	
	private EntityManager em;	
	
	/**
	 *  Constructor by default, instanciates the entity manager.
	 */
	public DefaultSubastaDAO() {
		entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = entityFactory.createEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		em.getEntityManagerFactory().getCache().evictAll();
	}

	

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.SubastaDAO#loadByVendedor(poligran.jpa.entities.Vendedor)
	 */
	@Override
	public List<Subasta> loadBySeller(Vendedor v) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		Query q = em.createNamedQuery("subasta.loadBySeller", Subasta.class);
		q.setParameter("vendedor", v.getId());
		List<Subasta> l = q.getResultList();
		watchdog.stop();
		return l;
	}


	/* (non-Javadoc)
	 * @see poligran.jpa.dao.SubastaDAO#loadByProduct(poligran.jpa.entities.Articulo)
	 */
	@Override
	public List<Subasta> loadByProduct(Articulo a) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		Query q = em.createNamedQuery("subasta.loadByProduct", Subasta.class);
		q.setParameter("articulo", a.getCodigo());
		List<Subasta> l = q.getResultList();
		watchdog.stop();
		return l;
	}


	/* (non-Javadoc)
	 * @see poligran.jpa.dao.SubastaDAO#getSubasta(int)
	 */
	@Override
	public Subasta getSubasta(int id) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		Subasta s = em.find(Subasta.class, id);
		watchdog.stop();
		return s;
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.SubastaDAO#registrarSubasta(poligran.jpa.entities.Subasta)
	 */
	@Override
	public void registrarSubasta(Subasta s) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		em.getTransaction().begin();
		em.persist(s);
		em.flush();
		em.getTransaction().commit();
		watchdog.stop();
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.SubastaDAO#actualizarSubasta(poligran.jpa.entities.Subasta)
	 */
	@Override
	public void actualizarSubasta(Subasta s) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		em.getTransaction().begin();
		em.merge(s);
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
