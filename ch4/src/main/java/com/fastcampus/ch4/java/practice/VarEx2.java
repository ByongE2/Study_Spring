package com.fastcampus.ch4.java.practice;

class VarEx2 {
	public static void main(String[] args) {
		int x = 10;
		int y = 20;
		int tmp = 0;
	
		System.out.println("x:"+ x + " y:" + y);
		
		tmp = x;
		x = y;
		y = tmp;

		System.out.println("x:"+ x + " y:" + y);
	}
}