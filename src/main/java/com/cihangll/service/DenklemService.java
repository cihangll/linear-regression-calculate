package com.cihangll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cihangll.model.DenklemBulma;
import com.cihangll.model.Point;

@Service
public class DenklemService {

	private DenklemBulma denklem;

	public void setDenklem(int value) {
		denklem = new DenklemBulma(value);
	}

	public List<Point> generateCoordinates() {
		return denklem.getCoordinates();
	}

	public List<Point> generatePointsFromLinearRegression() {
		return denklem.generatePointsFromLinearRegression();
	}

	public String getLinearRegression() {
		return denklem.getLinearRegression();
	}

}
