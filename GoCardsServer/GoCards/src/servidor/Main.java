package servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import modelos.Carta;
import modelos.Partida;

@Path("main")

public class Main {

	@GET
	@Path("login/{user}/{pass}")
	public Response login(@PathParam("user") String user, @PathParam("pass") String pass) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			
			if (sentencia.execute("SELECT nombre, pass FROM jugador WHERE nombre LIKE '"+user+"' AND pass LIKE '"+pass+"'")) {
				conexion.close();
				String mess = "Bienvenido "+user+" a la guerra de cartas de coches.";
				return Response.status(Response.Status.OK).entity(mess).build();
			} else {
				conexion.close();
				String mess = "El nombre o contraseņa introducidas son incorrectas.";
				return Response.status(Response.Status.NOT_FOUND).entity(mess).build();
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String mess = "Internal server error.";
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mess).build();
		}

	}
	
	@POST
	@Path("newGame")
	@Produces(MediaType.APPLICATION_JSON)
	public Response newGame(@FormParam("userId") int userId) {
		
		try {
			Partida p = new Partida(userId, 0);
			Gson g = new Gson();
			ResultSet rs;
			String s;
			ArrayList<Carta> cartas = new ArrayList<Carta>();
			Carta[] cliente = new Carta[6];
			Carta[] cpu =new Carta[6];
			

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("INSERT INTO partida (jugadorId, resultado) VALUES ('"+p.getJugadorId()+"','"+p.getResultado()+"')");
			rs = sentencia.executeQuery("SELECT * FROM carta");
			

			while (rs.next()) {
				String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				String pais = rs.getString("pais");
				int motor = rs.getInt("motor");
				int cilindros = rs.getInt("cilindros");
				int potencia = rs.getInt("potenciaKw");
				int revoluciones = rs.getInt("revolucionesMin");
				int velocidad = rs.getInt("velocidad");
				double consumo = rs.getDouble("consumo");
				
				Carta c = new Carta(id, nombre, pais, motor, cilindros, potencia, revoluciones, velocidad, consumo);
				cartas.add(c);
			}
			conexion.close();
			int k = 0, m = 0;
			for (int i = 0; i < 12; i++) {
				int j = (int)(Math.random() * cartas.size());
				if (i%2==0) {
					cliente[k] = cartas.get(j);
					k++;
				}else {
					cpu[m] = cartas.get(j);
					m++;
				}
			}
			
			s=g.toJson(p)+"\n"+g.toJson(cliente);
			return Response.status(Response.Status.OK).entity(s).build();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String mess = "Internal server error.";
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mess).build();
		}
		
	}
	
	@POST
	@Path("firstCard")
	@Produces(MediaType.TEXT_PLAIN)
	public Response primero() {
		if (Math.random() * 2 == 1) {
			return Response.status(Response.Status.OK).entity("player").build();
		}else {
			return Response.status(Response.Status.OK).entity("cpu").build();
		}
		
	}
	
	@POST
	@Path("handResult")
	@Produces(MediaType.APPLICATION_JSON)
	public Response handResult(@FormParam("idCartaCli") String idCartaCli, @FormParam("idCartaCpu") String idCartaCpu, @FormParam("artibuto") String atributo) {
		
		
		return null;
		
	}
	
	public Carta playCard(Carta[] cartas) {
		
		
		return null;
		
	}
	
	/*CRUD*/
	
	@POST
	@Path("newUser/{n}/{p}")
	public void newUser(@PathParam("n") String name, @PathParam("p") String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("INSERT INTO jugador (nombre, pass) VALUES ('"+name+"','"+pass+"' )");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PUT
	@Path("alterUser/{u}/{nu}/{p}")
	public void alterUser(@PathParam("u") String userId, @PathParam("nu") String newUserName, @PathParam("p") String newPass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.executeUpdate("UPDATE jugador SET nombre = '"+newUserName+"', pass = '"+newPass+"' WHERE (id = '"+userId+"')");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Path("delUser/{u}")
	public void delUser(@PathParam("u") String userId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("DELETE FROM jugador WHERE (id = '"+userId+"')");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("newCard")
	public void newCard(@FormParam("id") String id, @FormParam("nombre") String nombre, @FormParam("pais") String pais, @FormParam("motor") int motor, @FormParam("cilindros") int cilindros, @FormParam("potencia") int potencia, @FormParam("revoluciones") int revoluciones, @FormParam("velocidad") int velocidad, @FormParam("consumo") double consumo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("INSERT INTO carta (id, nombre, pais, motor, cilindros, potenciaKw, revolucionesMin, velocidad, consumo) VALUES ('"+id+"','"+nombre+"','"+pais+"','"+motor+"','"+cilindros+"','"+potencia+"','"+revoluciones+"','"+velocidad+"','"+consumo+"' )");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PUT
	@Path("alterCard")
	public void alterCard(@FormParam("id") String id, @FormParam("nombre") String nombre, @FormParam("pais") String pais, @FormParam("motor") int motor, @FormParam("cilindros") int cilindros, @FormParam("potencia") int potencia, @FormParam("revoluciones") int revoluciones, @FormParam("velocidad") int velocidad, @FormParam("consumo") double consumo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("UPDATE carta SET nombre = '"+nombre+"',pais = '"+pais+"',motor = '"+motor+"',cilindros = '"+cilindros+"',potenciaKw = '"+potencia+"',revolucionesMin = '"+revoluciones+"',velocidad = '"+velocidad+"',consumo = '"+consumo+"' WHERE (id = '"+id+"') ");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Path("delCard")
	public void delCard(@FormParam("id") String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("DELETE FROM carta WHERE (id = '"+id+"')");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("newGame/{p}/{r}")
	public void newGameT(@PathParam("p") int idP, @PathParam("r") int result) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("INSERT INTO partida (jugadorId, resultado) VALUES ('"+idP+"','"+result+"')");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PUT
	@Path("alterGame/{id}/{p}/{r}")
	public void alterGameT(@PathParam("id") int id, @PathParam("p") int idP, @PathParam("r") int result) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("UPDATE partida SET jugadorId = '"+idP+"',resultado = '"+result+"' WHERE (id = '"+id+"') ");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Path("delGame/{id}")
	public void delGameT(@PathParam("id") int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("DELETE FROM partida WHERE (id = '"+id+"')");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("newStats")
	public void newEstadisticas(@FormParam("jugadorId") int jugadorId, @FormParam("ganadas") int ganadas, @FormParam("perdidas") int perdidas, @FormParam("empatadas") int empatadas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("INSERT INTO estadisticas (jugadorId, partidasG, partidasP, partidasE) VALUES ('"+jugadorId+"','"+ganadas+"','"+perdidas+"','"+empatadas+"')");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PUT
	@Path("alterStats")
	public void alterEstadisticas(@FormParam("jugadorId") int jugadorId, @FormParam("ganadas") int ganadas, @FormParam("perdidas") int perdidas, @FormParam("empatadas") int empatadas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("UPDATE estadisticas SET partidasG = '"+ganadas+"',partidasP = '"+perdidas+"',partidasE = '"+empatadas+"' WHERE (jugadorId = '"+jugadorId+"') ");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Path("delStats")
	public void delEstadisticas(@FormParam("jugadorId") int jugadorId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gocards?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","toor");
			Statement sentencia = conexion.createStatement();
			sentencia.execute("DELETE FROM estadisticas WHERE (jugadorId = '"+jugadorId+"')");
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
