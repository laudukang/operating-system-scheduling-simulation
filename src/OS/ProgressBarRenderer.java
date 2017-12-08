package OS;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ProgressBarRenderer extends DefaultTableCellRenderer {
	/**
	 * ����������Ⱦ��
	 * 
	 * @author SavageGarden
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  JProgressBar jProgressBar;

	public ProgressBarRenderer() {
		super();
		setOpaque(true);
		jProgressBar = new GyrJProgressBar();//new JProgressBar();
		// �Ƿ���ʾ�����ַ���
		jProgressBar.setStringPainted(true);
		//Font font = new Font("TimesRoman", Font.BOLD, 15);
		//jProgressBar.setFont(font);
		jProgressBar.setMinimum(0);
		jProgressBar.setMaximum(100);
		//jProgressBar.setBackground(new Color(255, 255, 255));
		// �Ƿ���Ʊ߿�
		jProgressBar.setBorderPainted(true);
		//jProgressBar.setMaximum(processTime);
		//System.out.println(processTime);
		jProgressBar.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Integer i = (Integer) value;
		jProgressBar.setValue(i);
		return jProgressBar;
	}

	public JProgressBar getjProgressBar() {
		return jProgressBar;
	}
}