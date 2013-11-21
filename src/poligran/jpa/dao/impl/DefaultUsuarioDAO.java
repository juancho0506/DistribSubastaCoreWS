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
		return em.createNamedQuery("usuario.loadAll", Usuario.class).getResultList();
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.UsuarioDAO#getUsuario(int)
	 */
	@Override
	public Usuario getUsuario(int id) throws PersistenceException {
		return em.find(Usuario.class, id);
	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.UsuarioDAO#registrarUsuario(poligran.jpa.entities.Usuario)
	 */
	@Override
	public void registrarUsuario(Usuario u) throws PersistenceException {
		em.getTransaction().begin();
		em.persist(u);
		em.flush();
		em.getTransaction().commit();

	}

	/* (non-Javadoc)
	 * @see poligran.jpa.dao.UsuarioDAO#actualizarUsuario(poligran.jpa.entities.Usuario)
	 */
	@Override
	public void actualizarUsuario(Usuario u) throws PersistenceException {
		em.getTransaction().begin();
		em.merge(u);
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
