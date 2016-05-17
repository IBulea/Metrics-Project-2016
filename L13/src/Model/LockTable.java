package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LockTable implements LockTableArray,Serializable {
	
Map<Integer,Integer> table = new HashMap<Integer,Integer>();

    public LockTable(){	
    	table.put(0,0);
    	table.put(1, 0);
    	table.put(2,0);
    }
    /*
	
	
	public int getValue(int t)
	{
		return table.get(t);
	}*/

	@Override
	public int Len() {
		return table.size();
	}

	@Override
	public void update(int id, int value) {
		table.put(id, value);
		
	}

	@Override
	public int Search(int id) {
		int rt;
		if(table.containsKey(id))
			return table.get(id);
		rt=-1;
		return rt;
	}

	@Override
	public String ToString() {
		String rez="";
		rez+=table.toString();
		rez+="\n";
		return rez;
	}

}
