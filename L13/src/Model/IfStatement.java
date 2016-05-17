package Model;
import java.io.Serializable;

import Model.Expresion;
import Model.ArithmethicExpresion;
import Model.ConstantExpresion;
import Model.VarExpresion;
public class IfStatement extends Statement implements Serializable{
	public Expresion exp;
	public Statement th;
	public Statement els;
	public IfStatement(){}
	public IfStatement(Expresion e,Statement t,Statement el){
		exp=e;
		th=t;
		els=el;
	}
	public String ToString(){
		String result="";
    	result+="If "+exp.ToString()+ " Then "+th.ToString()+" Else "+els.ToString();
    	return result;
	}

}
