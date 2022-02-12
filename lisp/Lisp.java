package lisp;

import java.util.Scanner;

import stack.Stack;

public class Lisp {

	static Stack<LispValue> valueStack = new Stack<>();
	static Stack<Character> opStack = new Stack<>();

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.printf("Enter a valid Lisp Expression: ");
		String exp = s.nextLine();

		createStacks(exp);

		valueStack = reverse(valueStack);
		opStack = reverse(opStack);

		valueStack.pop();
		double result = evaluate();

		System.out.printf("The result of this expression is: %f", result);
	}

	public static void createStacks(String lispExp) {
		for (int i = 0; i < lispExp.length(); i++) {
			Character c = lispExp.charAt(i);
			int code = (int) c;

			if (Character.isDigit(c))
				valueStack.push(new LispValue((double) (c - '0')));
			else if (c.equals('(') || c.equals(')'))
				valueStack.push(new LispValue(c));

			boolean isOperator = (code == 42 || code == 43 || code == 45 || code == 47);
			if (isOperator)
				opStack.push(c);
		}
	}

	public static double evaluate() {
		boolean resolved = false;
		Character op = opStack.pop();
		double val = LispValue.getStartingValue(op);
		Stack<LispValue> v = new Stack<>();

		while (!resolved) {
			LispValue nextVal = valueStack.pop();

			if (!nextVal.isParantheses()) {
				v.push(nextVal);
				continue;
			}

			if (nextVal.isClosedPar())
				resolved = true;
			if (nextVal.isOpenPar())
				v.push(new LispValue(evaluate()));
		}

		if (v.isEmpty())
			return val;

		v = reverse(v);

		double res = v.pop().getVal();
		if (v.isEmpty())
			return LispValue.combineWithSign(op, val, res);

		while (!v.isEmpty())
			res = LispValue.combineWithSign(op, res, v.pop().getVal());

		return res;
	}

	public static <T> Stack<T> reverse(Stack<T> s) {
		Stack<T> temp = new Stack<>();

		while (!s.isEmpty())
			temp.push(s.pop());

		return temp;
	}
}
