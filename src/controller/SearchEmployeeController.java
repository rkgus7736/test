package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.EmployeeDTO;
import model.ModelAndView;
import service.EmployeeService;

public class SearchEmployeeController {

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		ArrayList<EmployeeDTO> list = EmployeeService.getInstance().searchEmployee(kind, search);
		
		request.setAttribute("list", list);
		return new ModelAndView("EmplistView.do", false);
	}
}
