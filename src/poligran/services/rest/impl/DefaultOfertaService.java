package poligran.services.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import poligran.jpa.dao.OfertaDAO;
import poligran.jpa.dao.SubastaDAO;
import poligran.jpa.dao.UsuarioDAO;
import poligran.jpa.dao.impl.DefaultOfertaDAO;
import poligran.jpa.dao.impl.DefaultSubastaDAO;
import poligran.jpa.dao.impl.DefaultUsuarioDAO;
import poligran.jpa.entities.Oferta;
import poligran.jpa.entities.Subasta;
import poligran.jpa.entities.Usuario;
import poligran.services.rest.interfaces.OfertaService;

@Path("oferta")
public class DefaultOfertaService implements OfertaService {

	@Context
    private UriInfo context;
	
	private SubastaDAO subastaDAO = new DefaultSubastaDAO();
	private OfertaDAO ofertaDAO = new DefaultOfertaDAO();
	private UsuarioDAO usuarioDAO = new DefaultUsuarioDAO();
	
	@Override
	@GET
    @Produces("text/html")
	@Path("/{idUsuario}/{idSubasta}/{valorOferta}")
	public String crearOferta(@PathParam("idUsuario") int idUsuario,@PathParam("idSubasta") int idSubasta, @PathParam("valorOferta")int valorOferta) {
		
		Subasta subasta = new Subasta();
		Oferta oferta = new Oferta();
		Usuario usuario = new Usuario();
		String response = "";
		try {
			usuario = usuarioDAO.getUsuario(idUsuario);
			subasta = subastaDAO.getSubasta(idSubasta);
			try {
				oferta=ofertaDAO.loadByAuctionUser(usuario, subasta);
			} catch (javax.persistence.NoResultException e) {
				oferta = new Oferta();
			}
			
			if(oferta!=null){
				oferta.setUsuario(usuario);
				oferta.setSubasta(subasta);
				oferta.setValorOferta(valorOferta);
				ofertaDAO.actualizarOferta(oferta);
				subasta = subastaDAO.getSubasta(idSubasta);
				if(subasta.getPrecioActual()>valorOferta){
					return "-1";
				}
				subasta.setPrecioActual(valorOferta);
				subastaDAO.actualizarSubasta(subasta);
			}else{
				oferta = new Oferta();
				oferta.setUsuario(usuario);
				oferta.setSubasta(subasta);
				oferta.setValorOferta(valorOferta);
				ofertaDAO.registrarOferta(oferta);
				subasta = subastaDAO.getSubasta(idSubasta);
				if(subasta.getPrecioActual()>valorOferta){
					return "-1";
				}
				subasta.setPrecioActual(valorOferta);
				subastaDAO.actualizarSubasta(subasta);
			}
			response="1";
		} catch (Exception e) {
			e.printStackTrace();
			response="0";
		}
		return response;
	}

}
