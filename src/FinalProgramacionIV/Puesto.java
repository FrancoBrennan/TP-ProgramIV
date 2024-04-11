package FinalProgramacionIV;

import java.util.Enumeration;
import java.util.Hashtable;

import Utilidades.Fecha;

public abstract class Puesto {
	private int nro;
	private String nombre;
	private Hashtable<Experiencia,Integer> añosExperiencia;
	private boolean vacante;
	
	public Puesto(int nro, String nombre) {
		this.nro = nro;
		this.nombre = nombre;
		añosExperiencia = new Hashtable<Experiencia,Integer>(); 
		this.vacante=true;
	}
	
	public boolean sos(int nro) {
		return this.nro==nro;
	}
	
	public void ocuparPuesto() {
		this.vacante=false;
	}
	
	public void desocuparPuesto() {
		this.vacante=true;
	}
	
	public boolean estaVacante() {
		return vacante;
	}
	
	public void agregarExp(Experiencia e,int cant) {
		if(añosExperiencia.containsKey(e)) {
			int valor=añosExperiencia.get(e)+cant;
			
			añosExperiencia.put(e, valor);
		}
		else {
			añosExperiencia.put(e, cant);
		}
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public boolean noTieneExperiencia() {
		return añosExperiencia.isEmpty();
	}
	
	public abstract boolean esJerarquico();
	
	public int getMinimoAñosEmpresa() {
		return 0;
	}
	
	public int getMinimoAñosPuestoActual() {
		return 0;
	}
	
	public boolean cumpleAñosMinimos(Empleado e) {
		if(this.esJerarquico()) {
			return e.cumpleAñosEmpresa(this.getMinimoAñosEmpresa()) && e.cumpleAñosPuesto(this.getMinimoAñosPuestoActual());
		} else {
			return true;
		}
	}
	
	
	public boolean cumpleCondiciones(Empleado e) {
		int cantAños;
		boolean cumpleCondiciones = true;
		
		if(this.cumpleAñosMinimos(e)) {
			Experiencia exp;
			
			Enumeration<Experiencia> expKey = añosExperiencia.keys();
			
			while(expKey.hasMoreElements() && true) {
				exp=expKey.nextElement();
				
				cantAños = añosExperiencia.get(exp);
				
				if(!e.cumpleExperiencia(exp, cantAños)) {
					cumpleCondiciones = false;
				}
			}
		} else {
			cumpleCondiciones = false;
		}
		
		return cumpleCondiciones;
	}
	
	public void mostrate() {
		System.out.println("\nNumero de puesto: " + this.nro);
		System.out.println("Nombre: " + this.nombre);
		System.out.println("Vacante: " + this.vacante);
		
		if (añosExperiencia.isEmpty())
			{
				System.out.println("\nNo tiene experiencias asignadas !! ");
			}
		else
			{
			System.out.println("\nCantidad de experiencias asignadas: "+añosExperiencia.size());	
			Enumeration<Experiencia> listaExp = añosExperiencia.keys();
				System.out.print("\nExperiencias: ");
				while(listaExp.hasMoreElements())
				{
					Experiencia e = listaExp.nextElement();
					e.mostrate();
				}
			}
	}
	
	public int getNro() {
		return this.nro;
	}
	
}
