package poligran.services.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import poligran.jpa.dao.ArticuloDAO;
import poligran.jpa.dao.SubastaDAO;
import poligran.jpa.dao.impl.DefaultArticuloDAO;
import poligran.jpa.dao.impl.DefaultSubastaDAO;
import poligran.jpa.entities.Articulo;
import poligran.jpa.entities.Subasta;
import poligran.services.rest.interfaces.SubastaService;

/**
 * Root resource (exposed at "subasta" path)
 */
@Path("subasta")
public class DefaultSubastaService implements SubastaService {

	@Context
    private UriInfo context;
	
	private SubastaDAO subastaDAO = new DefaultSubastaDAO();
	private ArticuloDAO articuloDAO = new DefaultArticuloDAO();
	
	@Override
	@GET
    @Produces("text/html")
	@Path("/{codArticulo}")
	public String verSubasta(@PathParam("codArticulo") int codArticulo) {
		Subasta subasta = new Subasta();
		Articulo a = new Articulo();
		String response="";
		try {
			response+="<articulo>";
			a = articuloDAO.getArticulo(codArticulo);
			if(a!=null){
				subasta = subastaDAO.loadByProduct(a).get(0);
				response+=a.getCodigo()+";";
				response+=a.getNombre()+";";
				response+=a.getDescripcion()+";";
				response+=a.getPrecioBase()+";";
				response+=subasta.getId()+";";
				response+=subasta.getPrecioActual()+";";
				response+=subasta.getFechaIni()+";";
				response+=subasta.getFechaFin()+"";
				
			}else{
				response+="Error de persistencia: No se pudo obtener el articulo con id:"+codArticulo;
			}
			
		} catch (Exception e) {
			response+="Error de persistencia: "+ e.getMessage();
		}
		response+="<articulo>";
		return response;
	}

}
