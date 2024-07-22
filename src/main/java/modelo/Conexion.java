package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controlador.Empleado;

public class Conexion implements Transacciones {
	
	private Connection conectar() {
		Connection baseDatos = null;
		String url="jdbc:postgresql://nbappprb:5432/jsr_prueba_crud";
		try {
			baseDatos = DriverManager.getConnection(url, "postgres", "Lucrecia*$18");
			System.out.println("Conexion exitosa");
		}catch(Exception e){
			e.printStackTrace();
		}	
		return baseDatos;
	}
	
	@Override
	public List<Empleado> consultarEmpleados() {
		Connection _conn = null;		
		try {
			_conn = DriverManager.getConnection("jdbc:postgresql://nbappprb:5432/jsr_prueba_crud", "postgres", "Lucrecia*$18");
		}catch(Exception e) {

			e.printStackTrace();
		}

		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		String sql = "SELECT * FROM empleado";

		try {
			Statement stmt = _conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Empleado empleado = new Empleado(rs.getString("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"));
				listaEmpleados.add(empleado);
				System.out.println(rs.getString("nombre"));
			}

		}catch(Exception e) {

			e.printStackTrace();
		}
		System.out.println(listaEmpleados);
		return listaEmpleados;
		
	}

	@Override
	public Empleado consultarEmpleadoId(String id) {
		Connection _conn = null;
		try {
			_conn = DriverManager.getConnection("jdbc:postgresql://nbappprb:5432/jsr_prueba_crud", "postgres", "Lucrecia*$18");
		}catch(Exception e) {

			e.printStackTrace();
		}
		String sql = "SELECT * FROM empleado WHERE id = ?";
		Empleado empleado = null;
		try {
			PreparedStatement stmt = _conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				empleado = new Empleado(rs.getString("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"));
			}

		}catch(Exception e) {

			e.printStackTrace();
		}
		try {
			_conn.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return empleado;
	}

	@Override
	public void crearEmpleado(Empleado empleado) {
		String sql = "INSERT INTO empleado (id,nombre,apellido,telefono,direccion) VALUES (?,?,?,?,?)";
		try {
			Connection _conn = this.conectar();
			PreparedStatement pstmt = _conn.prepareStatement(sql);
			pstmt.setString(1, empleado.getId());
			pstmt.setString(2, empleado.getNombre());
			pstmt.setString(3, empleado.getApellido());
			pstmt.setString(4, empleado.getTelefono());
			pstmt.setString(5, empleado.getDireccion());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	@Override
	public void editarEmpleado(Empleado empleado) {
		String sql = "UPDATE empleado SET nombre = ?,apellido = ?,telefono = ?,direccion = ? WHERE id = ?";
		try {
			Connection _conn = this.conectar();
			PreparedStatement pstmt = _conn.prepareStatement(sql);
			pstmt.setString(1, empleado.getNombre());
			pstmt.setString(2, empleado.getApellido());
			pstmt.setString(3, empleado.getTelefono());
			pstmt.setString(4, empleado.getDireccion());
			pstmt.setString(5, empleado.getId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarEmpleado(String id) {
		String sql = "delete from empleado where id = ?";
		try {
			Connection _conn = this.conectar();
			PreparedStatement pstmt = _conn.prepareStatement(sql);
			pstmt.setString(1, id);		
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		Conexion conexion = new Conexion();
		conexion.consultarEmpleados();
	}

}
