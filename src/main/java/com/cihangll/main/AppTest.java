package com.cihangll.main;

import com.cihangll.model.DenklemBulma;

public class AppTest {
	public static void main(String[] args) {
		DenklemBulma d = new DenklemBulma(100);

		System.out.println("Coordinates: " + d.getCoordinates());
		System.out.println("X Values: " + d.getXValueList());
		System.out.println("Y Values: " + d.getYValueList());
		System.out.println("X Ort: " + d.getXMeanValue());
		System.out.println("Y Ort: " + d.getYMeanValue());
		System.out.println("x - xOrt: " + d.getXMinusTheXMeanList());
		System.out.println("y - yOrt: " + d.getYMinusTheYMeanList());
		System.out.println("[(x - xOrt) ^ 2]: " + d.getXDistenceSquareList());
		// System.out.println(d.getYDistenceSquareList());
		System.out.println("∑[(x - xOrt) ^ 2]: " + d.getXSumDistenceSquareValue());
		// System.out.println(d.getYSumDistenceSquareValue());
		System.out.println("[(x - xOrt) * (y-yOrt)]: " + d.getXDistenceMultiplyYDistence());
		System.out.println("∑[(x - xOrt) * (y-yOrt)]: " + d.getSumXDistenceMultiplyYDistence());
		System.out.println("a value: " + d.find_a());
		System.out.println("b value: " + d.find_b());
		System.out.println("Denklem: " + d.getLinearRegression());
		System.out.println("Denkleme Göre Yeni x ve y noktalari\n" + d.generatePointsFromLinearRegression());
	}
}
