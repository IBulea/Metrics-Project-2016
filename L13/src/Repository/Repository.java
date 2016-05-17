package Repository;

import java.io.*;
import java.util.ArrayList;

import Model.ExeStack;
import Model.Out;
import Model.PrgState;
import Model.Statement;
import Model.SymTable;

public class Repository  {
	public ArrayList<PrgState> states;
	private int current,iden=1; 
	private PrgState pro;
	public Repository() {
	}
	public void add(PrgState p)
	{
		states.add(p);
		p.setIdentifier(iden);
		iden++;
	}
	public Repository(PrgState s) {
		states=new ArrayList<PrgState>();
		states.add(s);
		s.setIdentifier(iden);
		iden++;
		pro=s;
		current=0;

	}

	public void AddStatementToStack(int index,Statement s) {
		states.get(index).getExeStack().Push(s);
	}
	
	public void thread(Statement s)
	{
		PrgState pr=new PrgState();
		pr.thread(getPrfromPos(current).getSymTable(), getPrfromPos(current).getHeap(), getPrfromPos(current).getOut(),getPrfromPos(current).getLock(),s);
		add(pr);
	}
	
	public int getNextValid()
	{
		if(current>=length()-1)
			for(int j=0;j<length();j++)
				if(valid(j))
				{	current=j;
					return j;
				}
				else
				{states.remove(j);}
		else
			for(int ji=current+1;ji<length();ji++)
					if(valid(ji))
					{current=ji;
						return ji;
					}
					else
					{
						states.remove(ji);
					}
		return (-1);
	}
	
	public void next()
	{
		int a=getNextValid();
		//current=a;
	}
	public PrgState crnt()
	{
		if(getPrfromPos(current)!=null && valid(current))
			return getPrfromPos(current);
		else
			if(getNextValid()==(-1))
				return null;
		return getPrfromPos(getNextValid());
					
	}
	
	public PrgState getPrfromPos(int i)
	{
		try{
		return states.get(i);
		}
		catch(IndexOutOfBoundsException e){
			return null;
		}
	}
	
	public boolean valid(int i)
	{
		return !(states.get(i).getExeStack().isEmpty());
	}
	
	public void removePr(PrgState p)
	{
		states.remove(p);
	}
	
	public String dictToString()
	{
		String r="";
		for (int i=0;i<length();i++)
		{
			r+="Program State "+String.valueOf(states.get(i).getIdentifier())+": "+states.get(i).getSymTable().ToString()+" \n ";
		}
		return r;
	}
	
	public String stackToString()
	{
		String r="";
		for (int i=0;i<length();i++)
		{
			r=r+"Program State "+String.valueOf(states.get(i).getIdentifier())+": "+states.get(i).getExeStack().ToString()+" \n ";
		}
		return r;
	}
	
	public void serialise(){
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("./src/data/PrgState.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(states);
	         out.close();
	         fileOut.close();
	         //System.out.printf("Serialized data is saved in PrgState.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
	}
	public PrgState getState(int index){
		return states.get(index);
	}
	@SuppressWarnings("unchecked")
	public void deserialise(){
		ArrayList<PrgState> s;
		 try
	      {
	         FileInputStream fileIn = new FileInputStream("./src/data/PrgState.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         s=(ArrayList<PrgState>) in.readObject();
	         states=s;
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("PrgState class not found");
	         c.printStackTrace();
	         return;
	      }
		
	}
	public void ToFile(){
		int size=states.size();
		int i;
		String content="";
		for(i=0;i<size;i++){
			content+=states.get(i).getExeStack().ToString();
			content+="\n"+states.get(i).getSymTable().ToString();
			content+="\n"+states.get(i).getOut().ToString();
		}
			try {
				 
				File file = new File("./src/data/file.out");
	 
				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);//,true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
	 
				//System.out.println("Done");
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public int length(){
		return states.size();
	}
	public boolean isEmpty()
	{
		return length()==0;
	}
}
