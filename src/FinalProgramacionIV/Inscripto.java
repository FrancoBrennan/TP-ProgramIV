package FinalProgramacionIV;

import Utilidades.Fecha;

public class Inscripto {
	private Empleado empleado;
	private Fecha fecha;
	
	public Inscripto(Empleado empleado, Fecha fecha) {
		this.empleado = empleado;
		this.fecha = fecha;
	}
	
	public void mostrate() {
		this.empleado.mostrate();
		System.out.println("\nFecha de inscripcion: "+fecha.toString());
	}
	
	public Empleado getEmpleado() {
		return this.empleado;
	}
	
	public boolean comparar(Inscripto ins) {
		return this.empleado.comparar(ins.getEmpleado());
	}
	
	public Fecha getFecha() {
		return this.fecha;
	}
}
