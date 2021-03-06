package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.EmployeeDTO;
import model.ModelAndView;
import service.EmployeeService;

public class LoginController {

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String eno = request.getParameter("eno");
		HttpSession session = request.getSession();	// request에서 세션 정보를 읽어옴
		session.setMaxInactiveInterval(60*10);
		EmployeeDTO dto = EmployeeService.getInstance().loginEmployee(name, eno);
		ModelAndView view = new ModelAndView();
		try {
			if(dto == null)
				response.getWriter().append("<script>alert('입력하신 정보를 확인하세요.');history.back();</script>");
			else {
				// 직급 정보 확인
				String position_name = EmployeeService.getInstance().selectPositionName(dto.getPosition());
				
				session.setAttribute("login", true);
				session.setAttribute("eno", dto.getEno());
				session.setAttribute("name", dto.getName());
				session.setAttribute("department", dto.getDepartment());
				session.setAttribute("position", dto.getPosition());
				session.setAttribute("position_name", position_name);
				
				
				view.setPage("emp_listView.do");
				view.setSendRedirect(false);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return view;
	}

}
