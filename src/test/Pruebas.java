package test;

import modelo.persistencia.DaoBBDD;

public class Pruebas {

	public static void main(String[] args) {

		DaoBBDD daoBBDD = new DaoBBDD();
		daoBBDD.crearBBDD();
		
	}

}
