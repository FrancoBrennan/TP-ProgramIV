package FinalProgramacionIV;

import java.util.Scanner;

import Utilidades.Fecha;

public class Jerarquico extends Puesto{
	private static int minimoAñosPuestoActual;
	private int minimoAñosEmpresa;
	
	public Jerarquico(int nro, String nombre, int minimoAñosEmpresa) {
		super(nro, nombre);
		this.minimoAñosEmpresa=minimoAñosEmpresa;
	}
	
	public int getMinimoAñosPuestoActual() {
		return Jerarquico.dameMinimoAñosPuestoActual();
	}
	
	public int getMinimoAñosEmpresa() {
		return this.minimoAñosEmpresa;
	}
	
	public static int dameMinimoAñosPuestoActual() {
		
		if(minimoAñosPuestoActual==0) {
			Scanner in = new Scanner(System.in);
			
			System.out.println("Ingrese la cantidad minima de años en el puesto actual para poder tener un puesto jerarquico: ");
			minimoAñosPuestoActual=in.nextInt();
		}
		
		return minimoAñosPuestoActual;
	}
	
	public boolean esJerarquico() {
		return true;
	}
	
}
