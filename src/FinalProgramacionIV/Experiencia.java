package FinalProgramacionIV;

public class Experiencia {
	private String descripcion;
	private int cod;
	
	public Experiencia(String descripcion, int cod) {
		this.descripcion = descripcion;
		this.cod = cod;
	}
	
	public boolean sos(int nro) {
		return this.cod==nro;
	}
	
	public void mostrate() {
		System.out.println("\nCodigo: #"+this.cod);
		System.out.println("Descripcion: "+this.descripcion);
	}
	
	public String getDesc() {
		return this.descripcion;
	}
	
}
