package com.javaintern.me;

public class Kelvin {
	double k;
	Kelvin(double x) {
		k=x;
	}
	double ktof() {
		return ((ktoc()*9/5)+32);
	}
	double ktoc() {
		return k-273;
	}
}

