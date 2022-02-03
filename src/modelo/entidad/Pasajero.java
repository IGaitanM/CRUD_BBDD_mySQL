package modelo.entidad;

public class Pasajero {
	
	private int id;
	private String nombre;
	private int edad;
	private double peso;
	
	
	public Pasajero() {
		super();
	}


	public Pasajero(int id, String nombre, int edad, double peso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
