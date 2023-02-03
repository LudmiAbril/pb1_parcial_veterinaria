package ar.edu.unlam.dominio;

public class Veterinaria {

	private String nombre;
	private Atencion[] atenciones;
	private final String usuario="admin";
	private final String contrasenia="123";
	
	public Veterinaria(String nombreVeterinaria, int cantidadMaximaAtenciones) {
	this.nombre=nombreVeterinaria;
	this.atenciones=new Atencion[cantidadMaximaAtenciones];
	}
	
	/**
	 * Agrega una atenciona al arreglo de atenciones
	 * 
	 * @param atencion Atencion	que se agregara
	 * @return true en caso de exito
	 * */
	public boolean registrarAtencion(Atencion atencion) {
		for(int i=0;i<atenciones.length;i++) {
			if(atenciones[i]==null) {
				atenciones[i]=atencion;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene una atencion por su identificador
	 * 
	 * @param id	Identificador de la atencion
	 * @return atencion o null en caso de no encontrarse
	 * */
	public Atencion buscarAtencionPorId(int id) {
		for(int i=0;i<atenciones.length;i++) {
			if(atenciones[i]!=null && atenciones[i].getId()==id) {
				return atenciones[i];
			}
		}
		return null;
	}
	
	/**
	 * Obtiene atenciones filtradas por la especie de la mascota
	 * 
	 * @return array de atenciones
	 * */
	public Atencion[] listarAtencionesPorEspecieDeMascota(Especie especieMascota){
		Atencion atencionesEspecie[]=new Atencion[atenciones.length];
		int j=0;
		
		for(int i=0;i<atenciones.length;i++) {
			if(atenciones[i]!=null && atenciones[i].getEspecieMascota().equals(especieMascota)) {
				atencionesEspecie[j]=atenciones[i];
				j++;
			}
		}
		
		return atencionesEspecie;
	}
	
	/**
	 * Elimina una atencion por su identificador
	 * 
	 * @param id 	Identificador de la atencion
	 * @return true en caso de exito
	 * */
	public boolean eliminarAtencionPorId(int id) {
		for(int i=0;i<atenciones.length;i++) {
			if(atenciones[i]!=null && atenciones[i].getId()==id) {
				atenciones[i]=null;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene la cantidad de atenciones realizadas hasta el momento
	 * 
	 * @return cantidad de atenciones
	 * */
	public int obtenerCantidadDeAtencionesRealizadas() {
		int atencion=0;
		for(int i=0;i<atenciones.length;i++) {
			if(atenciones[i]!=null) {
				atencion++;
			}
		}
		return atencion;
	}
	
	/**
	 * Obtiene la cantidad de atenciones disponibles
	 * 
	 * @return atenciones
	 * */
	public int obtenerCantidadDeAtencionesDisponibles(int atencionesRealizadas) {
		
		return atenciones.length-atencionesRealizadas;
	}
	
	/**
	 * Calcula y devuelve el total de todas las atenciones realizadas
	 * 
	 * @return monto total
	 * */
	public double obtenerTotalDeAtenciones() {
		double montoTotal=0.0;
		for(int i=0;i<atenciones.length;i++) {
			if(atenciones[i]!=null) {
				montoTotal+=atenciones[i].getMonto();
			}
		}
		
		return Math.round(montoTotal*100)/100;
	}
	
	/**
	 * Obtiene atenciones ordenadas por monto descendente
	 * 
	 * @return atenciones
	 * */
	public void ordenarAtencionesPorMontoDescendente() {
		Atencion aux;
		for(int i=1;i<atenciones.length;i++) {
			for(int j=0;i<atenciones.length-1;i++) {
				if(atenciones[j]!=null && atenciones[j+1]!=null && atenciones[j].getMonto()<atenciones[j+1].getMonto()) {
					aux=atenciones[j];
					atenciones[j]=atenciones[j+1];
					atenciones[j+1]=aux;
				}
			}
		}
	}
	
	/**
	 * Valida las credenciales proporcionadas
	 * 
	 * @param nombreUsuario		Nombre de usuario del administrador
	 * @param contrasenia		Contrasenia del administrador
	 * @return verdadero en caso de exito
	 * */
	public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
		if(nombreUsuario.equals(this.usuario) && contrasenia.equals(this.contrasenia)) {
			return true;
		}
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Atencion[] getAtenciones() {
		return atenciones;
	}

	public void setAtenciones(Atencion[] atenciones) {
		this.atenciones = atenciones;
	}
	
	
	
}
