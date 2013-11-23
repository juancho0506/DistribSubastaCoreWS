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

import poligran.jpa.dao.OfertaDAO;
import poligran.jpa.entities.Oferta;
import poligran.jpa.entities.Subasta;
import poligran.jpa.entities.Usuario;
import poligran.security.Watchdog;

/**
 * @author Rodrigo
 *
 */
public class DefaultOfertaDAO implements OfertaDAO {
	
	private static final String PERSISTENCE_UNIT_NAME = "DistribSubastaCoreEM";
	private EntityManagerFactory entityFactory;
	
	private EntityManager em;

	/**
	 * Constructor by default, instanciates the entity manager.
	 */
	public DefaultOfertaDAO() {
		entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = entityFactory.createEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		em.getEntityManagerFactory().getCache().evictAll();
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.OfertaDAO#loadAll()
	 */
	@Override
	public List<Oferta> loadAllByAuction(Subasta s) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		Query q = em.createNamedQuery("oferta.loadAll", Oferta.class);
		q.setParameter("subasta", s.getId());
		List<Oferta> l = q.getResultList();
		watchdog.stop();
		return l;
	}

	@Override
	public Oferta loadByAuctionUser(Usuario u, Subasta s)
			throws PersistenceException {
		
		Query q = em.createNamedQuery("oferta.loadByAuctionUser", Oferta.class);
		q.setParameter("usuario", u.getId());
		q.setParameter("subasta", s.getId());
		return (Oferta) q.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.OfertaDAO#getOferta(int)
	 */
	@Override
	public Oferta getOferta(int id) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		Oferta o = em.find(Oferta.class, id);
		watchdog.stop();
		return o;
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.OfertaDAO#registrarOferta(poligran.jpa.entities.Oferta)
	 */
	@Override
	public void registrarOferta(Oferta a) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		em.getTransaction().begin();
		em.persist(a);
		em.flush();
		em.getTransaction().commit();
		watchdog.stop();
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.OfertaDAO#actualizarOferta(poligran.jpa.entities.Oferta)
	 */
	@Override
	public void actualizarOferta(Oferta a) throws PersistenceException {
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
