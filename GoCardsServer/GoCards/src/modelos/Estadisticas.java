package modelos;

public class Estadisticas {
	
	private int jugadorId;
	private int partidasG;
	private int partidasP;
	private int partidasE;
	
	public Estadisticas(int jugadorId, int partidasG, int partidasP, int partidasE) {
		super();
		this.jugadorId = jugadorId;
		this.partidasG = partidasG;
		this.partidasP = partidasP;
		this.partidasE = partidasE;
	}

	public Estadisticas() {
		super();
	}

	public int getJugadorId() {
		return jugadorId;
	}

	public void setJugadorId(int jugadorId) {
		this.jugadorId = jugadorId;
	}

	public int getPartidasG() {
		return partidasG;
	}

	public void setPartidasG(int partidasG) {
		this.partidasG = partidasG;
	}

	public int getPartidasP() {
		return partidasP;
	}

	public void setPartidasP(int partidasP) {
		this.partidasP = partidasP;
	}

	public int getPartidasE() {
		return partidasE;
	}

	public void setPartidasE(int partidasE) {
		this.partidasE = partidasE;
	}

	@Override
	public String toString() {
		return "Estadisticas \n Jugador=" + jugadorId + "\n Ganadas=" + partidasG + "\n Perdidas=" + partidasP
				+ "\n Empatadas=" + partidasE;
	}

	

}
