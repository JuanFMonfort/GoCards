package modelos;

public class Partida {
	
	private int id;
	private int jugadorId;
	private int resultado;
	
	public Partida(int id, int jugadorId, int resultado) {
		super();
		this.id = id;
		this.jugadorId = jugadorId;
		this.resultado = resultado;
	}
	
	public Partida( int jugadorId, int resultado) {
		super();
		this.jugadorId = jugadorId;
		this.resultado = resultado;
	}

	public Partida() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJugadorId() {
		return jugadorId;
	}

	public void setJugadorId(int jugadorId) {
		this.jugadorId = jugadorId;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
	public String resultadoHelper(int cod) {
		String win = "Ganada";
		String lose = "Perdida";
		String tie = "Empate";
		
		switch (cod) {
		case 1:
			return win;
		case 2:
			return lose;
		case 3:
			return tie;
		default:
			return "Codigo de resultado incorrecto";
		}
		
	}

	@Override
	public String toString() {
		return "Partida \n id=" + id + "\n jugador=" + jugadorId + "\n resultado=" + resultadoHelper(resultado);
	}
	
	
	
}
