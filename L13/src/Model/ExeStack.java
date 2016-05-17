package Model;
import Model.*;
import java.io.*;
import java.util.*;
import java.util.Observable;
import java.util.Observer;
public class ExeStack<T> implements ExeStackArray<T>,Serializable {
	private ArrayList<T> stmt = new ArrayList<T> ();
	private boolean is_reversed = false;
	private int top=0;
	private Observer ob;
	
	
	public void Push(T item){
		stmt.add (item);
		top++;
	}
	public T Pop(){
		
		T aux;
		top--;
		if (!is_reversed)
			aux=stmt.remove(stmt.size()-1);
		else
			aux=stmt.remove(0);
		return aux;
	}
	
	public void reverseStack()
	{
		is_reversed = !is_reversed;
	}
	public boolean isEmpty(){
		if (stmt.size()==0)return true;
		return false;
	}
	public T SeeTop(){
		return stmt.get(--top);
	}
	public int length(){
		return stmt.size();
	}
	public String ToString(){
		int i;
		Statement s;
		String r="";
		for (i=0;i<top;i++){
			if(stmt.get(i) instanceof Statement)
				{s=(Statement) stmt.get(i);
				r+=s.ToString()+" | ";}
		}
		r+="\n";
		return r;
	}
}