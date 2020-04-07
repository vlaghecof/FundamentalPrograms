import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import model.ComparatorCustom;
import model.Monomial;
import model.Polynomial;


public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pane = new JPanel(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	private JButton btnAdd = new JButton("+");
	private JButton btnSub = new JButton("-");
	private JButton btnMult = new JButton("*");
	private JButton btnDiv = new JButton("/");
	private JButton btnInte = new JButton("Integr");
	private JButton btnDeriv = new JButton("Deriv");

	private JTextField number1 = new JTextField(20);
	private JTextField number2 = new JTextField(20);
	private JTextField output = new JTextField(20);

	private JLabel labN1 = new JLabel("First polynom");
	private JLabel labN2 = new JLabel("Second polynom");
	private JLabel result = new JLabel("Result");

	Controller controller = new Controller(this);

	public View(String name) {
		super(name);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		pane.add(btnAdd, c);
		btnAdd.addActionListener(controller);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(btnSub, c);
		btnSub.addActionListener(controller);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(btnMult, c);
		btnMult.addActionListener(controller);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(btnDiv, c);
		btnDiv.addActionListener(controller);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(btnInte, c);
		btnInte.addActionListener(controller);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(btnDeriv, c);
		btnDeriv.addActionListener(controller);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 4;

		pane.add(number1, c);

		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 4;
		pane.add(number2, c);

		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 4;
		pane.add(output, c);

		c.gridx = 0;
		c.gridy = 0;
		pane.add(labN1, c);

		c.gridx = 0;
		c.gridy = 1;
		pane.add(labN2, c);

		c.gridx = 0;
		c.gridy = 2;
		pane.add(result, c);

		this.add(pane);

	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnSub() {
		return btnSub;
	}

	public JButton getBtnMult() {
		return btnMult;
	}

	public JButton getBtnDiv() {
		return btnDiv;
	}

	public JTextField getNumber1() {
		return number1;
	}

	public JTextField getNumber2() {
		return number2;
	}

	public JTextField getOutput() {
		return output;
	}

	public JLabel getLabN1() {
		return labN1;
	}

	public JLabel getLabN2() {
		return labN2;
	}

	public JLabel getResult() {
		return result;
	}

	public JButton getBtnInte() {
		return btnInte;
	}

	public JButton getBtnDeriv() {
		return btnDeriv;
	}

	public static void main(String args[]) {

		
		  JFrame frame = new View("Calculator");
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.pack();
		  frame.setVisible(true);
		 

	}
}
