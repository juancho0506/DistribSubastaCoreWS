/**
 * 
 */
package poligran.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * @author Rodrigo
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="subasta.loadBySeller", query="SELECT s FROM Subasta s WHERE s.vendedor.id =:vendedor"),
	@NamedQuery(name="subasta.loadByProduct", query="SELECT s FROM Subasta s WHERE s.articulo.codigo =:articulo")
})
public class Subasta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5192726531615299284L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	@Column(name="id")
	private Integer id;
	
	@Column
	private int precioActual;
	@Column
	private boolean cambioPrecio;
	
	@Column
	private Date fechaIni;
	@Column
	private Date fechaFin;
	
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
