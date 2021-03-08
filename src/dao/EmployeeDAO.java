package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;

import dto.EmployeeDTO;

public class EmployeeDAO {

	// 싱글톤 패턴 적용
	private static EmployeeDAO instance = new EmployeeDAO();
	private DBManager manager;

	private EmployeeDAO() {
			manager = DBManager.getInstance();
		}

	public static EmployeeDAO getInstance() {
		if (instance == null)
			instance = new EmployeeDAO();
		return instance;
	}

	// 로그인
	public EmployeeDTO loginEmployee(String name, String eno) {
		EmployeeDTO dto = null;
		String sql = "select * from employee where name like ? and eno like ?";
		Connection conn = manager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, eno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(rs, pstmt);
		}
		return dto;
	}

	// 직급 이름 확인
	public String selectPositionName(int position) {
		String position_name = "";
		String sql = "select name from POSITION_LIST where pno = ?";
		Connection conn = manager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, position);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				position_name = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(rs, pstmt);
		}
		return position_name;
	}

	// 사원 정보 리스트
	public ArrayList<EmployeeDTO> getEmployeeList() {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		String sql = "select e.eno, e.name, e.department, e.position, s.salary from employee e, EMPLOYEE_SALARY s where e.eno like s.eno";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(
						new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().close(rs, pstmt);
		}

		return list;
	}

	// 사원 검색
	public ArrayList<EmployeeDTO> searchEmployee(String kind, String search) {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		String sql = "select e.eno, e.name, e.department, e.position, s.salary from employee e, EMPLOYEE_SALARY s where e.eno like s.eno and e."
				+ kind + " like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(
						new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().close(rs, pstmt);
		}

		return list;
	}

	// 정보 수정
	public int updateEmployee(EmployeeDTO dto) {
		int count = 0;
		String sql = "update employee set name = ?, department=?, position=? where eno like ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getDepartment());
			pstmt.setInt(3, dto.getPosition());
			pstmt.setString(4, dto.getEno());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(null, pstmt);
		}

		return count;
	}

	// 정보수정 연봉
	public int updateEmployeeSalary(String eno, int salary) {
		int count = 0;
		String sql = "update employee_salary set salary = ? where eno like ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, salary);
			pstmt.setString(2, eno);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(null, pstmt);
		}

		return count;
	}

	// 정보 삭제
	public int deleteEmployee(String eno) {
		int count = 0;
		String sql = "delete from employee where eno like ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, eno);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(null, pstmt);
		}

		return count;
	}

	// 연봉 정보 삭제
	public void deleteEmployeeSalary(String eno) {
		String sql = "delete from employee_salary where eno like ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, eno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(null, pstmt);
		}

	}

	// 사번 검색
	public EmployeeDTO selectDTO(String eno) {
		EmployeeDTO dto = null;
		String sql = "select * from employee where eno like ?";
		Connection conn = manager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(rs, pstmt);
		}
		return dto;
	}

	// 사원 등록
	public int insertEmployee(EmployeeDTO dto) {
		int count = 0;
		String sql = "insert into employee values(?,?,?,?)";
		Connection conn = manager.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEno());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getDepartment());
			pstmt.setInt(4, dto.getPosition());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(null, pstmt);
		}
		return count;
	}

	// 사원 연봉 등록
	public void insertEmployeeSalary(String eno, int salary) {
		String sql = "insert into employee_salary values(?,?)";
		Connection conn = manager.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eno);
			pstmt.setInt(2, salary);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(null, pstmt);
		}
	}

	// 낮은 연봉 사원 검색
	public ArrayList<EmployeeDTO> getLowSalary() {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		String sql = "select e.eno, e.name, e.department " + "from EMPLOYEE e, "
				+ "(select eno from(select rownum, eno, salary, rank() over(order by salary asc) from EMPLOYEE_SALARY where rownum < 6)) a "
				+ "where e.eno like a.eno";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), 0, 0));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().close(rs, pstmt);
		}

		return list;
	}

	// 낮은 연봉 사원 연봉 증가
	public int setLowSalaryUp() {
		int count = 0;
		String sql = "update EMPLOYEE_SALARY set salary = round(salary*1.1) where eno in (select eno from(select rownum, eno, salary, rank() over(order by salary asc) from EMPLOYEE_SALARY where rownum < 6))";
		PreparedStatement pstmt = null;

		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close(null, pstmt);
		}
		return count;
	}

}