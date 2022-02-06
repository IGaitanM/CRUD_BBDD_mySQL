package modelo.negocio;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;

public class ControladorCoche {

	public int contador;
	private DaoCocheMySql daoCoche = new DaoCocheMySql();

	/**
	 * Metodo que da de alta un COCHE en base de datos.
	 * 
	 * @param c el coche a dar de alta
	 * @return 0 en caso de que hayamos dado de alta el coche, 1 en caso de algun
	 *         error de conexión con la bbdd y 2 en caso de que la matricula del
	 *         coche ya exista en la BBDD
	 */
	public int alta(Coche c) {
		boolean alta = daoCoche.alta(c);
		if (alta) {
			return 0;
		} else {
			return 1;

		}
	}

	/**
	 * Metodo que lista los coches contenidos en la BBDD.
	 */

	public void listarCoches() {

	}

	/**
	 * Metodo que borra un Coche por Id e imprime el coche borrado.
	 */
	public boolean BorrarPorId(int id) {

		return false;
	}

	/**
	 * Metodo que consulta un coche por Id y lo imprime.
	 */
	public Coche consultaPorId(int id) {
		return null;

	}

}
