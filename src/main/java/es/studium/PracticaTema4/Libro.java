package es.studium.PracticaTema4;

public class Libro {
	private int id;
	private String titulo;
	private double precio;
	private int stock;
	private String imagen;
	private String nombreAutor;
	private String primerApellidoAutor;
	private String segundoApellidoAutor;
	private String nombreEditorial;


	public Libro()
	{
		setId(0);
		titulo = "";
		precio = 0.0;
		stock = 0;
		imagen = "";
		nombreAutor = "";
		primerApellidoAutor = "";
		segundoApellidoAutor = "";
		nombreEditorial = "";
		
	}
	public Libro(int i, String t, double p, int s, String img,
			String nA, String pAA, String sAA, String nE)
	{
		setId(i);
		titulo = t;
		precio = p;
		stock = s;
		imagen = img;
		nombreAutor = nA;
		primerApellidoAutor = pAA;
		segundoApellidoAutor = sAA;
		nombreEditorial = nE;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getNombreAutor() {
		return nombreAutor;
	}
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	
	public String getPrimerApellidoAutor() {
		return primerApellidoAutor;
	}
	public void setPrimerApellidoAutor(String primerApellidoAutor) {
		this.primerApellidoAutor = primerApellidoAutor;
	}
	
	public String getSegundoApellidoAutor() {
		return segundoApellidoAutor;
	}
	public void setSegundoApellidoAutor(String segundoApellidoAutor) {
		this.segundoApellidoAutor = segundoApellidoAutor;
	}
	
	public String getNombreEditorial() {
		return nombreEditorial;
	}
	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}
	
	
	
}
