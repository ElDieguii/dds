public class BufferClima(){
	private static final BufferClima INSTANCE = new BufferClima(null, null);
	private Date horarioProximo=null;
	private List<Map<String, Object>> condicionesClimaticas=null;

	public BufferClima(Date horarioProximo,List<Map<String, Object>> condicionesClimaticas){
		this.horarioProximo=horarioProximo;
		this.condicionesClimaticas=condicionesClimaticas
	}

	public static BufferClima instance(){
		return INSTANCE;
	}

	public List<Map<String, Object>> getClima(ServicioClima servicioClima, String ciudad, String pais){
		if((!horarioProximo)||getDate>horarioProximo){
			condicionesClimaticas = servicioClima.getWeather(“+"ciudad"+, +"pais"+”); 
			horarioProximo=getDate+"12 horas";
			return condicionesClimaticas;
		}else{
			return condicionesClimaticas;
		}
	}
}


public interface ServicioClima(){
	public  float getWeather(String ciudad, String pais);
}

public final class AccuWeatherAPI implements ServicioClima{
    public final List<Map<String, Object>> getWeather(String ciudad) {
        return Arrays.asList(new HashMap(){{
            put("DateTime", "2019-05-03T01:00:00-03:00");
            put("EpochDateTime", 1556856000);
            put("WeatherIcon", 33);
            put("IconPhrase", "Clear");
            put("IsDaylight", false);
            put("PrecipitationProbability", 0);
            put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
            put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
            put("Temperature", new HashMap(){{
                put("Value", 57);
                put("Unit", "F");
                put("UnitType", 18);
            }});
        }});
    }
}

public class CondicionesClimaticas(){

	public List<Map<String, Object>> getClima(ServicioClima servicioClima, String ciudad, String pais){
		BufferClima bufferClima = new BufferClima();
		return bufferClima.getClima;
	}

	public float getTemperatura(ServicioClima servicioClima, String ciudad, String pais){
		return this.getClima(ServicioClima servicioClima, String ciudad, String pais);
	}

}



abstract class Sastre(){
	method crearUniforme(){
		new Uniforme(
				this.crearParteSuperior(),
				this.crearParteInferior(),
				this.crearCalzado()
			)
	}

	protected abstract method crearCalzado();
	protected abstract method crearParteSuperior();
	protected abstract method crearParteInferior();
}

class SastreSanJuan inherits Sastre(){
	method crearCalzado(){
		Borrador borrador = new Borrador(ZAPATILLA);
		......
		return borrador.crearPrenda();
	}
	method crearParteSuperior(){
		Borrador borrador = new Borrador(REMERA);
		......
		return borrador.crearPrenda();
	}
	method crearParteInferior(){
		Borrador borrador = new Borrador(PANTALON);
		......
		return borrador.crearPrenda();
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
	TipoDePrenda tipo;
	Material material;
	Trama trama = Trama.LISA;
	Color colorP;
	Color colorSecundario;
	
	public void Borrador(TipoDePrenda tipoDePrenda){
		this.tipo=requireNonNull(tipoDePrenda, "Falta el tipo de prenda");;
	}
	public void especificarMaterial(Material material){
		if(tipo.esMaterialConsistente(material)){ //TODO PREGUNTAR
			this.material=material;
		}else{
			throw new materialInconsistenteException("El material es inconsistente para el tipo de prenda");
		}
	}

	}
	public void especificarColorPrincipal(Color colorP){
		this.colorP=requireNonNull(colorP, "Falta el color principal");
	}
	public void especificarColorSecundario(Color colorSecundario){
		this.colorSecundario=colorSecundario;
	}

	public Prenda crearPrenda(){
		if(!esNulo(tipo) && !esNulo(material) && !esNulo(colorP)){ //PREGUNTAR
			return new Prenda(tipo, material, colorP, colorSecundario, trama);
		}
		
	}

}  

class Prenda{
	TipoDePrenda tipo;
	Material material;
	Trama trama;
	Color colorP;
	Color colorSecundario;
	
	public void Prenda(TipoDePrenda tipo, Material material, Color colorP, Color colorSecundario, Trama trama){
		this.tipo = tipo
		this.material = material;
		this.colorP = colorP;
		this.colorSecundario = colorSecundario;
		this.trama=trama;
	}

}



class Validaciones(){

	public Object esNulo(Object object){
		if(object!=null){
			return object;
		}else{
			throw new atributoFaltanteException("Falta el atributo: "+object); //VER SI SE PUEDE HACER
		}
		
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
	float temperaturaLimite;

	public void TipoDePrenda(Categoria categoria, float temperaturaLimite){
	this.categoria = categoria;
	this.temperaturaLimite=temperaturaLimite;
	}

	method getCategoria(){
	return this.categoria;
	}

	method esMaterialConsistente(Material material){
		materialesConsistentes.contains(material);
	}

	methodo getTemperaturaLimite(){
		return temperaturaLimite;
	}

	constant ZAPATILLA = new TipoDePrenda(CALZADO, 25);
	constant REMERA =new TipoDePrenda(PARTE_SUPERIOR, 24);
	//Defino todas las prendas aca
}

class enum Material{
	ALGODON, CUERO, LANA
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

