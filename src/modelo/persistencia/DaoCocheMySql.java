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

	public boolean alta(Coche c) {
		if (!abrirConexion()) {
			return false;
		}
		boolean alta = true;

		String query = "insert into coches (MATRICULA,MODELO,COLOR) " + " values(?,?;?)";
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

}
