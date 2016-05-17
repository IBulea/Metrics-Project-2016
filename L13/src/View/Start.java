package View;

import java.util.Scanner;

import Repository.Repository;
import View.*;
import Model.Statement;
import Controller.Controller;

public class Start {

	public static void main(String[] args) {
		Controller ctr=new Controller();
		View g=new View(ctr);
		g.setVisible(true);
	}
}
