import java.math.BigInteger;

public class Payment {
	private BigInteger cardNum;
	private String cardName;
	private int expDateM;
	private int expDateY;
	private int cvv;
	
	public Payment(BigInteger cardNum, String cardName, int expDateM, int expDateY, int cvv) {
		this.cardNum = cardNum;
		this.cardName = cardName;
		this.expDateM = expDateM;
		this.expDateY = expDateY;
		this.cvv = cvv;
	}

	public BigInteger getCardNum() {
		return cardNum;
	}

	public String getCardName() {
		return cardName;
	}

	public int getExpDateM() {
		return expDateM;
	}

	public int getExpDateY() {
		return expDateY;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCardNum(BigInteger cardNum) {
		this.cardNum = cardNum;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public void setExpDateM(int expDateM) {
		this.expDateM = expDateM;
	}

	public void setExpDateY(int expDateY) {
		this.expDateY = expDateY;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
}
