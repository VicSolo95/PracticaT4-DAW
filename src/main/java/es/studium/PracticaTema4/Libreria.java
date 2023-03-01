package es.studium.PracticaTema4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Libreria {
	
	
	static ArrayList<Libro> tablaLibros = new ArrayList<Libro>();
	public static void cargarDatos()
	{
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try
		{
			// Paso 1: Cargamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Paso 2: Conectarse a la base de datos utilizando un objeto de la clase
			//Connection
			String userName = "usuarioPracticaT4";
			String password = "Studium2023;";
			//URL de la base de datos
			String url = "jdbc:mysql://localhost:3306/practicat4db";
			conn = DriverManager.getConnection(url, userName, password);
			
			//Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement
			stmt = conn.createStatement();
			
			//Paso 4: Ejecutar las sentencias
			String sqlStr = "SELECT idLibro, tituloLibro, precioLibro, stockLibro, imagenLibro, nombreAutor, primerApellidoAutor,"
					+ "COALESCE(segundoApellidoAutor,'') AS segundoApellidoAutor, nombreEditorial\r\n"
					+ "FROM libros\r\n"
					+ "JOIN autores ON idAutorFK = idAutor\r\n"
					+ "JOIN editoriales ON idEditorialFK = idEditorial;";
			ResultSet rs = stmt.executeQuery(sqlStr);
			Libro libro;
			while(rs.next())
			{
				libro = new Libro(rs.getInt("idLibro"), rs.getString("tituloLibro"),
						rs.getDouble("precioLibro"), rs.getInt("stockLibro"), rs.getString("imagenLibro"),
						rs.getString("nombreAutor"), rs.getString("primerApellidoAutor"),
						rs.getString("segundoApellidoAutor"), rs.getString("nombreEditorial"));
				tablaLibros.add(libro);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				//Cerramos el resto de recursos
				if(stmt != null)
				{
					stmt.close();
				}
				if(conn != null)
				{
					conn.close();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	//Devuelve el número de libros obtenidos
	 
	public static int tamano()
	{
		return tablaLibros.size();
	}
	
	//Devuelve el título del libro identificado con idLibro
	 
	public static String getTitulo(int idLibro)
	{
		return tablaLibros.get(idLibro).getTitulo();
	}
	
	//Devuelve el precio del libro identificado con idLibro
	
	public static double getPrecio(int idLibro)
	{
		return tablaLibros.get(idLibro).getPrecio();
	}
	
	//Devuelve el stock del libro identificado con idLibro
	 
	public static int getStock(int idLibro)
	{
		return tablaLibros.get(idLibro).getStock();
	}

	//Devuelve la imagen del libro identificado con idLibro
	 
	public static String getImagen(int idLibro)
	{
		return tablaLibros.get(idLibro).getImagen();
	}
	
	//Devuelve el autor completo del libro identificado con idLibro
	 
	public static String getAutor(int idLibro)
	{
		
		return (
				tablaLibros.get(idLibro).getNombreAutor() + " " + 
				tablaLibros.get(idLibro).getPrimerApellidoAutor() + " " + 
				tablaLibros.get(idLibro).getSegundoApellidoAutor()
				);
	}

	//Devuelve la editorial del libro identificado con idLibro
	 
	public static String getEditorial(int idLibro)
	{
		return tablaLibros.get(idLibro).getNombreEditorial();
	}
}
