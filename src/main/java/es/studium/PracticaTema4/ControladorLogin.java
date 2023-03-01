package es.studium.PracticaTema4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/login")
public class ControladorLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	// Pool de conexiones a la base de datos
	private DataSource pool;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_practicat4db");
			if(pool == null)
			{
				throw new ServletException("DataSource desconocida 'mysql_practicat4db'");
			}
		}
		catch(NamingException ex){}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException
	{
		// Determina a qué página jsp se redirigirá
		String nextPage = "/login.jsp"; // initialize to login page
		String mensaje ="";

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = null;
		Statement stmt = null;

		try
		{
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Recuperar los parámetros usuario y password de la petición request
			String usuario = request.getParameter("formUsuario");
			String password = request.getParameter("formPassword");

			// Validar los parámetros de la petición request
			if(usuario.length()==0)
			{
				mensaje = "<div class='alert alert-danger' role='alert'>No se ha introducido ningún usuario</div>";
				nextPage = "/login.jsp";
			}
			else if(password.length()==0)
			{
				mensaje = "<div class='alert alert-danger' role='alert'>No se ha introducido ninguna contraseña</div>";
				nextPage = "/login.jsp";
			}
			else
			{
				// Verificar que existe el usuario y su correspondiente clave
				StringBuilder sqlStr = new StringBuilder();
				sqlStr.append("SELECT * FROM usuarios WHERE ");
				sqlStr.append("STRCMP(usuarios.nombreUsuario, '").append(usuario).append("') = 0");
				sqlStr.append(" AND STRCMP(usuarios.claveUsuario, MD5('").append(password).append("')) = 0");
				ResultSet rset = stmt.executeQuery(sqlStr.toString());
				if(!rset.next())
				{
					// Si el resultset está vacío
					mensaje = "<div class='alert alert-danger' role='alert'>Nombre de usuario o contraseña incorrectos</div>";
					nextPage = "/login.jsp";
				}
				else
				{
					// Si los datos introducidos son correctos
					// Crear una sesión nueva y guardar el usuario como variable de sesión
					// Primero, invalida la sesión si ya existe
					HttpSession session = request.getSession(false);
					if(session != null)
					{
						session.invalidate();
					}
					session = request.getSession(true);
					synchronized(session)
					{
						session.setAttribute("usuario", usuario);
					}

					//A continuación en función del tipo de usuario nos dirigirá a la parte de cliente o de gestión
					
					int tipoUsuario = rset.getInt(4);

					if(tipoUsuario == 1) {
						// Nos dirigimos a la parte de cliente
						nextPage = "/Controlador";
					}
					else if (tipoUsuario == 0) { 
						//Nos dirigimos a la parte de gestión
						System.out.println("Acceso a gestión");
					}
				}
			}

		}
		catch(SQLException ex)
		{
			mensaje = "<p>Servicio no disponible:</p>" +
					"<p>"+ex.getMessage()+"</p>";
		}
		finally
		{
			// Cerramos objetos

			try
			{
				if(stmt != null)
				{
					stmt.close();
				}
				if(conn != null)
				{
					// Esto devolvería la conexión al pool
					conn.close();
				}
			}
			catch(SQLException ex){}
		}
		// Dispatch the request to the appropriate JSP page
		request.setAttribute("mensaje", mensaje);
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher =
				servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}
} 
