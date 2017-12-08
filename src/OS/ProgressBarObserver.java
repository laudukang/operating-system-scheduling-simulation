package OS;

import java.util.Observable;
import java.util.Observer;

public class ProgressBarObserver implements Observer {
	/**
	 * ����ʵ�ֹ۲��� ---����������ʾֵ�仯ʱ����statusTableModel
	 * 
	 * @author SavageGarden
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		String showValue = (String) arg;
		MainFrame.statusTableModel.setValueAt(Integer.parseInt(showValue.split(":")[1]),
				Integer.parseInt(showValue.split(":")[0]), 5);
	}
}