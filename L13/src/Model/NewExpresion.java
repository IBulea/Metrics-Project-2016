package Model;

import java.io.Serializable;

public class NewExpresion extends Expresion implements Serializable {
	private int _value;

    public NewExpresion(int value)
    {
        _value = value;
    }

    public int LookUp(HeapArray<Integer> heap, int value)
    {
     
        return heap.indexOf(value);
    }

    public int Evaluation(SymTableArray<String,Integer> table,HeapArray<Integer> heap)
    {
        heap.add(_value);
        return LookUp(heap, _value);
    }

    public String ToString()
    {
        return "New(" + _value + ")";
    }

}

