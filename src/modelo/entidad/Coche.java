package modelo.entidad;

import java.io.Serializable;

public class Coche implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -320638518592825896L;
	private int id;
	private String matricula, modelo, color;
	
	public Coche(int id, String matricula, String modelo, String color) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.modelo = modelo;
		this.color = color;
	}

	
	public Coche() {
		super();
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	@Override
	public String toString() {
		return " Id: "+ id + " - Matricula: " + matricula + " - Modelo: " + modelo + " - Color: " + color + "\n";
	}
	
	
	

}
