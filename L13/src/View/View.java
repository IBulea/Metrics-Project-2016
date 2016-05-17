package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import Controller.Controller;
import Model.*;

import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JScrollBar;

public class View extends JFrame {
	private Controller con;
	private JPanel mypage;
	View(Controller c){
		con=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 681);
		
		mypage = new JPanel();
		mypage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mypage);
		
		JButton AddInstruction = new JButton("Add Instructions");
		AddInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement st=ChooseStatement();
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Program introduced");
				con.AddProgram(st);
			}
		});
		mypage.setLayout(null);
		
		final JPanel app = new JPanel();
		app.setBounds(281, 61, 548, 570);
		mypage.add(app);
		app.setLayout(null);
		
		final JPanel allOut = new JPanel();
		allOut.setBounds(0, 0, 533, 533);
		app.add(allOut);
		allOut.setLayout(null);
		
		final JList exeList = new JList();
		exeList.setBounds(20, 63, 367, 181);
		allOut.add(exeList);
		
		final JList symList = new JList();
		symList.setBounds(20, 269, 289, 90);
		allOut.add(symList);
		
		final JList outList = new JList();
		outList.setBounds(419, 63, 87, 181);
		allOut.add(outList);
		
		final JList heapList = new JList();
		heapList.setBounds(319, 269, 250, 181);
		allOut.add(heapList);
		
		JLabel lblNewLabel = new JLabel("Exe Stack");
		lblNewLabel.setBounds(20, 37, 87, 15);
		allOut.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sym Table");
		lblNewLabel_1.setBounds(20, 255, 81, 15);
		allOut.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Output");
		lblNewLabel_2.setBounds(419, 38, 87, 14);
		allOut.add(lblNewLabel_2);
		
		final JList lockList = new JList();
		lockList.setBounds(20, 393, 289, 90);
		allOut.add(lockList);
		
		JLabel lblNewLabel_3 = new JLabel("Lock List");
		lblNewLabel_3.setBounds(20, 370, 46, 14);
		allOut.add(lblNewLabel_3);
		
		final JPanel output_1 = new JPanel();
		output_1.setBounds(0, 0, 533, 522);
		app.add(output_1);
		output_1.setLayout(null);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBounds(43, 29, 74, 24);
		output_1.add(lblOutput);
		
		final JList list = new JList();
		
				list.setBounds(43, 64, 185, 316);
				output_1.add(list);
		AddInstruction.setBounds(48, 103, 153, 23);
		mypage.add(AddInstruction);
		
		JButton run = new JButton("Run");
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.removeAll();
				app.repaint();
				app.revalidate();
				app.add(output_1);
				app.repaint();
				app.revalidate();
				con.Run(0);
				String output=con.printOutput();
				DefaultListModel dlm=new DefaultListModel();
				dlm.addElement(output);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setModel(dlm);
				
			}
		});
		run.setBounds(48, 137, 153, 23);
		mypage.add(run);
		
		JButton debug = new JButton("Debug");
		debug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.removeAll();
				app.repaint();
				app.revalidate();
				app.add(allOut);
				app.repaint();
				app.revalidate();
				DefaultListModel dout=new DefaultListModel();
				DefaultListModel de=new DefaultListModel();
				DefaultListModel ds=new DefaultListModel();
				DefaultListModel dh=new DefaultListModel();
				DefaultListModel dl=new DefaultListModel();
				con.Run(1);
				String output=con.printOutput();
				String exe=con.printStack();
				String sym=con.printSym();
				String heap=con.printHeap();
				String lock=con.printLock();
				String[] ll=lock.split("\n");
				String[] hl=heap.split("\n");
				String[] sl=sym.split("\n");
				String[] el=exe.split("\n");
				String[] ol=output.split("\n");
				int size,i;
				size=sl.length;
				for(i=0;i<size;i++){
					ds.addElement(sl[i]);
				}
				size=el.length;
				for(i=0;i<size;i++){
					de.addElement(el[i]);
				}
				size=ol.length;
				for(i=0;i<size;i++){
					dout.addElement(ol[i]);
				}
				size=hl.length;
				for(i=0;i<size;i++){
					dh.addElement(hl[i]);
				}
				size=ll.length;
				for(i=0;i<size;i++){
					dl.addElement(ll[i]);
				}
				outList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				outList.setModel(dout);
				exeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				exeList.setModel(de);
				symList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				symList.setModel(ds);
				heapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				heapList.setModel(dh);
				lockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				lockList.setModel(dl);
			}
		});
		debug.setBounds(48, 171, 153, 23);
		mypage.add(debug);
		
		JButton serialise = new JButton("Serialise");
		serialise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con.serialise();
			}
		});
		serialise.setBounds(48, 206, 153, 23);
		mypage.add(serialise);
		
		JButton deserialise = new JButton("Deserialise");
		deserialise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con.deserialise();
			}
		});
		deserialise.setBounds(48, 232, 153, 23);
		mypage.add(deserialise);
		
		JButton toFile = new JButton("Write to File");
		toFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con.WriteToFile();
			}
		});
		toFile.setBounds(48, 266, 153, 23);
		mypage.add(toFile);
		
		JLabel lblWhatDoYou = new JLabel("What do you want to do?");
		lblWhatDoYou.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblWhatDoYou.setBounds(48, 61, 209, 21);
		mypage.add(lblWhatDoYou);
		
		JButton test = new JButton("Introduce a test program");
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//v=10;lockEnter(1);print(v);fork(v=20;lockEnter(1);print(v);lockExit(1));print(v+1);lockExit(1)
				Statement sn=new CompoundStatement(new AssignStatement("v",new ConstantExpresion(10)),new CompoundStatement(new LockEnter(new ConstantExpresion(1)),new CompoundStatement(new PrintStatement(new VarExpresion("v")),new CompoundStatement(new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v",new ConstantExpresion(20)),new CompoundStatement(new LockEnter(new ConstantExpresion(1)),new CompoundStatement(new PrintStatement(new VarExpresion("v")),new LockExit(new ConstantExpresion(1)))))),new PrintStatement(new ArithmethicExpresion(new VarExpresion("v"),new ConstantExpresion(1),"+"))),new LockExit(new ConstantExpresion(1))))));
				Statement s=new CompoundStatement(new AssignStatement("a",new ConstantExpresion(3)),new ForkStatement(new CompoundStatement(new AssignStatement("b",new ArithmethicExpresion(new VarExpresion("a"),new ConstantExpresion(1),"+")),new IfStatement(new VarExpresion("a"),new PrintStatement(new VarExpresion("b")),new PrintStatement(new ArithmethicExpresion(new VarExpresion("a"),new VarExpresion("a"),"*"))))));
	        	con.AddProgram(sn);
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Program introduced");
			}
		});
		test.setBounds(48, 295, 153, 23);
		mypage.add(test);
		
		
		}
	private Statement ChooseStatement()
	 {
		 JFrame jframe = new JFrame();
			Object[] possibilities = {"Assignment", "Print", "If","Compound","Fork","LockEnter","LockExit"};
			String s = (String)JOptionPane.showInputDialog(
	                jframe,
	                "Choose a statement to add:\n",
	                "Statement Menu",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                possibilities,
	                "");
			if(s.compareTo("Assignment")==0)
			{
				JFrame frame = new JFrame();			
				s = (String)JOptionPane.showInputDialog(
		                frame,
		                "Give a variable name (string):\n",
		                "Variable expression",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "");
				if(s.compareTo("")==0)
				{
					JFrame rame=new JFrame("Info");
					JOptionPane.showMessageDialog(rame,"Please, give a string");
				}
				else
				{	Expresion e=ChooseExpresion();
					return new AssignStatement(s,e);
				}
			}
			if(s.compareTo("Print")==0)
			{
				Expresion e=ChooseExpresion();
				return new PrintStatement(e);
			}
			if(s.compareTo("If")==0)
			{
				Expresion e=ChooseExpresion();
				Statement st=ChooseStatement();
				Statement stt=ChooseStatement();
				return new IfStatement(e,st,stt);
			}
			if(s.compareTo("Compound")==0)
			{
				Statement st=ChooseStatement();
				Statement stt=ChooseStatement();
				return new CompoundStatement(st,stt);
			}
			if(s.compareTo("Fork")==0)
			{
				Statement st=ChooseStatement();
				return new ForkStatement(st);
			}
			if(s.compareTo("LockEnter")==0)
			{
				Expresion e=ChooseExpresion();
				return new LockEnter(e);
			}
			if(s.compareTo("LockExit")==0)
			{
				Expresion e=ChooseExpresion();
				return new LockExit(e);
			}
			return null;
	 }
	
	private Expresion ChooseExpresion()
	{
		JFrame jframe = new JFrame();
		Object[] possibilities = {"Constant", "Variable", "Arithmetic", "New","Heap Reading"};
		String s = (String)JOptionPane.showInputDialog(
                jframe,
                "Choose an expression:\n",
                "Expression Menu",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "");
		switch (s)
		{
		case "Constant":
			return DoConstant(s);
		case "Variable":
			return DoVariable(s);
		case "Arithmetic":
			return DoArith(s);
		case "New":
			return DoNew(s);
		case "Heap Reading":
			return DoHeap(s);
		default:
			return null;
		}
		/*
		if (s.compareTo("Constant")==0)
		{
			JFrame frame = new JFrame();			
			
			s = (String)JOptionPane.showInputDialog(
	                frame,
	                "Give an integer value:\n",
	                "Constant expression",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if(s.compareTo("")==0)
			{
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Please, give an integer value");
			}
			else
				return new ConstantExpresion(Integer.parseInt(s));
		}
		
		if(s.compareTo("Variable")==0)
		{
			JFrame frame = new JFrame();			
			
			s = (String)JOptionPane.showInputDialog(
	                frame,
	                "Give variable value (string):\n",
	                "Variable expression",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if(s.compareTo("")==0)
			{
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Please, give a varible (string)");
			}
			else
				return new VarExpresion(s);
		}
		
		if(s.compareTo("Arithmetic")==0)
		{
			Expresion e=ChooseExpresion();
			Expresion ex=ChooseExpresion();
			String op=ChooseOperation();
			if(op!="no")
				return new ArithmethicExpresion(e,ex,op);
			else op=ChooseOperation();
		}
		
		if (s.compareTo("New")==0)
		{
			JFrame frame = new JFrame();			
			
			s = (String)JOptionPane.showInputDialog(
	                frame,
	                "Give an integer value:\n",
	                "New expression",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if(s.compareTo("")==0)
			{
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Please, give an integer value");
			}
			else
				return new NewExpresion(Integer.parseInt(s));
		}if(s.compareTo("Heap Reading")==0)
		{
			JFrame frame = new JFrame();			
			
			s = (String)JOptionPane.showInputDialog(
	                frame,
	                "Give variable value (string):\n",
	                "Heap-reading expression",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if(s.compareTo("")==0)
			{
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Please, give a varible (string)");
			}
			else
				return new HeapReadingExpresion(s);
	}*/
	}
	public String ChooseOperation()
	{
		JFrame jframe = new JFrame();
		Object[] possibilities = {"+", "-", "*","/"};
		String s = (String)JOptionPane.showInputDialog(
                jframe,
                "Choose an operation:\n",
                "Operation Menu",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "");
		if(s.compareTo("+")==0)
		{
			return s;
		}
		if(s.compareTo("-")==0)
		{
			return s;
		}
		if(s.compareTo("*")==0)
		{
			return s;
		}
		if(s.compareTo("/")==0)
		{
			return s;
		}
		return "no";
	}
	private Expresion DoConstant(String s)
	{
		JFrame frame = new JFrame();			
		
		s = (String)JOptionPane.showInputDialog(
                frame,
                "Give an integer value:\n",
                "Constant expression",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
		if(s.compareTo("")==0)
		{
			JFrame rame=new JFrame("Info");
			JOptionPane.showMessageDialog(rame,"Please, give an integer value");
		}
		else
			return new ConstantExpresion(Integer.parseInt(s));
		return null;
	}
	private Expresion DoVariable(String s)
	{
		if(s.compareTo("Variable")==0)
		{
			JFrame frame = new JFrame();			
			
			s = (String)JOptionPane.showInputDialog(
	                frame,
	                "Give variable value (string):\n",
	                "Variable expression",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if(s.compareTo("")==0)
			{
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Please, give a varible (string)");
			}
			else
				return new VarExpresion(s);
		}
		return null;
	}
	private Expresion DoArith(String s)
	{
		if(s.compareTo("Arithmetic")==0)
		{
			Expresion e=ChooseExpresion();
			Expresion ex=ChooseExpresion();
			String op=ChooseOperation();
			if(op!="no")
				return new ArithmethicExpresion(e,ex,op);
			else op=ChooseOperation();
		}
		return null;
	}
	private Expresion DoNew(String s)
	{
		if (s.compareTo("New")==0)
		{
			JFrame frame = new JFrame();			
			
			s = (String)JOptionPane.showInputDialog(
	                frame,
	                "Give an integer value:\n",
	                "New expression",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if(s.compareTo("")==0)
			{
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Please, give an integer value");
			}
			else
				return new NewExpresion(Integer.parseInt(s));
		}
		return null;
	}
	private Expresion DoHeap(String s)
	{
		if(s.compareTo("Heap Reading")==0)
		{
			JFrame frame = new JFrame();			
			
			s = (String)JOptionPane.showInputDialog(
	                frame,
	                "Give variable value (string):\n",
	                "Heap-reading expression",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if(s.compareTo("")==0)
			{
				JFrame rame=new JFrame("Info");
				JOptionPane.showMessageDialog(rame,"Please, give a varible (string)");
			}
			else
				return new HeapReadingExpresion(s);
	}
		return null;
	}
}
