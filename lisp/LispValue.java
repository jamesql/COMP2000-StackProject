package lisp;

public class LispValue {
	private double val;
	private boolean isParentheses;
	private Character parVal;
	
	public LispValue(Character p) {
		this.parVal = p;
		this.isParentheses = true;
	}
	
	public LispValue(double v) {
		this.val = v;
		this.isParentheses = false;
	}
	
	public boolean isParantheses() {
		return this.isParentheses;
	}
	
	public Character getParVal() {
		return this.parVal;
	}
	
	public double getVal() {
		return this.val;
	}
	
	public boolean isOpenPar() {
		return this.parVal.equals('(');
	}
	
	public boolean isClosedPar() {
		return this.parVal.equals(')');
	}
	
	public static double combineWithSign(Character op, double val1, double val2) {
		switch(op) {
		case '+':
			return val1 + val2;
		case '-':
			return val1 - val2;
		case '*':
			return val1 * val2;
		case '/':
			return val1 / val2;
			default:
				return 0.;
		}
	}
	
	public static Double getStartingValue(Character op) {
		switch (op) {
		case '+':
			return 0.;
		case '-':
			return 0.;
		default:
			return 1.;
		}
	}
	
	public String toString() {
		String str = (this.isParentheses ? Character.toString(parVal) : Double.toString(val));
		return String.format("%s", str);
	}
	
}
