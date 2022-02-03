package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DaoBBDD {

	private Connection conexion;
	private final String NOMBRE_BBDD = "CONECTORES";
	private final String URL = "jdbc:mysql://localhost:3306/";
	private final String USUARIO = "root";
	private final String PASSWORD = "";

	
	
	public boolean crearBBDD() {
		String query = "CREATE DATABASE " + NOMBRE_BBDD;
		try {
			conexion = DriverManager.getConnection(URL+NOMBRE_BBDD,USUARIO,PASSWORD);
			Statement st = conexion.createStatement();
			st.execute("CREATE DATABASE CONECTORES");
			System.out.println("Base de datos creada correctamente");
			cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
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

}
