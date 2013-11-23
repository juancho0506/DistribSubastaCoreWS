/**
 * 
 */
package poligran.services.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.JsonObject;

import poligran.services.rest.interfaces.ArticuloService;

/**
 * Root resource (exposed at "articulos" path)
 */
@Path("articulos")
public class DefaultArticuloService implements ArticuloService {
	
	@Context
    private UriInfo context;
	
	public DefaultArticuloService(){
		
	}

	/* (non-Javadoc)
	 * @see poligran.services.rest.interfaces.ArticuloService#listarArticulos()
	 */
	@GET
    @Produces("application/json")
	@Override
	public JsonObject listarArticulos() {
		//return "<html lang=\"en\"><body><h1>Hello, World!!</h1></body></html>";
		Json json;
	}

}
