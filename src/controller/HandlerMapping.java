package controller;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();
	
	private HandlerMapping() {
		
	}
	
	public static HandlerMapping getInstance() {
		if(instance == null)
			instance = new HandlerMapping();
		return instance;
	}
	
	public Controller createController(String command) {
		
		Controller controller = null;
		switch(command) {
		case "login.do":
			controller = (Controller) new LoginController();
			break;
		case "logout.do":
			controller = (Controller) new LogoutController();
			break;
		case "EmplistView.do":
			controller = (Controller) new EmployeeListViewController();
			break;
		case "searchEmp.do":
			controller = (Controller) new SearchEmployeeController();
			break;
		case "updateEmp.do":
			controller = (Controller) new UpdateEmployeeController();
			break;
		case "deleteEmp.do":
			controller = (Controller) new DeleteEmployeeController();
			break;
		case "insertEmp.do":
			controller = (Controller) new InsertEmployeeController();
			break;
		}
		return controller;
	}
}
