package com.cihangll.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cihangll.model.Point;
import com.cihangll.service.DenklemService;

@Controller
public class JSONController {

	@Autowired
	private DenklemService denklemService;

	@RequestMapping("/getPoint")
	@ResponseBody
	public Point getPoint() {
		// Basit test
		return new Point(3, 5);
	}

	@RequestMapping(value = "/getPoints", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public List<Point> getPointCoordinates(HttpServletRequest request) {
		// Request verisi test
		// System.out.println(request.getSession().getAttribute("pointVal"));

		int val = (int) request.getSession().getAttribute("pointVal");
		denklemService.setDenklem(val);
		return denklemService.generateCoordinates();
	}

	@RequestMapping(value = "/getEquationValues", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public List<Point> getEquations(HttpServletRequest request) {

		// Eger Olusturulacak nokta girilmisse islem yap.
		if (request.getSession().getAttribute("pointVal") != null) {
			System.out.println(denklemService.getLinearRegression());
			return denklemService.generatePointsFromLinearRegression();
		} else {
			return null;
		}

	}

}
