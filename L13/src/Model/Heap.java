package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Heap<N> implements HeapArray<N>,Serializable {

	private ArrayList<N> _heap = new ArrayList<N> ();

	@Override
	public void add(N number) {
		_heap.add(number);
		
	}

	@Override
	public N get(int index) {
		return _heap.get(index);
	}

	@Override
	public boolean isEmpty() {
		if (_heap.size()==0) return true;
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return _heap.size();
	}

	@Override
	public String ToString() {
		String rez = "";
        for (N h:_heap)
            rez += "( " + String.valueOf(_heap.indexOf(h)) + "->" + String.valueOf(h) + " )";
        return "Heap = [ " + rez + " ]\n";

	}

	@Override
	public int indexOf(N number) {
		return _heap.indexOf(number);
	}
}
