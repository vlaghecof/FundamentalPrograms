

import model.ComparatorCustom;
import model.ComparatorDesc;
import model.Monomial;
import model.Polynomial;

public class Operations {

	Utility ut = new Utility();

	public Polynomial formatareResult(Polynomial poly1) {
		ComparatorCustom comp = new ComparatorCustom();
		poly1.getPolynom().sort(comp);
		for (int i = 0; i < poly1.getPolynom().size() - 1; i++) {
			for (int j = i + 1; j < poly1.getPolynom().size(); j++) {
				if (poly1.getPolynom().get(i).getPower() == poly1.getPolynom().get(j).getPower()) {
					poly1.getPolynom().get(i).setCoefficient(
							poly1.getPolynom().get(i).getCoefficient() + poly1.getPolynom().get(j).getCoefficient());
					poly1.getPolynom().remove(j);
					j--;
				}
			}
		}
		return poly1;
	}

	
	
	public Polynomial aidOperation(Polynomial poly1, Polynomial poly2, char sign) {
		Polynomial result = new Polynomial();
		Monomial mon;
		int firstIterate = 0, secondIterate = 0;
		if(sign=='-')
		{
			poly2.getPolynom().forEach((n)->{
				n.setCoefficient(-1*n.getCoefficient());
			});
		}
		

		while (firstIterate != poly1.getPolynom().size() && secondIterate != poly2.getPolynom().size()) {
			if (poly1.getPolynom().get(firstIterate).getPower() < poly2.getPolynom().get(secondIterate).getPower()) {
				mon = new Monomial(poly1.getPolynom().get(firstIterate).getCoefficient(),
						poly1.getPolynom().get(firstIterate).getPower());
				if (mon.getCoefficient() == 0) {
					firstIterate++;
					continue;
				}
				result.addPolynom(mon);
				firstIterate++;
			} else if (poly1.getPolynom().get(firstIterate).getPower() > poly2.getPolynom().get(secondIterate)
					.getPower()) {
				mon = new Monomial(poly2.getPolynom().get(secondIterate).getCoefficient(),
						poly2.getPolynom().get(secondIterate).getPower());
				if (mon.getCoefficient() == 0) {
					secondIterate++;
					continue;
				}
				result.addPolynom(mon);
				secondIterate++;
			} else if (poly1.getPolynom().get(firstIterate).getPower() == poly2.getPolynom().get(secondIterate)
					.getPower()) {

				
					mon = new Monomial(
							poly1.getPolynom().get(firstIterate).getCoefficient()
									+ poly2.getPolynom().get(secondIterate).getCoefficient(),
							poly1.getPolynom().get(firstIterate).getPower());
				

				if (mon.getCoefficient() == 0) {
					firstIterate++;
					secondIterate++;
					continue;
				}
				result.addPolynom(mon);
				firstIterate++;
				secondIterate++;
			}
		}
		while (firstIterate != poly1.getPolynom().size()) {
			mon = new Monomial(poly1.getPolynom().get(firstIterate).getCoefficient(),
					poly1.getPolynom().get(firstIterate).getPower());
			result.addPolynom(mon);
			firstIterate++;
		}
		while (secondIterate != poly2.getPolynom().size()) {
			mon = new Monomial(poly2.getPolynom().get(secondIterate).getCoefficient(),
					poly2.getPolynom().get(secondIterate).getPower());
			result.addPolynom(mon);
			secondIterate++;
		}
		return result;
	}
	
	
	public Polynomial addition(String first, String second) {
		Polynomial poly1, poly2;
		ComparatorCustom comp = new ComparatorCustom();

		poly1 = new Polynomial(ut.generateMonList(first));
		poly2 = new Polynomial(ut.generateMonList(second));
		poly1.getPolynom().sort(comp);
		poly2.getPolynom().sort(comp);
		return formatareResult(aidOperation(poly1, poly2, '+'));
	}

	public Polynomial substraction(String first, String second) {
		Polynomial poly1, poly2;
		
		ComparatorDesc comp = new ComparatorDesc();
		poly1 = new Polynomial(ut.generateMonList(first));
		poly2 = new Polynomial(ut.generateMonList(second));
		poly1.getPolynom().sort(comp);
		poly2.getPolynom().sort(comp);
		return formatareResult(aidOperation(poly1, poly2, '-'));
	}

	public Polynomial derivation(String first) {
		Polynomial poly1, result = new Polynomial();
		Monomial rezMon;
		ComparatorCustom comp = new ComparatorCustom();
		poly1 = new Polynomial(ut.generateMonList(first));
		poly1.getPolynom().sort(comp);

		for (Monomial mon : poly1.getPolynom()) {
			if (mon.getPower() == 0)
				continue;
			else {
				rezMon = new Monomial(mon.getCoefficient() * mon.getPower(), mon.getPower() - 1);
				result.addPolynom(rezMon);
			}

		}
		return formatareResult(result);
	}

	public Polynomial integration(String first) {
		Polynomial poly1, result = new Polynomial();
		if(first.contentEquals("0"))
			return new Polynomial();
		Monomial rezMon;
		double coeff, power;
		ComparatorCustom comp = new ComparatorCustom();
		poly1 = new Polynomial(ut.generateMonList(first));
		poly1.getPolynom().sort(comp);

		for (Monomial mon : poly1.getPolynom()) {
			coeff = mon.getCoefficient() / (mon.getPower() + 1);
			power = mon.getPower() + 1;
			rezMon = new Monomial(coeff, power);
			result.addPolynom(rezMon);
		}
			
		return formatareResult(result);
	}

	public Polynomial multiplication(String first, String second) {
		
		if(second.contentEquals("0"))
			return new Polynomial();
		
		Polynomial poly1, poly2, result = new Polynomial();
		Monomial rezMon;
		double coeff = 0, power = 0;
		
		poly1 = new Polynomial(ut.generateMonList(first));
		poly2 = new Polynomial(ut.generateMonList(second));

		
		for (Monomial mon1 : poly1.getPolynom()) {
			for (Monomial mon2 : poly2.getPolynom()) {
				coeff = (mon1.getCoefficient() * mon2.getCoefficient());
				power = (mon1.getPower() + mon2.getPower());
				rezMon = new Monomial(coeff, power);
				result.addPolynom(rezMon);
			}
		}
			
		return formatareResult(result);
	}

	
public String division(String first, String second)
{
	if(second.contentEquals("0"))
	return "Invalid , division by 0";
	ComparatorCustom comp = new ComparatorCustom();
	Polynomial quotient = new Polynomial();
	Monomial dividend = new Monomial(0, 0);
	String rest;
	Polynomial aux = new Polynomial();
	
	Polynomial	poly1 = new Polynomial(ut.generateMonList(first));
	Polynomial poly2 = new Polynomial(ut.generateMonList(second));

	if(poly1==null||poly2==null)
	{
		return "Wrong Imput";
	}

	poly1.getPolynom().sort(comp);
	poly2.getPolynom().sort(comp);
	poly1=formatareResult(poly1);
	poly2=formatareResult(poly2);
	
	Monomial divisor = poly2.getPolynom().get(0);
	
	if(divisor.getPower() == 0 )
	{
		poly1.getPolynom().forEach((n)->{
			Monomial mon  =new Monomial(n.getCoefficient()/divisor.getCoefficient(),n.getPower());
			quotient.addPolynom(mon);
		});
		return ut.polToString( quotient);
		
	}
	
	while(poly1.getPolynom().get(0).getPower()>= divisor.getPower())
	{
		dividend = poly1.getPolynom().get(0);
		Monomial m3 = new Monomial(dividend.getCoefficient()/divisor.getCoefficient(), dividend.getPower()-divisor.getPower());
		quotient.addPolynom(m3);
		
		
		poly2.getPolynom().forEach((n) -> {
			Monomial m  =new Monomial(n.getCoefficient()*m3.getCoefficient(),n.getPower()+m3.getPower());
			aux.addPolynom(m);
		});
		
		poly1 = substraction(ut.polToString(poly1), ut.polToString(aux));
		aux.getPolynom().clear();
		if(poly1.getPolynom().isEmpty())
			break;
	}
	
	if(poly1.getPolynom().isEmpty())
	{
		 rest = "0";
	}
	else
	 rest =ut.polToString(poly1);
	return ut.polToString(quotient)+" ; Rest: "+ut.polToString(poly1);
}



}
	
