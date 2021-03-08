package service;

import java.util.ArrayList;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

public class EmployeeService {
		
		// 싱글톤 적용
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
		
		// 로그인
		public EmployeeDTO loginEmployee(String name, String eno) {
			return dao.loginEmployee(name, eno);
		}
		
		// 직급 이름 확인
		public String selectPositionName(int position) {
			return dao.selectPositionName(position);
		}
		
		// 사원정보 리스트
		public ArrayList<EmployeeDTO> getEmployeeList() {
			return dao.getEmployeeList();
		}
		
		// 사원 검색
		public ArrayList<EmployeeDTO> searchEmployee(String kind, String search) {
			return dao.searchEmployee(kind, search);
		}
		
		// 사원 정보 수정
		public int updateEmployee(EmployeeDTO dto) {
			return dao.updateEmployee(dto);
		}
		
		// 사원 연봉 정보 수정
		public int updateEmployeeSalary(String eno, int salary) {
			return dao.updateEmployeeSalary(eno, salary);
		}
		
		// 사원 정보 삭제
		public int deleteEmployee(String eno) {
			dao.deleteEmployeeSalary(eno);
			return dao.deleteEmployee(eno);
		}
		
		// 사원 등록
		public int insertEmployee(EmployeeDTO dto) {
			int result = 0;
			if(dao.selectDTO(dto.getEno()) == null) {
				result = dao.insertEmployee(dto);
				dao.insertEmployeeSalary(dto.getEno(), dto.getSalary());
			}
			return result;
		}
		
		// 낮은 연봉 사원 찾기
		public ArrayList<EmployeeDTO> lowSalary() {
			return dao.getLowSalary();
		}
		
		// 낮은 연봉 사원 연봉 증가
		public int lowSalaryUp() {
			return dao.setLowSalaryUp();
		}
}
