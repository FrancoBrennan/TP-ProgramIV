package FinalProgramacionIV;

import java.util.Enumeration;
import java.util.Hashtable;

import Utilidades.Fecha;

public class Empleado {
	private int nroLegajo;
	private String nombre;
	private Puesto puesto;
	private Hashtable<Experiencia,Integer> añosExperiencia;
	private Fecha fechaIngresoEmpresa;
	private Fecha fechaIngresoPuestoActual;
	
	public Empleado(int nroLegajo, String nombre, Puesto puesto, Fecha fechaIngresoEmpresa, Fecha fechaIngresoPuestoActual) {
		this.nroLegajo = nroLegajo;
		this.nombre = nombre;
		this.puesto = puesto;
		this.fechaIngresoEmpresa = fechaIngresoEmpresa;
		this.fechaIngresoPuestoActual = fechaIngresoPuestoActual;
		añosExperiencia = new Hashtable<Experiencia,Integer>();
	}
	
	public boolean sos(int nro) {
		return this.nroLegajo==nro;
	}
	
	public void agregarExperiencia(Experiencia e, int cant) {
		
		if(añosExperiencia.containsKey(e)) {
			int valor=añosExperiencia.get(e)+cant;
			añosExperiencia.put(e, valor);
		} else {
			añosExperiencia.put(e, cant);
		}
	}
	
	public boolean noTieneExperiencia() {
		return añosExperiencia.isEmpty();
	}
	
	public void setPuesto(Puesto p) {
		this.puesto=p;
	}
	
	public void setFechaPuestoActual(Fecha f) {
		this.fechaIngresoPuestoActual=f;
	}
	
	public Hashtable<Experiencia,Integer> getExperiencias() {
		return añosExperiencia;
	}
	
	public void mostrate() {
		System.out.println("\n");
		System.out.println("\nNumero de legajo: " + this.nroLegajo);
		System.out.println("Nombre: " + this.nombre);
		System.out.println("Puesto: " + this.puesto.getNombre());
		System.out.println("Años en la empresa: " + this.getAñosEmpresa());
		if (añosExperiencia.isEmpty())
			{
				System.out.println("\nNo tiene experiencias asignadas !! ");
			}
		else
			{
			System.out.println("\nCantidad de experiencias asignadas: "+añosExperiencia.size());	
			Enumeration<Experiencia> listaExp = añosExperiencia.keys();
				System.out.println("\nExperiencias: ");
				while(listaExp.hasMoreElements())
				{
					Experiencia e = listaExp.nextElement();
					e.mostrate();
				}
			}
	}
	
	public int getNroPuesto() {
		return puesto.getNro();
	}
	
	public int getAñosEmpresa() {
		return Fecha.hoy().getAño() - fechaIngresoEmpresa.getAño();
	}
	
	public int getAñosPuesto() {
		return Fecha.hoy().getAño() - fechaIngresoPuestoActual.getAño();
	}

	public Fecha getFecha() {
		return this.fechaIngresoPuestoActual;
	}
	
	public boolean cumpleAñosEmpresa(int años) {
		return this.getAñosEmpresa() > años;
	}
	
	public boolean cumpleAñosPuesto(int años) {
		return this.getAñosPuesto() > años;
	}
	
	public boolean cumpleExperiencia(Experiencia key, int años) {
		return añosExperiencia.containsKey(key) && añosExperiencia.get(key) >= años;
	}
	
	public boolean comparar(Empleado emp) {
		return this.fechaIngresoEmpresa.compareTo(emp.getFecha())>0;
	}
	
	public Puesto getPuesto() {
		return this.puesto;
	}
}
