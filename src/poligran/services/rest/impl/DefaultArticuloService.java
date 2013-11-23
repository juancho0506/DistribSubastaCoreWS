/**
 * 
 */
package poligran.services.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import poligran.jpa.dao.ArticuloDAO;
import poligran.jpa.dao.impl.DefaultArticuloDAO;
import poligran.jpa.entities.Articulo;
import poligran.services.rest.interfaces.ArticuloService;

/**
 * Root resource (exposed at "articulos" path)
 */
@Path("articulos")
public class DefaultArticuloService implements ArticuloService {
	
	@Context
    private UriInfo context;
	
	private ArticuloDAO articuloDAO;
	
	public DefaultArticuloService(){
		articuloDAO = new DefaultArticuloDAO();
	}

	/* (non-Javadoc)
	 * @see poligran.services.rest.interfaces.ArticuloService#listarArticulos()
	 */
	@GET
    @Produces("text/html")
	@Override
	public String listarArticulos() {
		List<Articulo> articulos = new ArrayList<>();
		String response = "";
		try {
			articulos = articuloDAO.loadAll();
			response = "<articulos>";
			for (Articulo articulo : articulos) {
				response+=articulo.getCodigo()+";";
				response+=articulo.getNombre()+";";
				response+=articulo.getDescripcion()+";";
				response+=articulo.getPrecioBase()+"";
				response+=";;;";
			}
			response+="<articulos>";
			
		} catch (Exception e) {
			response+="Error de persistencia!";
		}
		return response;
		//return "<html lang=\"en\"><body><h1>Hello, World!!</h1></body></html>";
	}

	@Override
	@GET
	@Path("/{codArticulo}")
	@Produces("text/html")
	public String verArticulo(@PathParam("codArticulo") int codArticulo) {
		
		Articulo art = new Articulo();
		String response="";
		try {
			response+="<articulo>";
			art = articuloDAO.getArticulo(codArticulo);
			response+=art.getCodigo()+";";
			response+=art.getNombre()+";";
			response+=art.getDescripcion()+";";
			response+=art.getPrecioBase()+"";
		} catch (Exception e) {
			// TODO: handle exception
		}
		response+="<articulo>";
		return response;
	}
	
	

}
