package model;

import java.util.Comparator;

public class ComparatorCustom implements Comparator<Monomial> {

	public int compare(Monomial o1, Monomial o2) {

		if (o1.getPower() == o2.getPower())
			return 0;
		else if (o1.getPower() < o2.getPower())
			return 1;
		else
			return -1;
	}

}
