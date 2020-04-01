package tipoDePago;

import tipoDePago.TipoDePago;

public class Efectivo extends TipoDePago {
	
	@Override
	public float generarPrecio(float Precio) {
		return Precio;
	}

}
