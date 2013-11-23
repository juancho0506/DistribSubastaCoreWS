package poligran.jpa.dao.impl;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefaultSubastaDAOTest {
	
	DefaultArticuloDAO dao = new DefaultArticuloDAO();
	EntityManagerFactory emf;

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

	@After
	public void tearDown() throws Exception {
		if(emf!=null){
			emf.close();
		}
	}

	@Test
	public void testLoadBySeller() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadByProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubasta() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistrarSubasta() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualizarSubasta() {
		fail("Not yet implemented");
	}

}
