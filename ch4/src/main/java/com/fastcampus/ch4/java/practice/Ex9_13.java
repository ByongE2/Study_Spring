package com.fastcampus.ch4.java.practice;

import static java.lang.Math.*;
import static java.lang.System.*;

class Ex9_13 {
	public static void main(String args[]) {
		double val = 90.7552;
		out.println("round("+ val +")="+round(val));// ¹Ý¿Ã¸²

		val *= 100;
		out.println("round("+ val +")="+round(val));// ¹Ý¿Ã¸²

		out.println("round("+ val +")/100  =" + round(val)/100);   // ¹Ý¿Ã¸²
		out.println("round("+ val +")/100.0=" + round(val)/100.0); // ¹Ý¿Ã¸²
		out.println();
		out.printf("ceil(%3.1f)=%3.1f%n",  1.1, ceil(1.1));    // ¿Ã¸²
		out.printf("floor(%3.1f)=%3.1f%n", 1.5, floor(1.5));   // ¹ö¸²	
		out.printf("round(%3.1f)=%d%n",    1.1, round(1.1));   // ¹Ý¿Ã¸²
		out.printf("round(%3.1f)=%d%n",    1.5, round(1.5));   // ¹Ý¿Ã¸²
		out.printf("rint(%3.1f)=%f%n",     1.5, rint(1.5));    // ¹Ý¿Ã¸²
		out.printf("round(%3.1f)=%d%n",   -1.5, round(-1.5));  // ¹Ý¿Ã¸²
		out.printf("rint(%3.1f)=%f%n",    -1.5, rint(-1.5));   // ¹Ý¿Ã¸²
		out.printf("ceil(%3.1f)=%f%n",    -1.5, ceil(-1.5));   // ¿Ã¸²
		out.printf("floor(%3.1f)=%f%n",   -1.5, floor(-1.5));  // ¹ö¸²
	}
}