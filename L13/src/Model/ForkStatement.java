package Model;

import java.io.Serializable;

public class ForkStatement extends Statement implements Serializable {

	public Statement f;
	public ForkStatement(Statement s){
		this.f=s;
	}
	public Statement getStatement()
	{
		return f;
	}
	public String ToString() {
		String result = "Fork( ";
		result += f.ToString()+")";
		return result;
	}

}
