package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Método para modificar el registro de un Coche en la tabla COCHES de la BBDD
	 * Abre la conexión, intenta modificar registro y cierra la conexion con la BBDD
	 * 
	 * @param id El id del Coche que queremos modificar de la BBDD
	 * @return true si se ha modificado y false si no
	 */
	public boolean modificar(Coche c) {
		if (!abrirConexion()) {
			return false;
		}
		boolean modificado = true;
		String query = "update COCHES set MATRICULA=?, MODELO=?, " + "COLOR=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getModelo());
			ps.setString(3, c.getColor());
			ps.setInt(4, c.getId());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				modificado = false;
			else
				modificado = true;
		} catch (SQLException e) {
			System.out.println("modificar -> error al modificar el coche " + c);
			modificado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return modificado;
	}

	/**
	 * Método para consultar el registro de un coche en la tabla COCHES de la BBDD
	 * Abre la conexión, intenta consultar el registro y cierra la conexion con la
	 * BBDD
	 * 
	 * @param id El id del Coche que queremos consultar de la BBDD
	 * @return coche El coche que hemos consultado, null en caso de error de
	 *         conexión con la BBDD
	 */
	public Coche obtenerCoche(int id) {
		if (!abrirConexion()) {
			return null;
		}
		Coche coche = null;

		String query = "select ID,MATRICULA,MODELO,COLOR from coches " + "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setColor(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return coche;
	}

	/**
	 * Método para consultar todos los registros la tabla COCHES de la BBDD Abre la
	 * conexión, intenta consultar y cierra la conexion con la BBDD
	 * 
	 * @return listaCoches registros de todos los coches de la tabla COCHES
	 */
	public List<Coche> listar() {
		if (!abrirConexion()) {
			return null;
		}
		List<Coche> listaCoches = new ArrayList<>();

		String query = "select ID,MATRICULA,MODELO,COLOR from COCHES";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setColor(rs.getString(4));

				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las " + "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return listaCoches;
	}

}
