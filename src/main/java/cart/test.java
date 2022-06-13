package cart;

public class test {
	public static void main(String[] args) {
		int price = 45000;
		int downprice = 0;
		
		if (downprice!=0 && price > downprice) {
			System.out.println(downprice);; // 할인가가 존재하면 장바구니 안의 가격을 할인가로 
		} else {
			System.out.println(price);;
		}
	}
}
