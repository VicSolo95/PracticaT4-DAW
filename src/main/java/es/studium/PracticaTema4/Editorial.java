package es.studium.PracticaTema4;

public class Editorial {
	private int id;
	private String nombre;
	
	public Editorial()
	{
		setId(0);
		nombre = "";
	}
	public Editorial(int i, String n)
	{
		setId(i);
		nombre = n;
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
}
