package Controller;
import java.util.Scanner;

import Repository.Repository;
import Model.*;
public class Controller  {
	public Repository r;
	private String output,exe,sym,heap,lockt;
	OutArray<String> outList;
	SymTableArray<String,Integer> symTable;
	ExeStackArray<Statement> exeStack;
	HeapArray<Integer> heapList;
	LockTableArray locktable;
	private int prgid;
	public Controller()
	{
		r=new Repository(CreateState());
		this.outList=r.getPrfromPos(0).getOut();
		this.symTable=r.getPrfromPos(0).getSymTable();
		this.exeStack=r.getPrfromPos(0).getExeStack();
		this.heapList=r.getPrfromPos(0).getHeap();
		this.locktable=r.getPrfromPos(0).getLock();
		output="";
		exe="";
		sym="";
		heap="";
		lockt="";
	}
	private PrgState CreateState(){
		Out<String> output=new Out<String>();
		SymTable<String,Integer> symTable=new SymTable<String,Integer>();
		ExeStack<Statement> exeStack=new ExeStack<Statement>();
		Heap<Integer> heap=new Heap<Integer>();
		LockTable lock=new LockTable();
		PrgState prog=new PrgState(output,exeStack,symTable,heap,lock);
		return prog;
	}
	
	/*public ExeStackArray<Statement> getStack(int ){
		return r.getState().getExeStack();
	}
	public OutArray<String> getOut(int ){
		return outList;
	}
	public SymTableArray<String,Integer> getDictionary(int ){
		return symTable;
	}
	
	public HeapArray<Integer> getHeap(int ){
		return heapList;
	}*/
	public void AddProgram(Statement s){
		r.AddStatementToStack(0,s);
		r.getState(0).s=s;
	}
	
	public String FinalResult(){
		return outList.ToString();
	}
	
	public void ActualState(){
		output=outList.ToString()+"\n";
		exe=r.stackToString();
		sym=r.dictToString();
		heap=heapList.ToString()+"\n";
		lockt=locktable.ToString()+"\n";

	}
	public String printOutput(){
		return output;
	}
	public String printLock(){
		return lockt;
	}
	public String printStack(){
		return exe;
	}
	public String printSym(){
		return sym;
	}
	public String printHeap(){
		return heap;
	}
	public void step(){
		exeStack.reverseStack();
		Statement s=exeStack.Pop();
		//System.out.println(s.getClass().getName());
		switch (s.getClass().getName()) 
		{
		  case "Model.AssignStatement":
			  weHaveAssign(s);
			break;
		  case "Model.CompoundStatement":
			  weHaveCompound(s);
				break;
		  case "Model.IfStatement":
			  	weHaveIf(s);
				break;
		  case "Model.PrintStatement":
			  	weHavePrint(s);
				break;
		  case "Model.ForkStatement":
			  	weHaveFork(s);
				break;
		  case "Model.LockEnter":
			  	weHaveLockEnter(s);
				break;
		  case "Model.LockExit":
			  weHaveLockExit( s);
				break;
		  default:
			  System.out.println("Undefined instance");
		}
		exeStack.reverseStack();
		/*
		if (s instanceof AssignStatement){
			AssignStatement ass=(AssignStatement)s;	
			symTable.Add(ass.id, ass.e.Evaluation(symTable,heapList));
		}
		if (s instanceof CompoundStatement){
			CompoundStatement cos=(CompoundStatement)s;
			exeStack.Push(cos.begin);
			exeStack.Push(cos.end);
			
		}
		if (s instanceof IfStatement){
			IfStatement ifs=(IfStatement)s;
			if (ifs.exp.Evaluation(symTable,heapList)!=0){
				exeStack.Push(ifs.th);	
			}
			else{
				exeStack.Push(ifs.els);
			}
		}
		if (s instanceof PrintStatement){
			PrintStatement prs=(PrintStatement)s;
			outList.AddToOutTable(String.valueOf(prs.expr.Evaluation(symTable,heapList)));
		}
		if (s instanceof ForkStatement){
			ForkStatement prs=(ForkStatement)s;
			r.thread(prs.getStatement());

		}
		if (s instanceof LockEnter){
			LockEnter lk=(LockEnter)s;	
			Expresion ex=lk.e;
			if(locktable.Search(ex.Evaluation(symTable,heapList))==-1)
				output+="Error: Lock table index not found \n";
			else if(locktable.Search(ex.Evaluation(symTable,heapList))==0){
				locktable.update(ex.Evaluation(symTable,heapList), prgid);
			}
			else{exeStack.Push(lk);}
				
		}
		if (s instanceof LockExit){
			LockExit lk=(LockExit)s;	
			Expresion ex=lk.e;
			if(locktable.Search(ex.Evaluation(symTable,heapList))==-1){
				
			}
			else
				if(locktable.Search(ex.Evaluation(symTable,heapList))==prgid){
					locktable.update(ex.Evaluation(symTable,heapList), 0);
				
				}
				else{}
		}*/
	}
	private void weHaveAssign(Statement s)
	{
		AssignStatement ass=(AssignStatement)s;	
		symTable.Add(ass.id, ass.e.Evaluation(symTable,heapList));
	}
	private void weHaveCompound(Statement s)
	{
		CompoundStatement cos=(CompoundStatement)s;
		exeStack.Push(cos.begin);
		exeStack.Push(cos.end);
	}
	private void weHaveIf(Statement s)
	{
		 IfStatement ifs=(IfStatement)s;
			if (ifs.exp.Evaluation(symTable,heapList)!=0){
				exeStack.Push(ifs.th);	
			}
			else{
				exeStack.Push(ifs.els);
			}
	}
	private void weHavePrint(Statement s)
	{
		PrintStatement prs=(PrintStatement)s;
		outList.AddToOutTable(String.valueOf(prs.expr.Evaluation(symTable,heapList)));
	}
	private void weHaveFork(Statement s)
	{
		ForkStatement prs1=(ForkStatement)s;
		r.thread(prs1.getStatement());
	}
	private void weHaveLockEnter(Statement s)
	{
		 LockEnter lk=(LockEnter)s;	
			Expresion ex=lk.e;
			if(locktable.Search(ex.Evaluation(symTable,heapList))==-1)
				output+="Error: Lock table index not found \n";
			else if(locktable.Search(ex.Evaluation(symTable,heapList))==0){
				locktable.update(ex.Evaluation(symTable,heapList), prgid);
			}
			else{exeStack.Push(lk);}
	}
	private void weHaveLockExit(Statement s)
	{
		LockExit lk1=(LockExit)s;	
		Expresion ex1=lk1.e;
		if(locktable.Search(ex1.Evaluation(symTable,heapList))==-1){
			
		}
		else
			if(locktable.Search(ex1.Evaluation(symTable,heapList))==prgid){
				locktable.update(ex1.Evaluation(symTable,heapList), 0);
			
			}
			else{}
	}
	public void Run(int i){
		int size=r.length();
		if(i==1){ActualState();stepi();}
		else if(i==0)
			while(r.crnt()!=null)
				stepi();
	}
	
	public void stepi()
	{
		if(r.crnt()!=null)
		{
			setCurrent(r.crnt());
			step();
			ActualState();
			r.next();
		}
			
	}
	
	public void setCurrent(PrgState ps)
	{
		prgid=ps.getIdentifier();
		exeStack=ps.getExeStack();
		symTable=ps.getSymTable();
	}
	
	public void deserialise(){
		r.deserialise();
	}
	
	public void serialise()
	{
			r.serialise();

	}
	public void WriteToFile(){

			r.ToFile();

	}
	
	
}
