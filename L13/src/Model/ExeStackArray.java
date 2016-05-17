package Model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public interface ExeStackArray<T> extends Serializable {
	void Push(T s);
	T Pop();

	T SeeTop();

	boolean isEmpty();
	void reverseStack();
	String ToString();
	int length();

}
