package model;

public class ModelAndView {
	private String page;	// �̵��� ������ ���
	private boolean sendRedirect;	// ������ �̵� ���
	public ModelAndView(String page, boolean sendRedirect) {
		super();
		this.page = page;
		this.sendRedirect = sendRedirect;
	}
	public ModelAndView() {
		// TODO Auto-generated constructor stub
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public boolean isSendRedirect() {
		return sendRedirect;
	}
	public void setSendRedirect(boolean sendRedirect) {
		this.sendRedirect = sendRedirect;
	}
	
}
