package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.EmployeeDTO;
import model.ModelAndView;
import service.EmployeeService;

public class UpdateEmployeeController {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String eno = request.getParameter("eno");
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		int position = Integer.parseInt(request.getParameter("position"));
		int salary = Integer.parseInt(request.getParameter("salary"));
		EmployeeDTO dto = new EmployeeDTO(eno, name, department, position);
		int count = EmployeeService.getInstance().updateEmployee(dto);
		int count2 = EmployeeService.getInstance().updateEmployeeSalary(eno, salary);

		if(count != 0 && count2 != 0)
			request.setAttribute("result", "사원 정보 수정 성공");
		else {
			try {
				response.getWriter().append("<script>alert('사원 정보 수정 실패');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ModelAndView view = new ModelAndView("EmplistView.do", false);
		return view;
	}
}
