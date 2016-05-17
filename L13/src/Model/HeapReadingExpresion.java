package Model;

import java.io.Serializable;

public class HeapReadingExpresion extends Expresion implements Serializable {

	private String _var;

    public HeapReadingExpresion(String var)
    {
        _var = var;
    }

    public int LookUp(HeapArray<Integer> heap, int value)
    {
        return heap.indexOf(value);
    }

    public int Evaluation(SymTableArray<String,Integer> symT, HeapArray<Integer> heap)
    {
        int address = symT.Search(_var);
        //try
        //{
        //    return LookUp(heap, address);
        //}
        //catch (ExpressionException e)
        //{
        //    Console.WriteLine(e.Message);
        //}
        //return -1;
        return heap.get(address);
    }

    public String ToString()
    {
        return "HeapReading(" + _var + ")";
    }

}
