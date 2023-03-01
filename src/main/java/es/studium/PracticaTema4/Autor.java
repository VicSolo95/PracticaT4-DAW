package es.studium.PracticaTema4;

public class Autor {
	private int id;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	
	
	public Autor()
	{
		setId(0);
		nombre = "";
		primerApellido = "";
		segundoApellido = "";
		
	}
	public Autor(int i, String n, String pA, String sA)
	{
		setId(i);
		nombre = n;
		primerApellido = pA;
		segundoApellido = sA;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
}
