package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	 * Método para insertar el registro de un Pasajero en la tabla PASAJEROS de la BBDD
	 * Abre la conexión, intenta el registro y cierra la conexion con la BBDD
	 * 
	 * @param p EL pasajero que queremos insrtar en la BBDD
	 * @return true si se ha dado de alta y false si no
	 */
	public boolean alta(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into PASAJEROS (NOMBRE,EDAD,PESO)"
				+ " values(?,?,?)";
		try {
			
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}


}
