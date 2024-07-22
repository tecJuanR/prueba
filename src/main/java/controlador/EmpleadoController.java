package controlador;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import servicio.Servicio;

@Named
@RequestScoped
public class EmpleadoController {
	
	private static List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	private Empleado empleado;
	private Servicio crud = new Servicio();

	public EmpleadoController() {	
		setEmpleado(new Empleado());
	}
	
	public List<Empleado> consultarEmpleados(){
		listaEmpleados.clear();
		listaEmpleados=crud.consultarEmpleados();
		return listaEmpleados;
	}
	public Empleado consultarEmpleadoId(String id){
		this.empleado = crud.consultarEmpleadoId(id);
		return this.empleado;
	}
	public void crearEmpleado(){
		crud.crearEmpleado(empleado);
		empleado = new Empleado();
		consultarEmpleados();
	}
	public void editarEmpleado(){
		crud.editarEmpleado(empleado);
		empleado = new Empleado();
		consultarEmpleados();
	}
	public void eliminarEmpleado(String id){
		crud.eliminarEmpleado(id);
		empleado = new Empleado();
		consultarEmpleados();
	}
	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
