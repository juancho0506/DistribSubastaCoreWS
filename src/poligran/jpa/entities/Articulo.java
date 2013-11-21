/**
 * 
 */
package poligran.jpa.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * @author Rodrigo
 *
 */
@Entity
@NamedQueries(
	@NamedQuery(name="articulo.loadAll", query="SELECT a FROM Articulo a")
)
public class Articulo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8541136672395066727L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	@Column(name="codigo")
	private Integer codigo;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@Column
	private Integer precioBase;

	@Column
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Integer precioBase) {
		this.precioBase = precioBase;
	}
}
