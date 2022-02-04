package crearBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
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
		String coches = "CREATE TABLE COCHES(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "MATRICULA VARCHAR," + " MARCA VARCHAR)";
		String pasajeros = "CREATE TABLE PASAJEROS(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NOMBRE VARCHAR,"
							+ "EDAD INTEGER," + "PESO DOUBLE," + "ID_COCHE INTEGER FOREIGN KEY(ID) REFERENCES COCHES(ID))";
		abrirConexion();
		try {
			Statement st = conexion.createStatement();
			st.executeUpdate(coches);
			st.executeUpdate(pasajeros);
		} catch (SQLException e) {

			e.printStackTrace();
		}

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
