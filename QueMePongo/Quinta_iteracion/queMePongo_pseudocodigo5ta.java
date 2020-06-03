public class BufferClima(){
	private static final BufferClima INSTANCE = new BufferClima(null, null);
	private Date horarioProximo=null;
	private int temperatura=null;

	public BufferClima(Date horarioProximo,List<Map<String, Object>> condicionesClimaticas){
		this.horarioProximo=horarioProximo;
		this.condicionesClimaticas=condicionesClimaticas
	}

	public static BufferClima instance(){
		return INSTANCE;
	}
}

//CUARTA ITERACION

public interface ServicioClima(){
	public  int getTemperatura();
}

public final class AccuWeatherAPI{
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

public class proveedorClimaAccuWeatherAPI implements ServicioClima(){
	int temperaturaActual;
	private Date horarioProximo=null;

	public int getTemperatura(){
		if((!horarioProximo)||getDate>horarioProximo){
			AccuWeatherAPI condicionesClimaticas = new AccuWeatherAPI();
			temperaturaActual=condicionesClimaticas.getWeather(“Buenos Aires, Argentina”).get(0).get("Temperatura").get("Value").toDegrees(); 
			horarioProximo=getDate+"12 horas";
			return temperaturaActual;
		}else{
			return temperaturaActual;
		}
	}
}

//ahora vemos
class PrendasTemperatura(){
	ServicioClima servicioClima;

	public PrendasTemperatura(ServicioClima servicioClima){
		this.servicioClima = servicioClima;
	}

	public List<Prenda> obtenerPrendasAcordeATempActual(List<Prenda> prendas){
		prendas.filter(unaPrenda=>unaPrenda.puedeUsarseCon(servicioClima.getTemperatura()));
	}
}

//QUINTE ITERACION

abstract class Guardarropa{

	List<Prenda> prendas;
	Criterio criterio

	public Guardarropa(Criterio criterio,List<Prenda> prendas){
		this.criterio = criterio;
		this.prendas = prendas;
	}

	public getPredasPorCriterio(){
		return criterio.generarPredas(prendas);
	}

	public void agregarPrenda(Prenda prenda){
		prendas.add(prenda);
	}

	public void agregarPrenda(Prenda prenda){
		prendas.add(prenda);
	}
}

class GuardarropaPrivado extends Guardarropa{

	public GuardarropaPrivado(Criterio criterio,List<Prenda> prendas){
		super(criterio,prendas);
	}
}

class GuardarropaCompartido extends Guardarropa{

	public GuardarropaCompartido(Criterio criterio,List<Prenda> prendas){
		super(criterio,prendas);
	}

	List<Propuesta> propuestas;
	List<Propuesta> propuestasAceptadas;

	public proponerAgregarPrenda(Prenda prenda){
		propuestas.add(new PropuestaAgregar(Prenda prenda))
	}

	public proponerQuitarPrenda(Prenda prenda){
		propuestas.add(new PropuestaAgregar(Prenda prenda))
	}

	public aceptarPropuesta(Propuesta propuesta){
		propuestas.remove(propuesta);
		propuestas.aplicarSolicitud(this);
		propuestasAceptadas.add(propuesta);
	}

	public deshacerPropuesta(Propuesta propuesta){
		propuestasAceptadas.remove(propuesta);
		propuesta.deshacerPropuesta()
	}

	public rechazarPropuesta(Propuesta propuesta){
		propuestas.remove(propuesta);
	}

	public List<Propuesta> getPropuestas(){
		return this.propuestas;
	}
}

interface class Propuesta{
	
	public aplicarSolicitud(Guardarropa guardarropa);
	public deshacerPropuesta(Guardarropa guardarropa);
}

class PropuestaAgregar implements Propuesta{

	Prenda prenda;

	public PropuestaAgregar(Prenda prenda){
		this.prenda = prenda;
	}

	public aplicarSolicitud(Guardarropa guardarropa){
		Guardarropa.agregarPrenda(prenda);
	}

	public deshacerPropuesta(Guardarropa guardarropa){
		Guardarropa.quitarPrenda(prenda);
	}
}
class PropuestaQuitar implements Propuesta{

	Prenda prenda;

	public PropuestaQuitar(Prenda prenda){
		this.prenda = prenda;
	}

	public aplicarSolicitud(Guardarropa guardarropa){
		Guardarropa.quitarPrenda(prenda);
	}

	public deshacerPropuesta(Guardarropa guardarropa){
		Guardarropa.agregarPrenda(prenda);
	}

}


//NO SABEMOS QUE CRITERIOS PODRIA LLEGAR A TENER
//PARA FILTRAR LAS PRENDAS DE ESE CRITERIO
abstract class Criterio{
	//CRITERIOS

	public List<Prendas> generarPrendas(List<Prenda> prendas);

}

class RopaDeViaje implements Criterio{

	public List<Prendas> generarPrendas(List<Prenda> prendas){
		return //FILTRA SEGUN CRITERIOS DE ROPA DE VIAJE ;
	}
}


//SEGUNDA ITERACION
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


//PRIMERA ITERACION
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


	public Prenda categoriaNoInconsistente(Prenda unaPrenda,Categoria unaCategoria, String msg ){
		if(unaPrenda.tipo.getCategoria()==unaCategoria){
			return this;
		}else{
			throw new noEsCategoriaConsistenteException(msg);
		}
	}


	public boolean puedeUsarseCon(int temperatura){
		return tipo.puedeUsarseCon(temperatura);
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
	int temperaturaLimite;

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


	public boolean puedeUsarseCon(int temperatura){
		return temperatura>temperaturaLimite;
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

