package Model;

import java.io.Serializable;

import Model.SymTableArray;

public abstract class Expresion implements Serializable {
	public abstract int Evaluation(SymTableArray<String, Integer> symTableArray,HeapArray<Integer> heap);

	public abstract String ToString();

}
