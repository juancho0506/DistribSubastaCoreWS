/**
 * 
 */
package poligran.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Rodrigo
 *
 */
@Entity
public class Oferta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	private Integer id;
	
	@Column
	private int valorOferta;
	
	@ManyToOne(targetEntity=Usuario.class)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne(targetEntity=Subasta.class)
	@JoinColumn(name="id_subasta")
	private Subasta subasta;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(int valorOferta) {
		this.valorOferta = valorOferta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Subasta getSubasta() {
		return subasta;
	}

	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}

}
