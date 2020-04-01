package estado;

import estado.Estado;

public class Nueva extends Estado{
	
	@Override
	public int precioModificadoSegun(int precio) {
		return super.precioModificadoSegun(precio);
	}
	
	@Override
	public String estado() {
		return "Nuevo";
	}
	
	
}
