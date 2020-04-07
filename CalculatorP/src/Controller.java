import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;


public class Controller implements ActionListener {

	private View view;

	
	private Utility ut = new Utility();

	private Operations op = new Operations();

	public Controller(View v) {
		this.view = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == view.getBtnAdd()) {
			String pol1 = view.getNumber1().getText();
			String pol2 = view.getNumber2().getText();
			

			if (!Pattern.matches("[0-9x^/./+/-]*", pol1) || !Pattern.matches("[0-9x^/./+/-]*", pol2)) {
				view.getOutput().setText("Wrong input values ");
				return;
			}
			if (pol1.compareTo("") == 0 || pol2.compareTo("") == 0) {
				view.getOutput().setText("Wrong input values");
				return;
			}

			view.getOutput().setText(ut.polToString(op.addition(pol1, pol2)));
		}

		if (source == view.getBtnSub()) {
			String pol1 = view.getNumber1().getText();
			String pol2 = view.getNumber2().getText();

			if (!Pattern.matches("[0-9x^/./+/-]*", pol1) || !Pattern.matches("[0-9x^/./+/-]*", pol2)) {
				view.getOutput().setText("Wrong input values ");
				return;
			}

			if (pol1.compareTo("") == 0 || pol2.compareTo("") == 0) {
				view.getOutput().setText("Wrong input values");
				return;
			}
			view.getOutput().setText(ut.polToString(op.substraction(pol1, pol2)));
		}

		if (source == view.getBtnDeriv()) {
			String pol1 = view.getNumber1().getText();

			if (!Pattern.matches("[0-9x^/./+/-]*", pol1)) {
				view.getOutput().setText("Wrong input values");
				return;
			}

			if (pol1.compareTo("") == 0) {
				view.getOutput().setText("Wrong input values");
				return;
			}
			view.getOutput().setText(ut.polToString(op.derivation(pol1)));
		}
		if (source == view.getBtnInte()) {
			String pol1 = view.getNumber1().getText();

			if (!Pattern.matches("[0-9x^/./+/-]*", pol1)) {
				view.getOutput().setText("Wrong input values");
				return;
			}

			if (pol1.compareTo("") == 0) {
				view.getOutput().setText("Wrong input values");
				return;
			}
			view.getOutput().setText(ut.polToString(op.integration(pol1)));
		}

		if (source == view.getBtnMult()) {
			String pol1 = view.getNumber1().getText();
			String pol2 = view.getNumber2().getText();

			if (!Pattern.matches("[0-9x^/./+/-]*", pol1) || !Pattern.matches("[0-9x^/./+/-]*", pol2)) {
				view.getOutput().setText("Wrong input values");
				return;
			}
			if (pol1.compareTo("") == 0 || pol2.compareTo("") == 0) {
				view.getOutput().setText("Wrong input values");
				return;
			}
			view.getOutput().setText(ut.polToString(op.multiplication(pol1, pol2)));
		}
		if (source == view.getBtnDiv()) {
			String pol1 = view.getNumber1().getText();
			String pol2 = view.getNumber2().getText();

			if (!Pattern.matches("[0-9x^/./+/-]*", pol1) || !Pattern.matches("[0-9x^/./+/-]*", pol2)) {
				view.getOutput().setText("Wrong input values");
				return;
			}
			if (pol1.compareTo("") == 0 || pol2.compareTo("") == 0) {
				view.getOutput().setText("Wrong input values");
				return;
			}
			view.getOutput().setText(op.division(pol1, pol2));

		}

	}

}
