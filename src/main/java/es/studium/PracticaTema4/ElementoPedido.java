package es.studium.PracticaTema4;


public class ElementoPedido {
	private int idLibro;
	private int cantidad;
	
	public ElementoPedido(int idLibro, int cantidad)
	{
		this.idLibro = idLibro;
		this.cantidad = cantidad;
	}
	
	public int getIdLibro()
	{
		return idLibro;
	}
	public void setIdLibro(int idLibro)
	{
		this.idLibro = idLibro;
	}
	public int getCantidad()
	{
		return cantidad;
	}
	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}
	
	
	public String getTitulo()
	{
		return Libreria.getTitulo(idLibro);
	}
	public double getPrecio()
	{
		return Libreria.getPrecio(idLibro);
	}
	public int getStock()
	{
		return Libreria.getStock(idLibro);
	}
	public String getImagen()
	{
		return Libreria.getImagen(idLibro);
	}
	public String getAutor()
	{
		return Libreria.getAutor(idLibro);
	}
	public String getEditorial()
	{
		return Libreria.getEditorial(idLibro);
	}
}
