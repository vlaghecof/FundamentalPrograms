package model;

public class Monomial {

	private double coefficient;
	private double power;

	public Monomial(double coefficient, double power) {
		super();
		this.coefficient = coefficient;
		this.power = power;
	}


	
	
	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}
	
	
	
}
