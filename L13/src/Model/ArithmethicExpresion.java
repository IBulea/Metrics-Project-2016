package Model;

import java.io.*;

import Model.SymTableArray;

public class ArithmethicExpresion extends Expresion implements Serializable {
	private Expresion exp1;
	private Expresion exp2;
	public String operator;

	public ArithmethicExpresion(Expresion e1, Expresion e2, String op) {
		exp1 = e1;
		exp2 = e2;
		operator = op;
	}

	public int Evaluation(SymTableArray<String, Integer> t,HeapArray<Integer> heap) {
		int left = exp1.Evaluation(t,heap);
		int right = exp2.Evaluation(t,heap);
		switch (operator) {
		case "+":
			return left + right;
		case "-":
			return left - right;
		case "*":
			return left * right;
		case "/":
			return left / right;
		default:
			return 0;
		}
	}

	public String ToString() {
		String result = "";
		result += exp1.ToString() + operator + exp2.ToString();
		return result;
	}
}
