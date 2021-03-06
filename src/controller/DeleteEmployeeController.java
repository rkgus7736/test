package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.EmployeeService;

public class DeleteEmployeeController {

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String eno = request.getParameter("eno");
		int count = EmployeeService.getInstance().deleteEmployee(eno);
		if(count != 0)
			request.setAttribute("result", "사원 정보 삭제 성공");
		else {
			try {
				response.getWriter().append("<script>alert('사원 정보 삭제 실패');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ModelAndView view = new ModelAndView("EmplistView.do", false);
		return view;
	}
}
