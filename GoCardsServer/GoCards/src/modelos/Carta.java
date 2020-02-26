package modelos;

public class Carta {

	private String id;
	private String nombre;
	private String pais;
	private int motor;
	private int cilindros;
	private int potenciaKw;
	private int revolucionesMin;
	private int velocidad;
	private double consumo;
	
	public Carta(String id, String nombre, String pais, int motor, int cilindros, int potenciaKw, int revolucionesMin,
			int velocidad, double consumo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.motor = motor;
		this.cilindros = cilindros;
		this.potenciaKw = potenciaKw;
		this.revolucionesMin = revolucionesMin;
		this.velocidad = velocidad;
		this.consumo = consumo;
	}

	public Carta() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getMotor() {
		return motor;
	}

	public void setMotor(int motor) {
		this.motor = motor;
	}

	public int getCilindros() {
		return cilindros;
	}

	public void setCilindros(int cilindros) {
		this.cilindros = cilindros;
	}

	public int getPotenciaKw() {
		return potenciaKw;
	}

	public void setPotenciaKw(int potenciaKw) {
		this.potenciaKw = potenciaKw;
	}

	public int getRevolucionesMin() {
		return revolucionesMin;
	}

	public void setRevolucionesMin(int revolucionesMin) {
		this.revolucionesMin = revolucionesMin;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	@Override
	public String toString() {
		return "Carta\n id=" + id + "\n nombre=" + nombre + "\n pais=" + pais + "\n motor=" + motor + "\n cilindros="
				+ cilindros + "\n potenciaKw=" + potenciaKw + "\n revolucionesMin=" + revolucionesMin + "\n velocidad="
				+ velocidad + "\n consumo=" + consumo;
	}
	
	
	
}
