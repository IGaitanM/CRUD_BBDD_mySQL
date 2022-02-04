package vista;

import crearBBDD.CreaBBDD;

public class UsuarioConsola {

	public static void main(String[] args) {

		CreaBBDD bbdd = new CreaBBDD();

		if (!bbdd.abrirConexion())
			bbdd.crearBBDD();
		
		bbdd.crearTablas();
	}

}
