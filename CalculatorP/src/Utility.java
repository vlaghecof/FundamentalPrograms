
import java.util.ArrayList;


import model.ComparatorCustom;
import model.Monomial;
import model.Polynomial;

public class Utility {

	public ArrayList<Monomial> generateMonList(String expresion) {
		Monomial mon;
		ComparatorCustom comp = new ComparatorCustom();
		
		ArrayList<Monomial> monoms = new ArrayList<Monomial>();
		String format = expresion.replaceAll("-", "+-");
		String[] elements = format.split("\\+");

		for (String el : elements) {
			if (el.isEmpty())
				continue;

			mon = formatare(el);
			monoms.add(mon);
		}
monoms.sort(comp);
		return monoms;

	}

	public String detCoef(String coeff) {

		if (coeff.isEmpty()) {
			return "1";
		}

		if (coeff.contentEquals("-")) {
			return "-1";

		}

		return coeff;

	}

	public String detPower(String power) {
		if (power.isEmpty()) {
			return "1";
		}

		return power.substring(1);
	}

	public Monomial formatare(String expresion) {
		String coeff;
		String power;
		Monomial mon;

		
		
		if (!expresion.contains("x")) {
			coeff = expresion;
			power = "0";
		} else {
			if (expresion.contentEquals("x")) {
				coeff = "1";
				power = "1";
			} else {
				String[] number = expresion.split("x");
				if (expresion.contains("^"))
					power = detPower(number[1]);
				else {
					power = "1";
				}
				coeff = detCoef(number[0]);

			}
		}

		
		return mon = new Monomial(Double.valueOf(coeff), Double.valueOf(power));

	}

	public String polToString(Polynomial pol)
	{
		  
		String output = new String();
		for(Monomial mon :pol.getPolynom())
		{
		if(mon.getPower()==0)	
	  output+=String.valueOf(mon.getCoefficient())+"+";
		else
			{output+=String.valueOf(mon.getCoefficient())+"x^"+ String.valueOf(mon.getPower())+"+";
					}
		
		}
		if(output.isEmpty() )
			return "0000";
		return output.substring(0, output.length()-1 ).replace("+-", "-");
	}
}
