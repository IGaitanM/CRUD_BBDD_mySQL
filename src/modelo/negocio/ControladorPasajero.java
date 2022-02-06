package modelo.negocio;

import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajeroMySql;


public class ControladorPasajero {
	
	private DaoPasajeroMySql daoPasajero = new DaoPasajeroMySql();
	
	/**
	 * Metodo que da de alta un Pasajero en base de datos.
	 * 
	 * @param p el psajero a dar de alta
	 * @return
	 *         <li>0 en caso de que hayamos dado de alta el pasajero,
	 *         <li>1 en caso de algun error de conexión con la bbdd y
	 */
	public int alta(Pasajero p) {
		boolean alta = daoPasajero.alta(p);
		if (alta) {
			return 0;
		} else {
			return 1;
		}
	}

	
	

}
