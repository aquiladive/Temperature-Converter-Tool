package com.javaintern.me;

public class Celsius {
	double c;
	Celsius(double x) {
		c=x;
	}
	double ctof() {
		return ((c*9/5)+32);
	}
	double ctok() {
		return c+273;
	}
}
