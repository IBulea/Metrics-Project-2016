package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class SymTable<T,N> implements SymTableArray<T,N>,Serializable {
	
	Map<T,N> table = new HashMap<T,N>();
	
	public int Len(){
		return table.size();
	}

	public void Add(T id, N n){
		table.put(id, n);

			
	}
	public int Search(T id){
				int rt=(Integer) table.get(id);
				return rt;
			
	}
	public String ToString(){
		String rez="";
		rez+=table.toString();
		rez+="\n";
		return rez;
	}
	
	public SymTable<T,N> clone(){
		SymTable<T,N>  m=new SymTable<T,N> ();
		for (T t : table.keySet())
		{
			m.Add(t,getValue(t));
		}
		return m;
		
	}
	
	public N getValue(T t)
	{
		return table.get(t);
	}
}
