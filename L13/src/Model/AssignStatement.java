package Model;
import java.io.Serializable;

import Model.Expresion;
import Model.ArithmethicExpresion;
import Model.ConstantExpresion;
import Model.VarExpresion;
public class AssignStatement extends Statement implements Serializable {
	public String id;
	public Expresion e;
	public AssignStatement(){
		
	}
	public AssignStatement(String id,Expresion e){
		this.id=id;
		this.e=e;
	}
	public String ToString()
	{
		String result="";
		result+=id+"="+e.ToString();
		return result;
	}
	
	
}
