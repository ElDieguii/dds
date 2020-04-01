package estado;

import estado.Estado;

public class Liquidacion extends Estado{
	
	@Override
	public int precioModificadoSegun(int precio) {
		return precio/2;
	}
	
	@Override
	public String estado() {
		return "Liquidacion";
	}
}
