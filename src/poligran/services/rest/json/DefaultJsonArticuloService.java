/**
 * 
 */
package poligran.services.rest.json;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import poligran.jpa.dao.ArticuloDAO;
import poligran.jpa.dao.impl.DefaultArticuloDAO;
import poligran.jpa.entities.Articulo;
import poligran.services.rest.json.interfaces.JsonArticuloService;

/**
 * @author Bosz2013
 *
 */
@Path("/json/articulos")
@Produces({"text/xml", "application/json"})
public class DefaultJsonArticuloService implements JsonArticuloService {
	
	private ArticuloDAO articuloDao = new DefaultArticuloDAO();

	/* (non-Javadoc)
	 * @see poligran.services.rest.interfaces.ArticuloService#listarArticulos()
	 */
	@GET
	@Override
	public JsonObject listarArticulos() {
		return Json.createObjectBuilder().add("Hello", "World!!").build();
	}

}
