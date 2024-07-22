package servicio;

import java.util.List;

import controlador.Empleado;
import modelo.Conexion;

public class Servicio {
	public List<Empleado> consultarEmpleados(){
		Conexion conexion = new Conexion();
		return conexion.consultarEmpleados();
	}
	public Empleado consultarEmpleadoId(String id){
		Conexion conexion = new Conexion();
		return conexion.consultarEmpleadoId(id);
	}
	public void crearEmpleado(Empleado empleado){
		Conexion conexion = new Conexion();
		conexion.crearEmpleado(empleado);
	}
	public void editarEmpleado(Empleado empleado){
		Conexion conexion = new Conexion();
		conexion.editarEmpleado(empleado);
	}
	public void eliminarEmpleado(String id){
		Conexion conexion = new Conexion();
		conexion.eliminarEmpleado(id);
	}
}
