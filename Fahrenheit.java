package com.javaintern.me;

public class Fahrenheit {
	double f;
	Fahrenheit(double x) {
		f=x;
	}
	double ftoc() {
		return ((f-32)*5/9);
	}
	double ftok() {
		return ftoc()+273;
	}
}

