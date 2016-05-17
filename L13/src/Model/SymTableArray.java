package Model;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

public interface SymTableArray<T,T0> extends Serializable {
	

	int Len();

	void Add(T id, T0 value);

	int Search(T id);

	String ToString();
	
	SymTableArray<T,T0> clone();
}
