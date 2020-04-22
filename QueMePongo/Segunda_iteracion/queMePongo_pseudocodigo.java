class Institucion{
	List<Uniforme> uniformesAceptados;

	public void configurarUniforme(Prenda superior, Prenda inferior, Prenda calzado){
		try{
			Uniforme unUniforme = new Uniforme(superior, inferior, calzado);
			uniformesAceptados.add(unUniforme);
		}catch(noEsCategoriaConsistenteException){
			//INFORMO QUE ESTA MAL LA CONFIGURACION
		}
	}
}


class Armario{
	List<Uniforme> uniformes;

	public void generarUniformes(){
		/*
			logica de creacion de uniformes con lo que reciba por parametro 
		*/
		return uniformes;
	}
}

class Uniforme{
	Prenda superior, inferior, calzado;

	public void Uniforme(Prenda superior, Prenda inferior, Prenda calzado){
		this.superior = Validaciones.categoriaNoInconsistente(superior,Categoria.PARTE_SUPERIOR, "La prenda ingresada no es una PARTE_SUPERIOR" );
		this.inferior = Validaciones.categoriaNoInconsistente(inferior,Categoria.PARTE_INFERIOR, "La prenda ingresada no es una PARTE_INFERIOR" );
		this.calzado = Validaciones.categoriaNoInconsistente(calzado,Categoria.CALZADO, "La prenda ingresada no es un CALZADO" );
	}

}

class Borrador{
	List<Prenda> borrador ;

	public void guardarBorrador(Prenda prenda){
		borrador.add(prenda);
	}
}  

class Prenda{
	TipoDePrenda tipo;
	Material material;
	Color colorP;
	Color colorSecundario;
	

	public void Prenda(TipoDePrenda tipo, Material material, Color colorP){
		this.tipo = requireNonNull(tipo, "Falta el tipo de prenda");
		this.material = requireNonNull(material, "Falta el material");
		this.colorP = requireNonNull(colorP, "Falta el color principal");
	}

	public void Prenda(TipoDePrenda tipo){
		this.tipo = tipo;
	}

	public void setMaterial_color(Material material, Color colorP){
		if(tipo.esMaterialConsistente(material)){
			this.material=material;
			this.colorP=colorP;
		}else{
			throw new materialInconsistenteException("El material es inconsistente para el tipo de prenda");
		}
		
	}

	public setColorSecundario(Color colorSecundario){
		this.colorSecundario = colorSecundario;
	}


	public guardarPrenda(){
		if(validaciones.esNulo(this.material)){
			throw new atributoFaltanteException("Falta definir el material");
		}
		if(validaciones.esNulo(this.colorP)){
			throw new atributoFaltanteException("Falta definir el color primario");
		}
		//ELSE-> SE GUARDA EN ALGUN LADO
	}
}



class Validaciones(){

	public bool esNulo(Object object){
		return object.isNull();
	}

	public Prenda categoriaNoInconsistente(Prenda unaPrenda,Categoria unaCategoria, String msg ){
		if(unaPrenda.tipo.getCategoria()==unaCategoria){
			return unaPrenda;
		}else{
			throw new noEsCategoriaConsistenteException(msg);
		}
	}
}


class noEsCategoriaConsistenteException extends Exception{
	new (String msg){
		super(msg)
	}
}

class atributoFaltanteException extends Exception{
	new (String msg){
		super(msg)
	}
}

class materialInconsistenteException extends Exception{
	new (String msg){
		super(msg)
	}
}


class TipoDePrenda{
	List<Material> materialesConsistentes //TODO definir;
	Categoria categoria

	public void TipoDePrenda(Categoria categoria){
	this.categoria = categoria;
	}

	method getCategoria(){
	return this.categoria;
	}

	method esMaterialConsistente(Material material){
		materialesConsistentes.contains(material);
	}

	constant ZAPATILLA = new TipoDePrenda(CALZADO);
	constant REMERA =new TipoDePrenda(PARTE_SUPERIOR);
	//Defino todas las prendas aca
}

class Material{
	Trama trama;

	public void Material(Trama trama){
		if(trama==null){
			this.trama=Trama.LISA // ó LISA solo
		}else{
			this.trama = trama;
		}
	}
}


class enum Trama{
	LISA, RAYADA, CON_LUNARES, A_CUADROS, ESTAMPADO
}

class enum Categoria{
	CALZADO, PARTE_SUPERIOR, PARTE_INFERIOR, ACCESORIO
}


class Color{
	int rojo, verde, azul;

	public void Color(int rojo, int verde, int azul){
	this.rojo=rojo;
	this.verde = verde;
	this.azul = azul;
	}
}

