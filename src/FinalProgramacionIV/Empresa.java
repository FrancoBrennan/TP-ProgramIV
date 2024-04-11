package FinalProgramacionIV;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import Utilidades.Fecha;

public class Empresa {
	private ArrayList<Empleado> empleados;
	private ArrayList<Puesto> puestos;
	private ArrayList<Experiencia> experiencias;
	private ArrayList<Convocatoria> convocatorias;
	
	public Empresa() {
		empleados = new ArrayList<Empleado>();
		puestos = new ArrayList<Puesto>();
		experiencias = new ArrayList<Experiencia>();
		convocatorias = new ArrayList<Convocatoria>();
	}
	
	/** Pide al usuario las experiencias para un empleado */
	private void obtenerExperienciasParaEmpleado(Empleado emp) {
		String resp;
		Scanner in = new Scanner(System.in);
		int nroL,nroP,codExp,añosEmp,cant;
		
		System.out.println("Tiene alguna experiencia? (S/N): ");
		resp=in.next();
		
		while(resp.compareToIgnoreCase("S")==0) {
			System.out.println("Ingrese codigo de experiencia del empleado: ");
			codExp=in.nextInt();
			
			Experiencia exp = this.buscarExp(codExp);
			
			if(exp!=null) {
				System.out.println("Ingrese la cantidad de años de experiencia de " + exp.getDesc() + ": ");
				cant=in.nextInt();
				
				emp.agregarExperiencia(exp, cant);
			}
			else {
				System.out.println("La experiencia con codigo #"+codExp+" no existe en el sistema");
			}
			
			System.out.println("Desea agregar otra experiencia al empleado? (S/N): ");
			resp=in.next();
		}
	}
	
	public void agregarEmpleado() {
		Scanner in = new Scanner(System.in);
		int nroL,nroP,codExp,añosEmp,cant, diaEmpresa, mesEmpresa, añoEmpresa, diaPuesto, mesPuesto, añoPuesto;
		String nom,rta,resp;
		
		System.out.println("Ingrese el numero de legajo del empleado por favor: ");
		nroL=in.nextInt();
		
		Empleado e = this.buscarEmp(nroL);
		
		if(e==null) {
			
			System.out.println("Ingrese nombre: ");
			nom=in.next();
			
			// Pedir fecha de ingreso a la empresa
			System.out.println("Ingrese dia de ingreso en la empresa: ");
			diaEmpresa=in.nextInt();
			System.out.println("Ingrese mes de ingreso en la empresa: ");
			mesEmpresa=in.nextInt();
			System.out.println("Ingrese año de ingreso en la empresa: ");
			añoEmpresa=in.nextInt();
			
			Fecha fechaIngresoEmpresa = new Fecha(diaEmpresa, mesEmpresa, añoEmpresa);
			
			Empleado emp = new Empleado(nroL, nom, null, fechaIngresoEmpresa, null); //Pongo como null al puesto y la fecha de ingreso al puesto ya que primero 
																	 //ingreso las experiencias para luego comprobar que dichas experiencias son aptas para el puesto
			
			// Obtiene las experiencias del empleado
			this.obtenerExperienciasParaEmpleado(emp);
			
			System.out.println("Ingrese el numero de puesto: ");
			nroP=in.nextInt();
			
			Puesto p = this.buscarPuesto(nroP);
			
			if(p!=null) {
				
				if(p.estaVacante()==true) {
					
					// Pedir fecha de ingreso al puesto
					System.out.println("Ingrese dia de ingreso al puesto: ");  //Se podría usar la clase Fecha.
					diaPuesto=in.nextInt();
					System.out.println("Ingrese mes de ingreso al puesto: ");
					mesPuesto=in.nextInt();
					System.out.println("Ingrese año de ingreso al puesto: ");
					añoPuesto=in.nextInt();
					
					Fecha fechaIngresoPuesto = new Fecha(diaPuesto, mesPuesto, añoPuesto);
					
					emp.setFechaPuestoActual(fechaIngresoPuesto);
					
					if(p.cumpleCondiciones(emp)) {
						
						p.ocuparPuesto();
						
						emp.setPuesto(p); //Seteo el puesto que estaba en null
						
						empleados.add(emp);
							
						System.out.println("Empleado: "+nom+" --- Puesto: #"+nroP+" --- Fecha de ingreso: " + fechaIngresoPuesto.toString() + " agregado exitosamente");
					}
					else {
						System.out.println("Disculpe, el empleado #"+nroL+" no cumple con las condiciones del puesto #"+nroP);
					}
					
				}
				else {
					System.out.println("El puesto #"+nroP+" ya esta ocupado");
				}
				
			}
			else {
				System.out.println("El numero de puesto "+nroP+" no existe en el sistema");
			}
			
		}
		else {
			System.out.println("El empleado #"+nroL+" ya existe en el sistema");
		}
		
	}
	
	public void agregarPuesto() {
		Scanner in = new Scanner(System.in);
		String nom,rta,resp;
		int op,nro,minAños,cod,cant;
		
		nro=puestos.size()+1;
		
		System.out.println("Ingrese nombre del puesto: ");
		nom=in.next();
		
		System.out.println("Ingrese el tipo de puesto: ");
		System.out.println("1. Jerarquico");
		System.out.println("2. No jerarquico");
		
		System.out.print("\nIngrese su opcion: ");
		op=in.nextInt();
		
		while(op!=1 && op!=2) {
			System.out.println("\nDisculpe, recuerde que las opciones son: ");
			System.out.println("1. Jerarquico");
			System.out.println("2. No jerarquico");
			
			System.out.print("\nIngrese su opcion: ");
			op=in.nextInt();
		}
		
		Puesto p;
		
		if(op==1) {
			System.out.println("Ingrese la cantidad minima de años en la empresa requeridos para el puesto "+nro+": ");
			minAños=in.nextInt();
			
			p = new Jerarquico(nro,nom,minAños);
		}
		else {
			p = new NoJerarquico(nro,nom);
		}
		
		System.out.println("El puesto requiere de alguna experiencia? (S/N): ");
		resp=in.next();
		
		if(resp.compareToIgnoreCase("S")==0) {
			
			do {
				System.out.println("Ingrese codigo de experiencia: ");
				cod=in.nextInt();
				
				Experiencia e = this.buscarExp(cod);
				
				if(e!=null) {
					System.out.println("Ingrese la cantidad de años de experiencia de "+e.getDesc()+": ");
					cant=in.nextInt();
					
					p.agregarExp(e,cant);
				}
				else {
					System.out.println("La experiencia #"+cod+" no existe en el sistema");
				}
				
				System.out.println("Desea agregar otra experiencia para el puesto #" + nro + "? (S/N)");
				rta=in.next();
				
			}
			while(rta.compareToIgnoreCase("S")==0);
			
		}
		
		puestos.add(p);
		
		System.out.println("\nPuesto #"+nro+" agregado exitosamente");
		
	}
	
	public void agregarExperiencia() {
		Scanner in = new Scanner(System.in);
		int cod;
		String desc;
		
		System.out.println("Ingrese codigo de la experiencia: ");
		cod=in.nextInt();
		
		Experiencia e = this.buscarExp(cod);
		
		if(e==null) {
			System.out.println("Ingrese descripcion: ");
			desc=in.next();
			
			Experiencia exp = new Experiencia(desc,cod);
			
			experiencias.add(exp);
			
			System.out.println("Experiencia: " + desc + " agregada exitosamente");
		}
		else {
			System.out.println("\nLa experiencia con codigo #" + cod + " no existe en el sistema");
		}
	}
	
	public void agregarConvocatoria() {
		Scanner in = new Scanner(System.in);
		int cod,nro;
		
		System.out.println("Ingrese codigo de la convocatoria: ");
		cod=in.nextInt();
		
		Convocatoria c = this.buscarConvocatoria(cod);
		
		if(c==null) {
			System.out.println("Ingrese el numero de puesto vacante para la convocatoria: ");
			nro=in.nextInt();
			
			Puesto p = this.buscarPuesto(nro);
			
			if(p!=null) {
				if(p.estaVacante()) {
					if(!p.noTieneExperiencia()) {
						
						System.out.println("Ingrese fecha de la convocatoria: ");
						Fecha f = Fecha.nuevaFecha();
						
						Convocatoria convo = new Convocatoria(cod,p,f);
						
						convocatorias.add(convo);
						
						System.out.println("Convocatoria #"+cod+" --- Puesto #"+nro+" --- Fecha: "+f.toString()+" agregado exitosamente");
						
					}
					else {
						System.out.println("***El puesto ingresado no tiene ninguna experiencia requerida***");
					}
				}
				else {
					System.out.println("El puesto #"+nro+" no esta vacante!");
				}
				
			}
			else {
				System.out.println("El puesto #"+nro+" no existe en el sistema");
			}
		}
		else {
			System.out.println("La convocatoria #"+cod+" ya existe en el sistema");
		}
	}
	
	public void agregarInscriptoAConvocatoria() {
		Scanner in = new Scanner(System.in);
		int cod,nro;
		String rta;
		
		System.out.println("Ingrese codigo de la convocatoria: ");
		cod=in.nextInt();
		
		Convocatoria c = this.buscarConvocatoria(cod);
		
		if(c!=null) {
			
			do {
				System.out.println("Ingrese numero de legajo de empleado: ");
				nro=in.nextInt();
				
				Empleado e = this.buscarEmp(nro);
				
				if(e!=null) {
					
					if(!e.noTieneExperiencia()) {
							
						if(c.verificarCondiciones(e)) {
							Fecha fechaIncripcion = Fecha.nuevaFecha();
								
							Inscripto ins = new Inscripto(e, fechaIncripcion);
								
							if(!c.yaEstaInscripto(ins)) {
								c.agregarInscripto(ins);
									
								System.out.println("Empleado #"+nro+" agregado a la convocatoria #"+cod+" --- Fecha de inscripción: "+ins.getFecha().toString());
							}
							else {
								System.out.println("El empleado #"+nro+" ya se esta inscripto en la convocatoria #"+cod);
							}
								
						}
						else {
							System.out.println("El empleado #"+nro+" no cumple con las condiciones para el puesto #"+c.getPuesto().getNombre());
						}
							
					}
					else {
						System.out.println("***El empleado ingresado no tiene ninguna experiencia***");
					}
					
				}
				else {
					System.out.println("El empleado #"+nro+" no existe en el sistema");
				}
				
				System.out.println("Desea agregado otro empleado a la convocatoria #"+cod+"? (S/N): ");
				rta=in.next();
				
			}
			while(rta.compareToIgnoreCase("S")==0);
			
		}
		else {
			System.out.println("La convocatoria #"+cod+" no existe en el sistema");
		}
		
	}
	
	public void asignarExperienciaPuesto() {
		Scanner in = new Scanner(System.in);
		int nro, cod, cant;
		
		System.out.println("Ingrese numero de puesto: ");
		nro=in.nextInt();
		
		Puesto p = this.buscarPuesto(nro);
		
		if(p!=null) {
			String rta;
			
			do {
				System.out.println("Ingrese codigo de experiencia: ");
				cod=in.nextInt();
				
				Experiencia e = this.buscarExp(cod);
				
				if(e!=null) {
					System.out.println("Ingrese cantidad de años: ");
					cant=in.nextInt();
					
					p.agregarExp(e, cant);
					
					System.out.println("Experiencia #"+cod+" agregada al puesto #"+nro+" exitosamente");
				}
				else {
					System.out.println("El codigo de experiencia #"+cod+" no existe en el sistema");
				}
				
				System.out.println("Desea agregar otra experiencia? (S/N): ");
				rta=in.next();
			}
			while(rta.compareToIgnoreCase("S")==0);
			
		}
		else {
			System.out.println("El puesto #"+nro+" no existe en el sistema");
		}
		
	}
	
	public void asignarExperienciaEmpleado() {
		Scanner in = new Scanner(System.in);
		int nro, cod, cant;
		
		System.out.println("Ingrese numero de legajo de empleado: ");
		nro=in.nextInt();
		
		Empleado e = this.buscarEmp(nro);
		
		if(e!=null) {
			String rta;
			
			do {
				System.out.println("Ingrese codigo de experiencia: ");
				cod=in.nextInt();
				
				Experiencia em = this.buscarExp(cod);
				
				if(em!=null) {
					System.out.println("Ingrese cantidad de años: ");
					cant=in.nextInt();
					
					System.out.println("Experiencia #"+cod+" agregada al empleado #"+nro+" exitosamente");
					
					e.agregarExperiencia(em, cant);
				}
				else {
					System.out.println("El codigo de experiencia #"+cod+" no existe en el sistema");
				}
				
				System.out.println("Desea agregar otra experiencia? (S/N): ");
				rta=in.next();
				
			}
			while(rta.compareToIgnoreCase("S")==0);
			
		}
		else {
			System.out.println("El empleado #"+nro+" no existe en el sistema");
		}
	}
	
	public void asignarVacante() {  // TODO - Pensarlo en grupo. Elige por mayor tiempo en la empresa.
		Scanner in = new Scanner(System.in);
		int cod;
		
		System.out.println("Ingrese el codigo de la convocatoria: ");
		cod=in.nextInt();
		
		Convocatoria c = this.buscarConvocatoria(cod);
		
		if(c!=null) {
			if(!c.yaFueCubiertaVacante()) {
				c.seleccionarInscripto();
			}
			else {
				System.out.println("La vacante ya fue cubierta");
			}
			
		}
		else{
			System.out.println("La convocatoria #"+cod+" no existe en el sistema");
		}
		
	}
	
	public void mostrarEmpleados() {
		for(Empleado e : empleados) {
			System.out.println("\n*****Empleados*****");
			e.mostrate();
		}
	}
	
	public void mostrarPuestos() {
		System.out.println("\n*****Puestos*****");
		for(Puesto p : puestos) {
			p.mostrate();
		}
	}
	
	public void mostrarConvocatorias() {
		System.out.println("\n*****Convocatorias*****");
		for(Convocatoria c : convocatorias) {
			c.mostrate();
		}
	}
	
	public void mostrarExperiencias() {
		System.out.println("\n*****Experiencias*****");
		for(Experiencia e : experiencias) {
			e.mostrate();
		}
	}
	
	public void darBajaConvocatorias() {
		/*
		for(Convocatoria c : convocatorias) {
			if(c.vencida(Fecha.hoy()) || c.yaFueCubiertaVacante()) {
				
				System.out.println("Convocatoria #"+c.getCod()+" eliminada");
				
				Convocatoria eliminar=c;
				
				convocatorias.remove(eliminar);
			}
		}
		*/
		
		int i=0;
		
		do {
			if(convocatorias.get(i).vencida(Fecha.hoy()) || convocatorias.get(i).yaFueCubiertaVacante()) {  //De esta manera no da error en caso de tener una sola 
																											//convocatoria en el arraylist y eliminarla.
				System.out.println("Convocatoria #"+convocatorias.get(i).getCod()+" eliminada");
				
				Convocatoria eliminar=convocatorias.get(i);
				
				convocatorias.remove(eliminar);
			}
			
			i++;
		}
		while(!convocatorias.isEmpty() && i<convocatorias.size());
		
		
	}
	
	public void darBajaEmpleado() {
		Scanner in = new Scanner(System.in);
		int nro;
		
		System.out.println("Ingrese numero de legajo del empleado: ");
		nro=in.nextInt();
		
		Empleado e = this.buscarEmp(nro);
		
		if(e!=null) {
			Puesto p = this.buscarPuesto(e.getNroPuesto());
			
			p.desocuparPuesto();
			
			empleados.remove(e);
			
			System.out.println("\n*****Empleado #"+nro+" dado de baja y puesto #"+p.getNro()+" vacante*****");
		}
		else {
			System.out.println("El empleado #"+nro+" no existe en el sistema");
		}
		
	}
	
	private Empleado buscarEmp(int nro){
		int i=0;
		
		while(i<empleados.size() && !empleados.get(i).sos(nro)) {
			i++;
		}
		
		if(i<empleados.size())
			return empleados.get(i);
		else
			return null;
	}
	
	private Puesto buscarPuesto(int nro){
		int i=0;
		
		while(i<puestos.size() && !puestos.get(i).sos(nro)) {
			i++;
		}
		
		if(i<puestos.size())
			return puestos.get(i);
		else
			return null;
	}
	
	private Experiencia buscarExp(int cod){
		int i=0;
		
		while(i<experiencias.size() && !experiencias.get(i).sos(cod)) {
			i++;
		}
		
		if(i<experiencias.size())
			return experiencias.get(i);
		else
			return null;
	}
	
	private Convocatoria buscarConvocatoria(int cod){
		int i=0;
		
		while(i<convocatorias.size() && !convocatorias.get(i).sos(cod)) {
			i++;
		}
		
		if(i<convocatorias.size())
			return convocatorias.get(i);
		else
			return null;
	}
}
