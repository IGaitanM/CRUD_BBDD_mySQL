package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;

public class DaoPasajeroMySql {

	private Connection conexion;

	public boolean abrirConexion() {
		String url = "jdbc:mysql://localhost:3306/conectores";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Método para insertar el registro de un Pasajero en la tabla PASAJEROS de la
	 * BBDD Abre la conexión, intenta el registro y cierra la conexion con la BBDD
	 * 
	 * @param p EL pasajero que queremos insrtar en la BBDD
	 * @return true si se ha dado de alta y false si no
	 */
	public boolean alta(Pasajero p) {
		if (!abrirConexion()) {
			return false;
		}
		boolean alta = true;

		String query = "insert into PASAJEROS (NOMBRE,EDAD,PESO)" + " values(?,?,?)";
		try {

			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return alta;
	}

	/**
	 * Método para borrar el registro de un Pasajero en la tabla PASAJEROS de la
	 * BBDD Abre la conexión, intenta borrar registro y cierra la conexion con la
	 * BBDD
	 * 
	 * @param id El id del PAsajero que queremos borrar.
	 * @return true si se ha dado de alta y false si no
	 */
	public boolean baja(int id) {
		if (!abrirConexion()) {
			return false;
		}

		boolean borrado = true;
		String query = "delete from PASAJEROS where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			// sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja" + " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado;
	}

	/**
	 * Método para consultar el registro de un pasajero en la tabla PASAJEROS de la
	 * BBDD Abre la conexión, intenta consultar el registro y cierra la conexion con
	 * la BBDD
	 * 
	 * @param id El id del Pasajero que queremos consultar de la BBDD
	 * @return pasajero El coche que hemos consultado, null en caso de error de
	 *         conexión con la BBDD
	 */
	public Pasajero obtenerPasajero(int id) {
		if (!abrirConexion()) {
			return null;
		}
		Pasajero pasajero = null;

		String query = "select ID,NOMBRE,EDAD,PESO from PASAJEROS " + "where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el " + "pasajero con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return pasajero;
	}

	/**
	 * Método para consultar todos los registros de la tabla PASAJEROS de la BBDD
	 *  Abre la conexión, intenta consultar y cierra la conexion con la BBDD
	 * 
	 * @return listaPasajeros registros de todos los pasajeros de la tabla PASAJEROS
	 */
	public List<Pasajero> listar() {
		if (!abrirConexion()) {
			return null;
		}
		List<Pasajero> listaPasajeros = new ArrayList<>();

		String query = "SELECT ID,NOMBRE,EDAD,PESO FROM PASAJEROS";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));

				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las " + "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return listaPasajeros;
	}
	
	/**
	 * Método que hace un Update en la tabla PASAJEROS de la BBDD
	 * para asignar una id_coche al pasajero pasado por parámetro por su id
	 *  Abre la conexión, intenta consultar y cierra la conexion con la BBDD
	 * 
	 * @return true si lo ha podido asignar o false si  ha habido algún error
	 */
	public boolean asignarCoche(int idPasajero, int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "UPDATE PASAJEROS SET id_coche=?"
				+ " WHERE id=?";
		try {
			
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al asignar pasajero");
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

}
