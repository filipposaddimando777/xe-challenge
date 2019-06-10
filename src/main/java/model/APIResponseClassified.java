package model;

public class APIResponseClassified extends Classified {
	
	int Words;
	String Price;

	public int getWords() {
		return Words;
	}

	public void setWords(int words) {
		Words = words;
	}
	
	public String getPrice() {
		return Price;
	}
	
	public void setPrice(String price) {
		Price = price;
	}
	
}
