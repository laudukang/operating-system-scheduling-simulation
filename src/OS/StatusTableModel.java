package OS;

import javax.swing.table.DefaultTableModel;

public class StatusTableModel extends DefaultTableModel {
	/**
	 * ״̬�����ģ����
	 * 
	 * @author SavageGarden
	 * 
	 */

	public Object[] rowData = { "8","7", "7", "7", "7" ,0};

	public StatusTableModel() {
		super();
		addColumn("���̱�ʶ��");
		addColumn("����״̬");
		addColumn("����������");
		addColumn("������ʱ��");
		addColumn("ʣ������ʱ��");
		addColumn("����״̬��");
	}

	/**
	 * ����Ϊ���ɱ༭
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * ��״̬�����һ��������
	 * 
	 */
	public void addProgressBar(Object[] object) {
		//rowData[0] = "�ļ�" + (this.getRowCount() + 1);
		addRow(object);
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
	//	return super.getColumnClass(columnIndex);
		return getValueAt(0, columnIndex).getClass();
	}
}