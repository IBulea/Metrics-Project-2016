package Model;

public interface HeapArray<N> {

    N get(int index);
    boolean isEmpty();
    int size();
    String ToString();
    int indexOf(N number);
	void add(N number);

}
