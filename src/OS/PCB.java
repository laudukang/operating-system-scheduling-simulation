package OS;

import javax.swing.JProgressBar;

/**
 * 
 * @author laudukang
 */
public class PCB {

	int name; // ���̱�ʶ��
	int status; // ����״̬
	int pri; // ����������
	int total;// ������ʱ��
	int time; // ʣ������ʱ�䣬 ��ʱ��ƬΪ��λ�� ������ 0 ʱ�ý�����ֹ
	int next; // ��һ�����̿��ƿ��λ��
	int psw;//״̬��
	JProgressBar bar;//������
	
	public PCB(int name, int status, int pri, int total, int time,JProgressBar bar) {
		this.name = name;
		this.status = status;
		this.pri = pri;
		this.total = total;
		this.time = time;
		this.bar=bar;
	}

	public PCB(int name, int status, int pri, int total, int time) {
		this.name = name;
		this.status = status;
		this.pri = pri;
		this.total = total;
		this.time = time;
	}

}
