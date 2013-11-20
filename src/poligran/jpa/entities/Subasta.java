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
public class Subasta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	private Integer id;
	
	@Column
	private int precioActual;
	@Column
	private boolean cambioPrecio;
	
	@ManyToOne(targetEntity=Articulo.class)
	@JoinColumn(name="cod_articulo")
	private Articulo articulo;
	
	@ManyToOne(targetEntity=Vendedor.class)
	@JoinColumn(name="id_vendedor")
	private Vendedor vendedor;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPrecioActual() {
		return precioActual;
	}

	public void setPrecioActual(int precioActual) {
		this.precioActual = precioActual;
	}

	public boolean isCambioPrecio() {
		return cambioPrecio;
	}

	public void setCambioPrecio(boolean cambioPrecio) {
		this.cambioPrecio = cambioPrecio;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}
