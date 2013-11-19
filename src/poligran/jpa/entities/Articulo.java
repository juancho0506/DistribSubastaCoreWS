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

/**
 * @author Rodrigo
 *
 */
@Entity
public class Articulo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	private Integer codigo;
	
	@Column
	private String descripcion;
	
	@Column
	private Integer precioBase;

	@Column
	public Integer getCodigo() {
		return codigo;
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
