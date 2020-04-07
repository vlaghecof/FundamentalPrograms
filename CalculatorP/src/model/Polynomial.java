package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;



public class Polynomial {

	
	
	private ArrayList<Monomial> polynom ;

	public Polynomial() {
		super();
		ArrayList<Monomial> polynom =new ArrayList<Monomial>();
		this.polynom = polynom;
	}
	
	public Polynomial(ArrayList<Monomial> polynom) {
		super();
		this.polynom = polynom;
	}

	public ArrayList<Monomial> getPolynom() {
		return polynom;
	}

	public void setPolynom(ArrayList<Monomial> polynomIn) {

	
	
		for (Monomial mon2 : polynomIn) {
			
			Monomial mos = new Monomial(mon2.getCoefficient(), mon2.getPower());
			polynom.add(mos);
		}
		 
		
	}
	
	public void addPolynom(Monomial mon) {
		polynom.add(mon);
	}

	




}
