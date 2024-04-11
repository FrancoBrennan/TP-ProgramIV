package FinalProgramacionIV;

import java.util.Scanner;

//Una empresa quiere desarrollar un sistema para organizar el movimiento de sus
//empleados a otros puestos dentro de la empresa. El sistema también, deberá
//contemplar la convocatoria a los puestos vacantes y la inscripción de los empleados
//para aspirar a un puesto vacante.
//Cuando hay un puesto vacante se crea una convocatoria con los requerimientos para
//el mismo. Para todos los puestos se requiere cierta cantidad mínima de años de
//experiencia en diferentes ítems. Por ejemplo, para un puesto de desarrollador de
//software se requiere como mínimo 2 años de experiencia en programación java, 3
//años en HTML y 1 año en base de datos sql. Si el puesto es jerárquico además se
//requiere cierta cantidad de años mínimo trabajando en la empresa.
//Los empleados jerárquicos deben tener como mínimo 4 años en el puesto actual para
//poder pedir un cambio a un nuevo cargo, mientras que el resto de los empleados
//pueden obtener un nuevo puesto sin importar cuánto tiempo hace que estén en el
//actual.
//Presentación TP de la cursada se requiere definición de los casos de uso y diagrama
//de clases.
//Presentación TP para el final se requiere código completo, prueba del programa y
//defensa oral.

public class Ejecutora {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int op;
		Empresa emp = new Empresa();
		
		do {
			System.out.println("\n*****BIENVENIDO*****");
			System.out.println("1. Agregar empleado");
			System.out.println("2. Agregar puesto");
			System.out.println("3. Agregar experiencia");
			System.out.println("4. Agregar convocatoria");
			System.out.println("5. Agregar inscripto a convocatoria");
			System.out.println("6. Asignar experiencia a puesto");
			System.out.println("7. Asignar experiencia a empleado");
			System.out.println("8. Asignar vacante");
			System.out.println("9. Mostrar empleados");
			System.out.println("10. Mostrar puestos");
			System.out.println("11. mostrarConvocatorias");
			System.out.println("12. mostrarExperiencias");
			System.out.println("13. Dar de baja convocatorias por vencimiento/vacante asignada");
			System.out.println("14. Dar de baja empleado");
			System.out.println("0. Finalizar programa");
			
			System.out.print("\nIngrese su opcion: ");
			op=in.nextInt();
			
			switch(op) {
				case 1: emp.agregarEmpleado(); break;
				case 2: emp.agregarPuesto(); break;
				case 3: emp.agregarExperiencia(); break;
				case 4: emp.agregarConvocatoria(); break;
				case 5: emp.agregarInscriptoAConvocatoria(); break;
				case 6: emp.asignarExperienciaPuesto(); break;
				case 7: emp.asignarExperienciaEmpleado(); break;
				case 8: emp.asignarVacante(); break;
				case 9: emp.mostrarEmpleados(); break;
				case 10: emp.mostrarPuestos(); break;
				case 11: emp.mostrarConvocatorias(); break;
				case 12: emp.mostrarExperiencias(); break;
				case 13: emp.darBajaConvocatorias(); break;
				case 14: emp.darBajaEmpleado(); break;
			}
		}
		while(op!=0);

	}

}
