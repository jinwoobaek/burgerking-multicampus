package vo;

public class Order {
	private String order_No;
	private String manu_List;
	private String emp_No;
	private String order_Time;
	private int total_Price;
	
	
	public String getOrder_No() {
		return order_No;
	}
	public void setOrder_No(String order_No) {
		this.order_No = order_No;
	}
	public String getManu_List() {
		return manu_List;
	}
	public void setManu_List(String manu_List) {
		this.manu_List = manu_List;
	}
	public String getEmp_No() {
		return emp_No;
	}
	public void setEmp_No(String emp_No) {
		this.emp_No = emp_No;
	}
	public String getOrder_Time() {
		return order_Time;
	}
	public void setOrder_Time(String order_Time) {
		this.order_Time = order_Time;
	}
	public int getTotal_Price() {
		return total_Price;
	}
	public void setTotal_Price(int total_Price) {
		this.total_Price = total_Price;
	}
}
