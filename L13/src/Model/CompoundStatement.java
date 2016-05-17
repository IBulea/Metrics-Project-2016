package Model;
import java.io.*;

import Model.Expresion;
import Model.ArithmethicExpresion;
import Model.ConstantExpresion;
import Model.VarExpresion;
public class CompoundStatement extends Statement implements Serializable {
	public Statement begin;
	public Statement end;

	public CompoundStatement() {

	}

	public CompoundStatement(Statement begin, Statement end) {
		this.begin = begin;
		this.end = end;
	}

	public String ToString() {
		String result = "";
		result += begin.ToString() + ";" + end.ToString();
		return result;
	}
}
