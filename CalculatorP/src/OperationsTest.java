import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class OperationsTest {

	
	/*The test respect the following structure
	
	*The first one is a simple example
	*Then an operation example with 2 polynomials
	*An example with an ungrouped polynomial
	*Then a case where we use a constant for the operation 
	*Finally we use 0 for the operation
	*/
	
	@Test
	void test() {
		String polyn1 = "x^2+1.9x+20";
		String polyn2 = "x^8-12x+23";
		String constn = " 6";
		String ungroupedPoly= "-7x^2+x^2+6x-7+1+5-4-5x-x^5-x+x";// = -x^5-6x^2+x-5
		Operations op = new Operations();
		Utility ut = new Utility();
		
	//addition tests 
		assertTrue("2.0x^1.0+1.0".contentEquals(ut.polToString(op.addition("x", "x+1"))));
		assertTrue("1.0x^8.0+1.0x^2.0-10.1x^1.0+43.0".contentEquals(ut.polToString(op.addition(polyn1, polyn2))));//here we test that a+b
		assertTrue("1.0x^8.0+1.0x^2.0-10.1x^1.0+43.0".contentEquals(ut.polToString(op.addition(polyn2, polyn1))));//equals b+a
		assertTrue("-1.0x^5.0-6.0x^2.0+1.0x^1.0-4.0".contentEquals(ut.polToString(op.addition(ungroupedPoly, "1"))));
		assertTrue("1.0x^2.0+1.9x^1.0+20.0".contentEquals(ut.polToString(op.addition(polyn1, "0"))));
	
		//substraction test 
		assertTrue("-2.0".contentEquals(ut.polToString(op.substraction("x", "x+2"))));
		assertTrue("-1.0x^8.0+1.0x^2.0+13.9x^1.0-3.0".contentEquals(ut.polToString(op.substraction(polyn1, polyn2))));
		assertTrue( "0000".contentEquals( ut.polToString(op.substraction(polyn1, polyn1)))  ); //a -a = 0 
		assertTrue("-1.0x^5.0-6.0x^2.0+1.0x^1.0-11.0".contentEquals(ut.polToString(op.substraction(ungroupedPoly, "6"))));
		assertTrue("-1.0x^5.0-6.0x^2.0+1.0x^1.0-11.0".contentEquals(ut.polToString(op.substraction("-1.0x^5.0-6.0x^2.0+1.0x^1.0-5.0", "6"))));
		assertTrue("-1.0x^5.0-6.0x^2.0+1.0x^1.0-5.0".contentEquals(ut.polToString(op.substraction("-1.0x^5.0-6.0x^2.0+1.0x^1.0-5.0", "0"))));
	
		//Multiplication
		assertTrue("2.0x^2.0+26.0x^1.0+40.0".contentEquals(ut.polToString(op.multiplication("x^2+13x+20", "2"))));
		assertTrue("1.0x^4.0+4.0x^3.0+4.0x^2.0".contentEquals(ut.polToString(op.multiplication("x^2+2x", "x^2+2x"))));
		assertTrue("1.0x^5.0+1.0x^4.0+1.0x^3.0+6.0x^2.0".contentEquals(ut.polToString(op.multiplication("x^2+2x", "x^3-x^2+3x"))));
		assertTrue("-6.0x^5.0-36.0x^2.0+6.0x^1.0-30.0".contentEquals(ut.polToString(op.multiplication(ungroupedPoly, "6"))));
		assertTrue("-1.0x^5.0-6.0x^2.0+1.0x^1.0-5.0".contentEquals(ut.polToString(op.multiplication(ungroupedPoly, "1")))); //multiplication by 1
		assertTrue("-1.0x^1.0-2.0".contentEquals(ut.polToString(op.multiplication("x+2", "-1"))));
		assertTrue("0000".contentEquals(ut.polToString(op.multiplication("-1.0x^5.0-6.0x^2.0+1.0x^1.0-5.0", "0"))));
	
	
		//Division 
		
		assertTrue("1.0x^1.0+1.0".contentEquals(op.division("2x+2", "2")));
		assertTrue("1.0x^1.0+5.0 ; Rest: 0000".contentEquals(op.division("x^2+9x+20", "x+4")));
		assertTrue("1.0x^1.0+5.0 ; Rest: 2.0".contentEquals(op.division("x^2+9x+22", "x+4")));
		assertTrue("1.0x^2.0-2.0x^1.0+4.0 ; Rest: -7.0".contentEquals(op.division("3x^3-5x^2+10x-3", "3x+1")));
		assertTrue("Invalid , division by 0".contentEquals(op.division("3x^3-5x^2+10x-3", "0"))); // a/0=err
		assertTrue("3.0x^3.0-5.0x^2.0+10.0x^1.0-3.0".contentEquals(op.division("3x^3-5x^2+10x-3", "1")));// a/1=a
		assertTrue("1.0 ; Rest: 0000".contentEquals(op.division("3x^3-5x^2+10x-3", "3x^3-5x^2+10x-3")));// a/a=1
		
		//Integration 
		assertTrue("1.0x^2.0".contentEquals(ut.polToString(op.integration("2x"))));
		assertTrue("6.0x^1.0".contentEquals(ut.polToString(op.integration("6"))));
		assertTrue("1.0x^2.0+2.0x^1.0".contentEquals(ut.polToString(op.integration("2x+2"))));
		assertTrue("0000".contentEquals(ut.polToString(op.integration("0"))));
		assertTrue("0.75x^4.0-1.6666666666666667x^3.0+5.0x^2.0-3.0x^1.0".contentEquals(ut.polToString(op.integration("3x^3-5x^2+10x-3"))));
		assertTrue("2.0x^3.0+1.0x^2.0".contentEquals(ut.polToString(op.integration("6x^2+2x"))));
		
	//Derivation
		
		assertTrue("2.0".contentEquals(ut.polToString(op.derivation("2x"))));
		assertTrue("0000".contentEquals(ut.polToString(op.derivation("2"))));
		assertTrue("0000".contentEquals(ut.polToString(op.derivation("0"))));
		assertTrue("2.0".contentEquals(ut.polToString(op.derivation("2x+2+6+4+3"))));
		assertTrue("10.0".contentEquals(ut.polToString(op.derivation("2x+2x+6x+4+3"))));
		assertTrue("9.0x^2.0-10.0x^1.0+10.0".contentEquals(ut.polToString(op.derivation("3x^3-5x^2+10x-3"))));
	}

}
