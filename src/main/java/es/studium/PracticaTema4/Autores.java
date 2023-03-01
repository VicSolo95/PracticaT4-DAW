package es.studium.PracticaTema4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Autores {

	static ArrayList<Autor> tablaAutores = new ArrayList<Autor>();
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
			String sqlStr =  "SELECT idAutor, nombreAutor, primerApellidoAutor,"
					+ "COALESCE(segundoApellidoAutor,'') AS segundoApellidoAutor "
					+ "FROM autores;";
			ResultSet rs = stmt.executeQuery(sqlStr);
			Autor autor;
			while(rs.next())
			{
				autor = new Autor(rs.getInt("idAutor"), rs.getString("nombreAutor"),
						rs.getString("primerApellidoAutor"), rs.getString("segundoApellidoAutor"));
				tablaAutores.add(autor);
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

	//Devuelve el número de autores obtenidos

	public static int tamano()
	{
		return tablaAutores.size();
	}

	//Devuelve el nombre del autor identificado con idAutor

	public static String getNombre(int idAutor)
	{
		return tablaAutores.get(idAutor).getNombre();
	}

	//Devuelve los apellidos del autor identificado con idAutor

	public static String getApellidos(int idAutor)
	{
		String apellidos = tablaAutores.get(idAutor).getPrimerApellido() + " " +
				tablaAutores.get(idAutor).getSegundoApellido();
		
		return apellidos;
	}
}
