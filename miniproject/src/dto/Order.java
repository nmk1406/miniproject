package dto;

import java.sql.Date;

public class Order {
	private int id;
	private Date date;
	private int cid;
	private double totalmoney;

	public Order() {
		
	}

	public Order(int id, Date date, int cid, double totalmoney) {
		this.id = id;
		this.date = date;
		this.cid = cid;
		this.totalmoney = totalmoney;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
}