package com.javaintern.me;

import java.util.Scanner;
import java.io.*;

public class TempConverter {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String hist="";
		int ch=0;
		System.out.println("TEMPERATURE CONVERTER\n");
		System.out.println("Choose conversion to perform:");
		System.out.print("1) Celsius to Fahrenheit\n2) Celsius to Kelvin\n3) Fahrenheit to Celsius\n4) Fahrenheit to Kelvin\n5) Kelvin to Fahrenheit\n6) Kelvin to Celsius\n7) History\n8) Exit\n");
		do {
			System.out.print("\nEnter choice: ");
			ch=sc.nextInt();
			switch(ch) {
				case 1:
				case 2:
				System.out.print("Enter value of Celsius: ");
				double a=sc.nextDouble();
				Celsius cel=new Celsius(a);
				if(ch==1)
					hist=a+" C = "+cel.ctof()+" F";
				else
					hist=a + " C = " + cel.ctok() + " K";
				System.out.println(hist);
				History(hist);
				break;
		
				case 3:
				case 4:
				System.out.print("Enter value of Fahrenheit: ");
				double b=sc.nextDouble();
				Fahrenheit fah=new Fahrenheit(b);
				if(ch==3)
					hist=b + " F = " + fah.ftoc() + " C";
				else
					hist=b + " F = " + fah.ftok() + " K";
				System.out.println(hist);
				History(hist);
				break;
		
				case 5:
				case 6:
				System.out.print("Enter value of Kelvin: ");
				double d=sc.nextDouble();
				Kelvin kel=new Kelvin(d);
				if(ch==5)
					hist=d + " K = " + kel.ktoc() + " C";
				else
					hist=d + " K = " + kel.ktof() + " F";
				System.out.println(hist);
				History(hist);
				break;
	
				case 7: History("load");
				break;
				
				case 8: break;
		
				default: System.out.println("Invalid choice.");
			}
		} while(ch!=8);
	}
	
	public static void History(String line) {
		try {
			String path="C:\\Users\\prana\\eclipse-workspace\\trial\\src\\com\\javaintern\\me\\calchistory";
			File f=new File(path);
			Scanner s=new Scanner(f);
			if(line=="load") {
				if(s.hasNextLine()) {
					System.out.println("Your last calculation was: ");
					String data = s.nextLine();
					System.out.println(data);
				}
				else
					System.out.println("No calculation history is available.");
			}
			else {
				PrintStream out = new PrintStream(new FileOutputStream(path));
				out.print(line);
				out.close();
			}
		}
		catch(FileNotFoundException fnf) {
			System.out.println("The file that records the tool history is missing! Please create it.");
		}
	}
}
