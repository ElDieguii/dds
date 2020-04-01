package venta;


import java.util.Date;
import java.util.List;
import prendas.Prenda;
import tipoDePago.TipoDePago;

public class Venta {
	protected List<Prenda> prendas;
	protected Date fecha;
	protected TipoDePago tipoDePago;
	
	
	
	
	public Venta(List<Prenda> prendas, Date fecha, TipoDePago tipoDePago) {
		super();
		this.prendas = prendas;
		this.fecha = fecha;
		this.tipoDePago = tipoDePago;
	}
	
	
	public List<Prenda> getPrendas() {
		return prendas;
	}
	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public TipoDePago getTipoDePago() {
		return tipoDePago;
	}
	public void setTipoDePago(TipoDePago tipoDePago) {
		this.tipoDePago = tipoDePago;
	}
	
	public int calcularCantidadDePrendas() {
		int cantidadDePrendas = prendas.size();
		return cantidadDePrendas;
	}
	public float calcularPrecio() {
		int precioTotal = prendas.stream().mapToInt(prenda->prenda.getPrecioFinal()).sum();
		return precioTotal;
	}
	public static void registrarVenta() {
		//todo 
	}

}
