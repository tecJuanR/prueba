package modelo;

import java.util.List;

import controlador.Empleado;

public interface Transacciones {
	public List<Empleado> consultarEmpleados();
	public Empleado consultarEmpleadoId(String id);
	public void crearEmpleado(Empleado empleado);
	public void editarEmpleado(Empleado empleado);
	public void eliminarEmpleado(String id);
}
