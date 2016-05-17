package Model;

import java.io.Serializable;

public class LockEnter extends Statement implements Serializable {
	public Expresion e;
	public LockEnter(){}
	public LockEnter(Expresion s){e=s;}
	public String ToString(){
		String res="";
		res+="LockEnter("+e.ToString()+")";
		return res;
	}

}
