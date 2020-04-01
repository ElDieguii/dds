package estado;

import estado.Estado;

public class Promocion extends Estado{
	protected int valorFijo;
	
	
	
	public float getValorFijo() {
		return valorFijo;
	}



	public void setValorFijo(int valorFijo) {
		this.valorFijo = valorFijo;
	}



	@Override
	public int precioModificadoSegun(int precio) {
		return precio-valorFijo;
	}
	
	@Override
	public String estado() {
		return "Promocion";
	}
}
