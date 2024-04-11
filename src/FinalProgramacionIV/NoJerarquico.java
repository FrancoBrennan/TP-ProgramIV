package FinalProgramacionIV;

public class NoJerarquico extends Puesto{

	public NoJerarquico(int nro, String nombre) {
		super(nro, nombre);
	}

	public boolean esJerarquico() {
		return false;
	}
	
}
