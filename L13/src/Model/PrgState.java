package Model;
import Model.Statement;

import java.io.*;
public class PrgState implements Serializable {
	private OutArray<String> out;
	private ExeStackArray<Statement> stack;
	private SymTableArray<String,Integer> table;
	private HeapArray<Integer> heap;
	private LockTableArray lock;
	public Statement s;
	private int identifier;
	public PrgState(){
		out=new Out<String>();
		stack= new ExeStack<Statement>();
		table=new SymTable<String,Integer>();
		heap=new Heap<Integer>();
		lock=new LockTable();
	}
	public PrgState(OutArray<String> out,ExeStackArray<Statement> stack,SymTableArray<String,Integer> table, HeapArray<Integer> heap,LockTableArray lock){
		makeOut(out);
		makeStack(stack);
		makeTable(table);
		makeHeap(heap);
		makeLock(lock);
	}
	public void makeOut(OutArray<String> a){
		out=a;
	}
	public void makeLock(LockTableArray l){
		lock=l;
	}
	public void makeHeap(HeapArray<Integer> he){
		heap=he;
	}
	public void makeStack(ExeStackArray<Statement> a){
		stack=a;
	}
	public void makeTable(SymTableArray<String,Integer> a){
		table=a;
	}
	public SymTableArray<String,Integer> getSymTable(){
		return table;
	}
	public OutArray<String> getOut(){
		return out;
	}
	public ExeStackArray<Statement> getExeStack(){
		return stack;
	}
	public HeapArray<Integer> getHeap(){
		return heap;
	}
	
	public LockTableArray getLock(){
		return lock;
	}
	
	public void thread(SymTableArray<String,Integer> dt,HeapArray<Integer> ht,OutArray<String> lt,LockTableArray lo,Statement st)
	{
		out=lt;
		heap=ht;
		lock=lo;
		table=dt.clone();
		stack.Push(st);
	}
	public void setIdentifier(int i){
		identifier=i;
	}
	public int getIdentifier(){
		return identifier;
	}
	
}
