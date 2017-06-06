package com.cihangll.model;

import java.util.ArrayList;
import java.util.List;

public class DenklemBulma {

	// List of coordinates
	private List<Point> coordinates;

	private ArrayList<Point> points = new ArrayList<Point>();

	private ArrayList<Float> xValues = new ArrayList<Float>();

	private ArrayList<Float> yValues = new ArrayList<Float>();

	private ArrayList<Float> xDistenceList = new ArrayList<Float>();

	private ArrayList<Float> yDistenceList = new ArrayList<Float>();

	private ArrayList<Float> xSquareList = new ArrayList<Float>();

	private ArrayList<Float> multiplicationValue = new ArrayList<Float>();

	// Constructor Method
	public DenklemBulma(int value) {
		super();
		coordinates = generateCoordinates(value);
		points = new ArrayList<>();
		xValues = getXValueList();
		yValues = getYValueList();
		xDistenceList = getXMinusTheXMeanList();
		yDistenceList = getYMinusTheYMeanList();
		xSquareList = getXDistenceSquareList();
		multiplicationValue = getXDistenceMultiplyYDistence();
	}

	// generate (x,y) coordinates
	private List<Point> generateCoordinates(int value) {

		for (int i = 0; i < value; i++) {
			float valueX = (float) (Math.random() * 100);
			float valueY = (float) (Math.random() * 100);
			points.add(new Point(valueX, valueY));
		}
		return points;
	}

	// x values - independent variable
	public ArrayList<Float> getXValueList() {

		for (Point point : coordinates) {
			xValues.add(point.getX());
		}
		return xValues;
	}

	// y values - dependent variable
	public ArrayList<Float> getYValueList() {

		for (Point point : coordinates) {
			yValues.add(point.getY());
		}
		return yValues;
	}

	// xOrt
	public float getXMeanValue() {
		float value = 0f;
		for (float xValue : getxValues()) {
			value += xValue;
		}
		return (value / getxValues().size());
	}

	// yOrt
	public float getYMeanValue() {
		float value = 0f;
		for (float yValue : getyValues()) {
			value += yValue;
		}
		return (value / getyValues().size());
	}

	// (x - xORT) - Bize ortalama nokta ile nokta arasindaki uzakligi
	// vericektir.
	public ArrayList<Float> getXMinusTheXMeanList() {

		float xMean = getXMeanValue();

		for (float xValue : getxValues()) {
			xDistenceList.add(xValue - xMean);
		}
		return xDistenceList;
	}

	// (y - yORT) - Bize ortalama nokta ile nokta arasindaki uzakligi
	// vericektir.
	public ArrayList<Float> getYMinusTheYMeanList() {

		float yMean = getYMeanValue();

		for (float yValue : getyValues()) {
			yDistenceList.add(yValue - yMean);
		}
		return yDistenceList;
	}

	// value ^ 2
	public float powMethod(float value) {
		/*
		 * İslem yaptigimiz degerler int degerler olacagindan double tutmaya
		 * gerek yok.
		 */
		return (float) Math.pow(value, 2);
	}

	// [(x-xOrt) ^ 2]
	public ArrayList<Float> getXDistenceSquareList() {

		for (float xDistance : getxDistenceList()) {
			xSquareList.add(powMethod(xDistance));
		}
		return xSquareList;
	}

	// [(y - yOrt) ^ 2]
	// public ArrayList<Integer> getYDistenceSquareList() {
	//
	// ArrayList<Integer> ySquareList = new ArrayList<Integer>();
	// for (float yDistance : getYMinusTheYMeanList()) {
	// ySquareList.add(powMethod(yDistance));
	// }
	// return ySquareList;
	// }

	// ∑[(x - xOrt) ^ 2] - Toplamini al
	public float getXSumDistenceSquareValue() {
		float sumValueX = 0f;
		for (float squareValueX : getxSquareList()) {
			sumValueX += squareValueX;
		}
		return sumValueX;
	}

	// ∑[(y - yOrt) ^ 2] - Toplamini al
	// public float getYSumDistenceSquareValue() {
	// float sumValueY = 0f;
	// for (float squareValueY : getYDistenceSquareList()) {
	// sumValueY += squareValueY;
	// }
	// return sumValueY;
	// }

	// [(x - xOrt) * (y-yOrt)]
	public ArrayList<Float> getXDistenceMultiplyYDistence() {

		for (int i = 0; i < coordinates.size(); i++) {
			multiplicationValue.add(getxDistenceList().get(i) * getyDistenceList().get(i));
		}
		return multiplicationValue;
	}

	// ∑[(x - xOrt) * (y-yOrt)]
	public float getSumXDistenceMultiplyYDistence() {

		float sumValue = 0f;
		for (float squareValue : getMultiplicationValue()) {
			sumValue += squareValue;
		}
		return sumValue;
	}

	// (y = a ^ x + b) denklemindeki 'a' degerini bulma metodu.
	// ( ∑[(x - xOrt) * (y-yOrt)] / [(x-xOrt) ^ 2] )
	public float find_a() {
		return getSumXDistenceMultiplyYDistence() / getXSumDistenceSquareValue();
	}

	// (y = a ^ x + b) denklemindeki 'b' degerini bulma metodu.
	// (b = yOrt - a ^ xOrt)
	public float find_b() {
		return (getYMeanValue() - (find_a() * getXMeanValue()));
	}

	// Denklemin String halini veren metot.
	public String getLinearRegression() {
		StringBuilder sb = new StringBuilder("");
		sb.append("y = ");
		// Eger point degeri 1 ise hesaplama yapmamasi, dogrudan y degerini
		// almasi icin.
		if (getCoordinates().size() == 1) {
			sb.append("0 * x + ");
			sb.append(getCoordinates().get(0).getY());
		} else {
			sb.append(find_a());
			sb.append("x + ");
			sb.append(find_b());
		}
		return sb.toString();
	}

	// (y = a ^ x + b) denklemindeki 'y' degerini bulma metodu.
	/*
	 * y degerini bulmak icin parametre olarak x degerine ihtiyacimiz var.a ve b
	 * degerini bildigimiz icin y degerini kolaylikla bulabiliriz.
	 */
	public float find_y(float xValue) {
		// Eger point degeri 1 ise dogrudan hesaplama yapmasi icin.
		if (getCoordinates().size() == 1) {
			return getCoordinates().get(0).getY();
		}
		return ((find_a() * xValue) + find_b());
	}

	// genereate (x,y) coordinates from Linear Regression Equation.Parcali Dogru
	// Veren Metot.
	/*
	 * Yani sadece sahip oldugumuz x noktalari icin dogru cizimi.
	 */
	// public List<Point> generatePointsFromLinearRegression2() {
	//
	// ArrayList<Point> pointList = new ArrayList<>();
	// for (float xValue : getXValueList()) {
	// pointList.add(new Point(xValue, find_y(xValue)));
	// }
	// return pointList;
	// }

	// Tam Dogru Cizmek Icin Kullanilacak olan metot.
	/* Yani 0 ile 100 degeri arasindaki butun x degerleri icin dogru cizimi. */
	public List<Point> generatePointsFromLinearRegression() {

		ArrayList<Point> pointList = new ArrayList<>();
		for (int i = 0; i <= 100; i++) {
			// x degerine gore gelen y degeri 100 degerinden tasmamasi icin.
			if (find_y(i) <= 100) {
				pointList.add(new Point(i, find_y(i)));
			}
		}
		return pointList;
	}

	// GETTER AND SETTER
	public List<Point> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Point> coordinates) {
		this.coordinates = coordinates;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public ArrayList<Float> getxValues() {
		return xValues;
	}

	public void setxValues(ArrayList<Float> xValues) {
		this.xValues = xValues;
	}

	public ArrayList<Float> getyValues() {
		return yValues;
	}

	public void setyValues(ArrayList<Float> yValues) {
		this.yValues = yValues;
	}

	public ArrayList<Float> getxDistenceList() {
		return xDistenceList;
	}

	public void setxDistenceList(ArrayList<Float> xDistenceList) {
		this.xDistenceList = xDistenceList;
	}

	public ArrayList<Float> getyDistenceList() {
		return yDistenceList;
	}

	public void setyDistenceList(ArrayList<Float> yDistenceList) {
		this.yDistenceList = yDistenceList;
	}

	public ArrayList<Float> getxSquareList() {
		return xSquareList;
	}

	public void setxSquareList(ArrayList<Float> xSquareList) {
		this.xSquareList = xSquareList;
	}

	public ArrayList<Float> getMultiplicationValue() {
		return multiplicationValue;
	}

	public void setMultiplicationValue(ArrayList<Float> multiplicationValue) {
		this.multiplicationValue = multiplicationValue;
	}

}
