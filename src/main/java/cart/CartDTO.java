package cart;

public class CartDTO {
	private int cart_id;
	private String id;
	private String name;
	private String p_id;
	private String pname;
	private int cart_price;
	private int price;
	private int downprice;
	private int money;
	private int amount;
	private String pImg;
	
	
	//Getter Setter
	
	
	public String getName() {
		return name;
	}
	public String getpImg() {
		return pImg;
	}
	public void setpImg(String pImg) {
		this.pImg = pImg;
	}
	public int getCart_price() {
		return cart_price;
	}
	public void setCart_price(int cart_price) {
		this.cart_price = cart_price;
	}
	public int getDownprice() {
		return downprice;
	}
	public void setDownprice(int downprice) {
		this.downprice = downprice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
