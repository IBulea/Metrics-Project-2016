package Model;

import java.io.Serializable;

public class ConstantExpresion extends Expresion implements Serializable {
	public int nr;
	public ConstantExpresion(){
		
	}
	public ConstantExpresion(int n){
		nr=n;
		
	}
	public int Evaluation(SymTableArray<String, Integer> t,HeapArray<Integer> heap) {
		return nr;
	}
	
	public String ToString()
	{
		return String.valueOf(nr);
	}

}
