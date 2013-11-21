/**
 * 
 */
package poligran.jpa.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import poligran.jpa.entities.Articulo;

/**
 * @author Rodrigo
 *
 */
public class DefaultArticuloDAOTest {
	
	DefaultArticuloDAO dao = new DefaultArticuloDAO();
	EntityManagerFactory emf;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Open a database connection
        // (create a new database if it doesn't exist yet):
		try {
			emf = Persistence.createEntityManagerFactory("DistribSubastaCoreEM");
		    dao.setEm(emf.createEntityManager());
		    dao.getEm().setFlushMode(FlushModeType.COMMIT);
		    dao.getEm().getEntityManagerFactory().getCache().evictAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if(emf!=null){
			emf.close();
		}		
	}

	/**
	 * Test method for {@link poligran.jpa.dao.impl.DefaultArticuloDAO#loadAll()}.
	 */
	@Test
	public void testLoadAll() {
		try {
			System.out.println("Articulos: ");
			for (Articulo elem : dao.loadAll()) {
				System.out.println("- "+elem.getNombre());
			}			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for {@link poligran.jpa.dao.impl.DefaultArticuloDAO#getArticulo(int)}.
	 */
	@Test
	public void testGetArticulo() {
		try {
			Articulo a = dao.getArticulo(1);
			assertEquals("Reloj Casio", a.getNombre());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for {@link poligran.jpa.dao.impl.DefaultArticuloDAO#registrarArticulo(poligran.jpa.entities.Articulo)}.
	 */
	@Test
	public void testRegistrarArticulo() {
		Articulo a = new Articulo();
		a.setNombre("Reloj Casio");
		a.setDescripcion("Reloj original casio modelo #####");
		a.setPrecioBase(100000);
		
		try {
			dao.registrarArticulo(a);
			this.testLoadAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for {@link poligran.jpa.dao.impl.DefaultArticuloDAO#actualizarArticulo(poligran.jpa.entities.Articulo)}.
	 */
	@Test
	public void testActualizarArticulo() {
		fail("Not yet implemented");
	}

}
