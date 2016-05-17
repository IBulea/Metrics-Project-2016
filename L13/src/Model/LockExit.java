package Model;

import java.io.Serializable;

public class LockExit extends Statement implements Serializable {
	public Expresion e;
	public LockExit(){}
	public LockExit(Expresion s){e=s;}
	public String ToString(){
		String res="";
		res+="LockExit("+e.ToString()+")";
		return res;
	}

}
