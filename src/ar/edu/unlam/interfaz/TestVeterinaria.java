package ar.edu.unlam.interfaz;

import java.util.Scanner;

import ar.edu.unlam.dominio.Atencion;
import ar.edu.unlam.dominio.Especie;
import ar.edu.unlam.dominio.Veterinaria;

public class TestVeterinaria {

	private static final int SALIR= 9;
	private static final int CANTIDAD_MAXIMA_ATENCIONES = 5;

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		String nombreVeterinaria = "UNLaM-Vet";
		mostrarMensaje("Bienvenido a " + nombreVeterinaria);
		
		Veterinaria veterinaria = new Veterinaria(nombreVeterinaria, CANTIDAD_MAXIMA_ATENCIONES);

		int opcion = 0;

		do {
			opcion = seleccionarOpcion(teclado);

			switch (opcion) {
			case 1:
				// Registra una atencion con los datos ingresados por el empleado e informa el resultado
				crearAtencion(teclado, veterinaria);
				break;
			case 2:
				// Se solicita el ingreso del id y se muestra la informacion de la atencion
				// encontrada. Si no se encuentra, mostrar un mensaje
				buscarAtencionPorId(teclado,veterinaria);
				break;
			case 3:
				// Ingresar la especie de mascota para listar las atenciones que correspondan.
				// Si no hay atenciones indicar lo propio
				listarAtencionesPorEspecie(teclado,veterinaria);
				break;
			case 4:
				// Se solicita el ingresdo del id de la atencion y se elimina. Indicar el
				// resultado de la operacion
				eliminarAtencionId(teclado,veterinaria);
				break;
			case 5:
				// Listar las atenciones realizadas y las disponibles
				listarAtencionesDisponibles(veterinaria);
				break;
			case 6:
				// Informar el total de todas las atenciones realizadas. Debe inciar sesión para realizar esta operacion.
				// Si las credenciales son invalidas, indicarlo y volver al menu principal.
				montoTotalDelasAtenciones(teclado,veterinaria);
				break;
			case 7:
				// Informar por pantalla atenciones ordenadas en forma decreciente. Si no hay atenciones mostrar un mensaje aclaratorio.
				// Si las credenciales son invalidas, indicarlo y volver al menu principal.
				mostrarAtencionesDescendenteInicioSesion(teclado,veterinaria);
				break;
			case SALIR:
				mostrarMensaje("Gracias por utilizar el sistema");		
				break;
			}

		} while (opcion != SALIR);
	
		teclado.close();
	}

	/**
	 * Muestra el menu principal y solicita el ingreso de la funcionalidad deseada
	 * 
	 * @param teclado	Para el ingreso de datos
	 * @return opcion seleccionada
	 * */
	private static int seleccionarOpcion(Scanner teclado) {
		
		int opcionSeleccionada = 0;

		mostrarMensaje("\n************************");
		mostrarMensaje("Menu Principal\n");
		mostrarMensaje("1 - Registar una atencion ");
		mostrarMensaje("2 - Buscar atencion por su identificador unico");
		mostrarMensaje("3 - Listar atenciones por una especie determinada");
		mostrarMensaje("4 - Eliminar una atencion por su id");
		mostrarMensaje("5 - Informar la cantidad de atenciones realizadas y las disponibles");
		mostrarMensaje("6 - Infomar el total de todas las atenciones realizadas [Requiere iniciar sesion]");
		mostrarMensaje("7 - Listar atenciones por monto descendente [Requiere iniciar sesion]");
		mostrarMensaje("\n9 - Salir");
		mostrarMensaje("\n************************");
		mostrarMensaje("\nIngrese una opcion");

		opcionSeleccionada = teclado.nextInt();

		return opcionSeleccionada;
	}
	
	private static boolean iniciarSesion(Scanner teclado,Veterinaria veterinaria) {
		System.out.println("ingrese el usuario:");
		String user=teclado.next();
		System.out.println("ingrese la contrasenia: ");
		String contra=teclado.next();
		
		return veterinaria.iniciarSesion(user, contra);
	}
	
	
	private static void mostrarAtencionesDescendenteInicioSesion(Scanner teclado,Veterinaria veterinaria) {
		if(iniciarSesion(teclado,veterinaria)) {
			atencionesPormontoDescendente(veterinaria);
		}else{
			System.out.println("error al iniciar sesion");
		}
	}
	private static void atencionesPormontoDescendente(Veterinaria veterinaria) {
		veterinaria.ordenarAtencionesPorMontoDescendente();
		Atencion atencion[]=veterinaria.getAtenciones();
		
		if(atencion==null) {
			System.out.println("no se encontraron antenciones");
		}
		
		for(int i=0;i<atencion.length;i++) {
			if(atencion[i]!=null) {
				System.out.println(atencion[i].toString());
			}
		}
	}
	
	
	private static void montoTotalDelasAtenciones(Scanner teclado,Veterinaria veterinaria) {
		boolean iniciarSesion=iniciarSesion(teclado,veterinaria);
		if(iniciarSesion) {
			System.out.println("el monto total de todas las atenciones es de: " +veterinaria.obtenerTotalDeAtenciones() + "$");
		}else {
			System.out.println("error al iniciar sesion");
		}
	}
	
	
	
	private static void listarAtencionesDisponibles(Veterinaria veterinaria) {
		System.out.println("atenciones realizadas: " + veterinaria.obtenerCantidadDeAtencionesRealizadas()
		+ "\natenciones disponibles: " + veterinaria.obtenerCantidadDeAtencionesDisponibles(veterinaria.obtenerCantidadDeAtencionesRealizadas()));
	}
	
	private static void eliminarAtencionId(Scanner teclado,Veterinaria veterinaria) {
		System.out.println("ingrese el id de la atencion que desea eliminar");
		int id=teclado.nextInt();
		boolean eliminado=veterinaria.eliminarAtencionPorId(id);
		
		if(eliminado) {
			System.out.println("se elimino la atencion");
		}else {
			System.out.println("no se pudo realizar la operacion");
		}
	}
	
	/**
	 * Solicita el ingreso de la informacion para crear una atencion y la crea
	 * @param teclado	Para el ingreso de datos
	 * */
	private static boolean crearAtencion(Scanner teclado,Veterinaria veterinaria) {
		System.out.println("ingrese el id de la atencion:");
		int id=teclado.nextInt();
		System.out.println("ingrese el nombre del cliente:");
		String cliente=teclado.next();
		System.out.println("ingrese el nombre de la mascota:");
		String mascota=teclado.next();
		Especie especie=elegirEspecie(teclado);
		System.out.println("ingrese el monto de la atencion:");
		double monto=teclado.nextDouble();
		
		Atencion atencion=new Atencion(id,cliente,mascota,especie,monto);
		boolean registro=veterinaria.registrarAtencion(atencion);
		return registro;
	}
	
	private static void listarAtencionesPorEspecie(Scanner teclado,Veterinaria veterinaria) {
		Especie especie=elegirEspecie(teclado);
		Atencion atenciones[]=veterinaria.listarAtencionesPorEspecieDeMascota(especie);
		
		if(atenciones==null) {
			System.out.println("no se encontraron atenciones");
		}
		
		
		for(int i=0;i<atenciones.length;i++) {
			if(atenciones[i]!=null) {
				System.out.println(atenciones[i].toString());
			}
		}
		
		
	}
	
	private static Especie elegirEspecie(Scanner teclado) {
		int opcion=0;
		do {
		System.out.println("elija la especie:"
				+ "\n1 -PERRO"
				+ "\n2 -GATO"
				+ "\n3 -AVE");
		opcion=teclado.nextInt();
		}while(opcion<1 || opcion>3);
		
		Especie especie=Especie.values()[opcion-1];
		return especie;
	}
	
	private static void buscarAtencionPorId(Scanner teclado,Veterinaria veterinaria){
		System.out.println("ingrese el id de la atencion que quiere buscar");
		int id=teclado.nextInt();
		Atencion atencion=veterinaria.buscarAtencionPorId(id);
		
		if(atencion!=null) {
			System.out.println(atencion.toString());
		}else {
			System.out.println("no se encontro la atencion");
		}
	}
	
	
	private static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
}
