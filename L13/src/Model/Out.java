package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Out<T> implements OutArray<T>,Serializable{
	private ArrayList<T> table = new ArrayList<T> ();
	public void AddToOutTable(T t) {
		
		table.add(t);
	}
	
	public int GetSize() {
		return table.size();
	}
	public String ToString() {
		String res = "";
		res+=table.toString();
		//System.out.println(res);
		res += "\n";
		return res;
	}

}
