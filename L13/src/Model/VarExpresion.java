package Model;

import java.io.Serializable;

public class VarExpresion extends Expresion implements Serializable {
	public String var;
	public VarExpresion(String var){
		this.var=var;
	}
	public int Evaluation(SymTableArray<String, Integer> t,HeapArray<Integer> heap){	
		//System.out.println(t.Search(var));
		return t.Search(var);
	}
	public String ToString(){
		return var;
	}
}
