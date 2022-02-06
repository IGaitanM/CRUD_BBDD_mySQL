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

	/**
	 * Metodo que borra un Pasajero por Id .
	 * 
	 * @param id Id del pasajero que queremos borrar.
	 */
	public boolean borrarPorId(int id) {
		boolean baja = daoPasajero.baja(id);
		return baja;
	}
	
	/**
	 * Metodo que consulta un pasajero por su Id.
	 * @param id La Id del pasajero que queremos obtener
	 * @return el pasajero consultado,
	 */
	public Pasajero consultarPorId(int id) {
		Pasajero pasajero = daoPasajero.obtenerPasajero(id);
		return pasajero;
		

	}

	
	

}
