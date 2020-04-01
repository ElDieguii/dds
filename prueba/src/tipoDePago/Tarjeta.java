package tipoDePago;

import tipoDePago.TipoDePago;

public class Tarjeta extends TipoDePago {
	int cantidadDeCuotas;
	float coeficienteFijo=1;

	@Override
	public float generarPrecio(float Precio) {
		float recargo = (float) ((cantidadDeCuotas*coeficienteFijo)+(0.1*Precio));
		float precioConRecargo = (float) (Precio + recargo);
		return precioConRecargo;
	}
	
}
