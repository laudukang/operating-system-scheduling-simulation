package OS;

import java.util.Observable;

public class ProgressBarObservable extends Observable {
	/**
	 * ����ʵ�ֹ۲��߹۲�Ķ���---����������ʾֵ
	 * 
	 * @author SavageGarden
	 * 
	 */
	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
		setChanged();
		notifyObservers(price);
	}
}