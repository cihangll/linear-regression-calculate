package com.cihangll.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cihangll.model.Test;

@Controller
public class ChartController {

	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public String chartPoint(Map<String, Object> model) {
		Test testForm = new Test();
		model.put("testForm", testForm);

		return "chartGenerate";
	}

	@RequestMapping(value = "/chart", method = RequestMethod.POST)
	public String generateChartPoints(@ModelAttribute("testForm") Test test, HttpServletRequest request) {

		/*
		 * Test için System.out.println("Oluşturulacak nokta sayısı: " +
		 * test.getPointValue());
		 */

		request.getSession().setAttribute("pointVal", test.getPointValue());
		return "showChart";
	}

	@RequestMapping(value = "/linearEquation")
	public String chartPoint() {
		return "linearEquation";
	}

}
