package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.EmployeeDTO;
import model.ModelAndView;
import service.EmployeeService;

public class EmployeeListViewController {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		// �������
		HttpSession session = request.getSession();
		int position = (int) session.getAttribute("position");
		String department = (String) session.getAttribute("department");
		if(request.getAttribute("result") != null)
			request.setAttribute("result", request.getAttribute("result"));
		
		if(position > 3 && department.equals("�λ�")) {
			ArrayList<EmployeeDTO> list = null;
			if(request.getAttribute("list") != null)
				list = (ArrayList<EmployeeDTO>) request.getAttribute("list");
			else
				list = EmployeeService.getInstance().getEmployeeList();
				
			request.setAttribute("list", list);
		} else
			try {
				response.getWriter().append("<script>alert('������ �����ϴ�.<br>������ ���� �λ�� ���� �̻� ������ �� �ֽ��ϴ�.');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		return new ModelAndView("employee_manager.jsp", false);
	}
}
