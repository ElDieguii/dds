package prendas;

import estado.Estado;

public class Prenda {
	protected String tipo;
	protected Estado estado;
	protected int precio;
	

	public Prenda(Estado estado, int precio, String tipo) {
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public float getPrecio() {
		return precio;
	}



	public void setPrecio(int precio) {
		this.precio = precio;
	}



	public int getPrecioFinal() {
		return estado.precioModificadoSegun(precio);
	}

}
