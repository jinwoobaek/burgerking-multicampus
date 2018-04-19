package vo;

public class Stock {
	private int StockNo;
	private String StockName;
	private int Amount;
	private int ValidPeriod;
	private String EnteringDate;

	public int getStockNo() {
		return StockNo;
	}

	public void setStockNo(int stockNo) {
		StockNo = stockNo;
	}

	public String getStockName() {
		return StockName;
	}

	public void setStockName(String stockName) {
		StockName = stockName;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public int getValidPeriod() {
		return ValidPeriod;
	}

	public void setValidPeriod(int validPeriod) {
		ValidPeriod = validPeriod;
	}

	public String getEnteringDate() {
		return EnteringDate;
	}

	public void setEnteringDate(String enteringDate) {
		EnteringDate = enteringDate;
	}

}
