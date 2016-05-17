package Model;
import java.io.*;
import java.util.Observable;
import java.util.Observer;
public interface OutArray<T> extends Serializable{
	

	void AddToOutTable(T o);

	String ToString();

	int GetSize();

}
