package com.fastcampus.ch4.java.practice;

class FactorialTest {
	public static void main(String args[]) {
		System.out.println(factorial(4)); // FactorialTest.factorial(4)
	}

	static long factorial(int n) {
		long result=0;

		if (n == 1) return 1;		

		return n * factorial(n-1); // ¸Þ¼­µå ÀÚ½ÅÀ» È£ÃâÇÑ´Ù.
	}
}