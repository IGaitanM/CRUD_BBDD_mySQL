package crearBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreaBBDD {

	private Connection conexion;
	private final String NOMBRE_BBDD = "CONECTORES";
	private final String URL = "jdbc:mysql://localhost:3306/";
	private final String USUARIO = "root";
	private final String PASSWORD = "";

	public boolean crearBBDD() {
		String query = "CREATE DATABASE " + NOMBRE_BBDD;
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			Statement st = conexion.createStatement();
			st.execute(query);
			System.out.println("Base de datos creada correctamente");
			cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void crearTablas() {
		String pasajeros = "CREATE TABLE PASAJEROS( "
				+ "ID INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "NOMBRE VARCHAR(20) NOT NULL, "
				+ "EDAD INTEGER NOT NULL, "
				+ "PESO DOUBLE NOT NULL, "
				+ "ID_COCHE INTEGER, "
				+ "FOREIGN KEY (ID_COCHE) REFERENCES COCHES(ID))";
		String coches = "CREATE TABLE COCHES( "
				+ "ID INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "MATRICULA VARCHAR(7) NOT NULL, "
				+ "MODELO VARCHAR(20) NOT NULL, "
				+ "COLOR VARCHAR(15) NOT NULL) ";
		abrirConexion();
		try {
			Statement st = conexion.createStatement();
			st.executeUpdate(coches);
			st.executeUpdate(pasajeros);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();

	}

	public boolean abrirConexion() {

		try {
			conexion = DriverManager.getConnection(URL + NOMBRE_BBDD, USUARIO, PASSWORD);
		} catch (SQLException e) {
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
	
	public boolean existeTabla(String tableName) {
		
		boolean tExists = false; 
			try (ResultSet rs = conexion.getMetaData().getTables(null, null, tableName, null)) {
				while (rs.next()) {
					String tName = rs.getString("TABLE_NAME");
						if (tName != null && tName.equals(tableName)) {
							tExists = true; 
							break;
				} 
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return tExists; 
			
	}
	

}
