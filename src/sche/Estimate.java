package sche;

public class Estimate {
	private int id;
	private int heur;
	private int actual;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHeur() {
		return heur;
	}
	public void setHeur(int heur) {
		this.heur = heur;
	}
	public int getActual() {
		return actual;
	}
	public void setActual(int actual) {
		this.actual = actual;
	}
	public Estimate(int id, int heur, int actual) {
		super();
		this.id = id;
		this.heur = heur;
		this.actual = actual;
	}
	
	
}
