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

import poligran.jpa.dao.UsuarioDAO;
import poligran.jpa.entities.Usuario;
import poligran.security.Watchdog;

/**
 * @author Bosz2013
 *
 */
public class DefaultUsuarioDAO implements UsuarioDAO {
	
	private static final String PERSISTENCE_UNIT_NAME = "DistribSubastaCoreEM";
	private EntityManagerFactory entityFactory;
	
	private EntityManager em;

	public DefaultUsuarioDAO() {
		entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = entityFactory.createEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		em.getEntityManagerFactory().getCache().evictAll();
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.UsuarioDAO#loadAll()
	 */
	@Override
	public List<Usuario> loadAll() throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		List<Usuario> l = em.createNamedQuery("usuario.loadAll", Usuario.class).getResultList();
		watchdog.stop();
		return l; 
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.UsuarioDAO#getUsuario(int)
	 */
	@Override
	public Usuario getUsuario(int id) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		Usuario l = em.find(Usuario.class, id);
		watchdog.stop();
		return l;
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.UsuarioDAO#registrarUsuario(poligran.jpa.entities.Usuario)
	 */
	@Override
	public void registrarUsuario(Usuario u) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		em.getTransaction().begin();
		em.persist(u);
		em.flush();
		em.getTransaction().commit();
		watchdog.stop();
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.UsuarioDAO#actualizarUsuario(poligran.jpa.entities.Usuario)
	 */
	@Override
	public void actualizarUsuario(Usuario u) throws PersistenceException {
		Watchdog watchdog = new Watchdog(Thread.currentThread());
		em.getTransaction().begin();
		em.merge(u);
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
