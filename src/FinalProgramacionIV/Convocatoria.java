package FinalProgramacionIV;

import java.util.Enumeration;
import java.util.Hashtable;

import Utilidades.Fecha;

public class Convocatoria {
	private int cod;
	private Puesto puesto;
	private Fecha fecha;
	private Hashtable<Inscripto,Boolean> inscriptosConvocatoria;
	
	public Convocatoria(int cod, Puesto puesto, Fecha fecha) {
		this.cod = cod;
		this.puesto = puesto;
		this.fecha = fecha;
		this.inscriptosConvocatoria = new Hashtable<Inscripto,Boolean>();
	}
	
	public void seleccionarInscripto() {
		Inscripto seleccionado=null;
		Inscripto ins;
		
		Enumeration<Inscripto> key = inscriptosConvocatoria.keys();
		
		if(inscriptosConvocatoria.isEmpty()) {
			System.out.println("\n***La convocatoria no tiene inscriptos***");
		}
		else {
			while(key.hasMoreElements()) {
				ins=key.nextElement();
				
				if(seleccionado==null) {
					seleccionado=ins;
				}
				else {
					if(seleccionado.comparar(ins)) {
						seleccionado=ins;
					}
				}
				
			}
			inscriptosConvocatoria.put(seleccionado, true);
			
			System.out.println("\n***Inscripto seleccionado***");
			
			seleccionado.mostrate();
			
			seleccionado.getEmpleado().getPuesto().desocuparPuesto(); //Desocupa el puesto actual que tiene
			this.puesto.ocuparPuesto(); //Ocupa el nuevo puesto que tendrá
			
			seleccionado.getEmpleado().setPuesto(puesto);  //Seteo el puesto nuevo
			
		}
	}
	
	public boolean sos(int nro) {
		return this.cod==nro;
	}
	
	public boolean verificarCondiciones(Empleado e) {
		return this.puesto.cumpleCondiciones(e);
	}
	
	public Fecha getFecha() {
		return this.fecha;
	}
	
	public Puesto getPuesto() {
		return this.puesto;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public void agregarInscripto(Inscripto ins) {
		inscriptosConvocatoria.put(ins, false);
	}
	
	public boolean yaEstaInscripto(Inscripto ins) {
		return inscriptosConvocatoria.containsKey(ins);
	}
	
	public void mostrate() {
		System.out.println("\n ");
		System.out.println("\nCodigo: #"+this.cod);
		System.out.println("Puesto: "+this.puesto.getNombre());
		System.out.println("Fecha : "+this.fecha.toString());
		
		if (inscriptosConvocatoria.isEmpty())
		{
			System.out.println("\nNo tiene inscriptos asignados !! ");
		}
	else
		{
		System.out.println("\nCantidad de inscriptos asignados: "+inscriptosConvocatoria.size());	
		Enumeration<Inscripto> listaIns = inscriptosConvocatoria.keys();
			System.out.print("\n Inscriptos: ");
			while(listaIns.hasMoreElements())
			{
				Inscripto ins = listaIns.nextElement();
				ins.mostrate();
			}
		}
	}
	
	public boolean vencida(Fecha f) {
		return fecha.compareTo(f)<0;
	}
	
	public boolean yaFueCubiertaVacante() {
		return inscriptosConvocatoria.containsValue(true);
	}
}
