package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.entidad.Coche;

public class DaoCocheMySql {

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
	 * Método para insertar el registro de un Coche en la tabla COCHES de la BBDD
	 * Abre la conexión, intenta el registro y cierra la conexion con la BBDD
	 * 
	 * @param c EL coche que queremos insrtar en la BBDD
	 * @return true si se ha dado de alta y false si no
	 */
	public boolean alta(Coche c) {
		if (!abrirConexion()) {
			return false;
		}
		boolean alta = true;

		String query = "insert into coches (MATRICULA,MODELO,COLOR) " + " values(?,?,?)";
		try {
			// preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getModelo());
			ps.setString(3, c.getColor());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return alta;
	}

	/**
	 * Método para borrar el registro de un Coche en la tabla COCHES de la BBDD Abre
	 * la conexión, intenta borrar registro y cierra la conexion con la BBDD
	 * 
	 * @param id El id del Coche que queremos borrar de la BBDD
	 * @return true si se ha dado de alta y false si no
	 */
	public boolean baja(int id) {
		if (!abrirConexion()) {
			return false;
		}

		boolean borrado = true;
		String query = "delete from coches where id = ?";
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

}
