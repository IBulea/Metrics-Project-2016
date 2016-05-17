package Model;
import java.io.Serializable;

import Model.Expresion;
import Model.ArithmethicExpresion;
import Model.ConstantExpresion;
import Model.VarExpresion;
public class PrintStatement extends Statement implements Serializable {
	public Expresion expr;
	public PrintStatement(){}
	public PrintStatement(Expresion s){
		expr=s;
	}
	public String ToString(){
		String r="Print ("+expr.ToString()+")";
		return r;
	}
}
