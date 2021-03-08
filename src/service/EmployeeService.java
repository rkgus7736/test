package service;

import java.util.ArrayList;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

public class EmployeeService {
		
		// �̱��� ����
		private static EmployeeService instance = new EmployeeService();
		private EmployeeDAO dao;
		private EmployeeService() {
			dao = EmployeeDAO.getInstance();
		}
		
		public static EmployeeService getInstance() {
			if(instance == null)
				instance = new EmployeeService();
			return instance;
		}
		
		// �α���
		public EmployeeDTO loginEmployee(String name, String eno) {
			return dao.loginEmployee(name, eno);
		}
		
		// ���� �̸� Ȯ��
		public String selectPositionName(int position) {
			return dao.selectPositionName(position);
		}
		
		// ������� ����Ʈ
		public ArrayList<EmployeeDTO> getEmployeeList() {
			return dao.getEmployeeList();
		}
		
		// ��� �˻�
		public ArrayList<EmployeeDTO> searchEmployee(String kind, String search) {
			return dao.searchEmployee(kind, search);
		}
		
		// ��� ���� ����
		public int updateEmployee(EmployeeDTO dto) {
			return dao.updateEmployee(dto);
		}
		
		// ��� ���� ���� ����
		public int updateEmployeeSalary(String eno, int salary) {
			return dao.updateEmployeeSalary(eno, salary);
		}
		
		// ��� ���� ����
		public int deleteEmployee(String eno) {
			dao.deleteEmployeeSalary(eno);
			return dao.deleteEmployee(eno);
		}
		
		// ��� ���
		public int insertEmployee(EmployeeDTO dto) {
			int result = 0;
			if(dao.selectDTO(dto.getEno()) == null) {
				result = dao.insertEmployee(dto);
				dao.insertEmployeeSalary(dto.getEno(), dto.getSalary());
			}
			return result;
		}
		
		// ���� ���� ��� ã��
		public ArrayList<EmployeeDTO> lowSalary() {
			return dao.getLowSalary();
		}
		
		// ���� ���� ��� ���� ����
		public int lowSalaryUp() {
			return dao.setLowSalaryUp();
		}
}
