package ar.edu.unlam.dominio;

public class Atencion {

	private int id;
	private String nombreCliente;
	private String nombreMascota;
	private Especie especieMascota; 
	private double monto;
	
	public Atencion(int id, String nombreCliente, String nombreMascota, Especie especieMascota, double monto) {
		this.id = id;
		this.nombreCliente = nombreCliente;
		this.nombreMascota = nombreMascota;
		this.especieMascota = especieMascota;
		this.monto = monto;
	}
	

	@Override
	public String toString() {
		return "Atencion [id=" + id + ", nombreCliente=" + nombreCliente + ", nombreMascota=" + nombreMascota
				+ ", especieMascota=" + especieMascota + ", monto=" + monto + "]";
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreMascota() {
		return nombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}

	public Especie getEspecieMascota() {
		return especieMascota;
	}

	public void setEspecieMascota(Especie especieMascota) {
		this.especieMascota = especieMascota;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	
	
}

