package OS;

import static OS.MyPath.addPath;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showConfirmDialog;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import static javax.swing.UIManager.setLookAndFeel;

/**
 * 
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuItem about;

	private JTextField createProcessJTextField;
	private String find = "";
	private JPanel controlerJPanel;
	private JRadioButton timeRadioButton;
	private JRadioButton priorityRadioButton;
	private JRadioButton SPNRadioButton;
	private JRadioButton SRTRadioButton;
	private JButton createProcessJButton;
	private JButton clearProcessJButton;
	private JButton startProcessJButton;
	private JButton resetProcessJButton;
	public static StatusTableModel statusTableModel;
	public static ProgressBarObservable progressBarObservable;
	public JTable statusTable;
	public JScrollPane statusScrollPane1;
	private int inputNumber = 0;
	private JPanel processListJPanel;
	public Object[][] object;
	public ArrayList<PCB> arraylist = new ArrayList<PCB>();
	public ArrayList<PCB> resetArraylist = new ArrayList<PCB>();
	public static boolean start_time = true; // ������ʱ�������
	private javax.swing.JLabel total_time_label;
	private boolean firstRun = true;
	private Runnable proc;
	private Set<Integer> set = new HashSet<Integer>();
	private Thread p1 = new Thread(), p2 = null;

	public static void main(String[] args) throws Exception {
		setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		setDefaultLookAndFeelDecorated(true);
		JFrame frame = new MainFrame();

		frame.setTitle("��������ϵͳ�Ľ��̵���_201230740312_���ſ�");

		frame.addWindowListener(new WindowAdapter() {// ��ȷ��Ҫ�˳���
			@Override
			public void windowClosing(WindowEvent e) {
				exit(0);// ֱ���˳�
				/*
				 * int option = showConfirmDialog(null, "��ȷ��Ҫ�˳���", "��ܰ��ʾ",
				 * JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); if
				 * (option == JOptionPane.YES_OPTION) { exit(0); }
				 */
			}
		});
		// com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.3f);

		frame.setUndecorated(false);// ���û����ô˴����װ��
		frame.setSize(1000, 680);
		// frame.setResizable(false);//���ô�������
		frame.setLocationRelativeTo(null);

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}

	public MainFrame() throws Exception {
		setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		setDefaultLookAndFeelDecorated(true);

		// ////////////////

		MenuBar jMenuBar = new MenuBar();
		Menu jMenu = new Menu("����");
		jMenu.setFont(new Font("TimesRoman", Font.BOLD, 14));// ���ò˵���ʾ������

		about = new MenuItem("����");
		about.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		about.addActionListener(new MenuItemListener());
		jMenu.add(about);

		jMenuBar.add(jMenu);
		this.setMenuBar(jMenuBar);// �˵�Bar�ŵ�JFrame��

		JPanel centerFrame = new JPanel(new GridLayout(1, 2, 10, 10));

		controlerJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 5));// (new

		createProcessJTextField = new JTextField(5); // 10,

		Font font = new Font("TimesRoman", Font.BOLD, 15);

		createProcessJTextField.setHorizontalAlignment(SwingConstants.CENTER);
		createProcessJTextField
				.setToolTipText("��ܰ��ʾ��������������Ҫ�����Ľ��̸�����Ȼ�����ұߵ�\"��������\"��ť��");

		createProcessJButton = new JButton("��������");
		clearProcessJButton = new JButton("���");
		ButtonListener buttonListener = new ButtonListener();
		createProcessJButton.addActionListener(buttonListener);
		clearProcessJButton.addActionListener(buttonListener);

		timeRadioButton = new JRadioButton();
		priorityRadioButton = new JRadioButton();
		SPNRadioButton = new JRadioButton();
		SRTRadioButton = new JRadioButton();
		startProcessJButton = new JButton("��ʼģ��");
		resetProcessJButton = new JButton("��λ");
		startProcessJButton.addActionListener(buttonListener);
		resetProcessJButton.addActionListener(buttonListener);

		timeRadioButton.setText("ʱ��Ƭ��ת");
		timeRadioButton.setSelected(true);
		priorityRadioButton.setText("������");
		SPNRadioButton.setText("��̽�������");
		// SPNRadioButton.setSelected(true);
		SRTRadioButton.setText("���ʣ��ʱ������");

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(timeRadioButton);
		radioButtonGroup.add(priorityRadioButton);
		radioButtonGroup.add(SPNRadioButton);
		radioButtonGroup.add(SRTRadioButton);

		EtchedBorder etchedBorder = (EtchedBorder) createEtchedBorder();
		TitledBorder title = new TitledBorder(etchedBorder);
		title.setTitleFont(font);
		title.setTitleColor(Color.BLACK);
		controlerJPanel.setBorder(title);

		createProcessJTextField.addKeyListener(new InputNumListener());

		controlerJPanel.add(createProcessJTextField);
		controlerJPanel.add(createProcessJButton);
		controlerJPanel.add(clearProcessJButton);

		controlerJPanel.add(timeRadioButton);
		controlerJPanel.add(priorityRadioButton);
		controlerJPanel.add(SPNRadioButton);
		controlerJPanel.add(SRTRadioButton);
		controlerJPanel.add(startProcessJButton);
		controlerJPanel.add(resetProcessJButton);
		total_time_label = new javax.swing.JLabel();
		total_time_label.setText("����ʱ��");
		controlerJPanel.add(total_time_label);

		this.add(controlerJPanel, BorderLayout.NORTH);

		processListJPanel = new JPanel(new BorderLayout(5, 5));

		TitledBorder processListTitle = new TitledBorder("����");
		title.setTitleFont(new Font("TimesRoman", Font.BOLD, 15));
		title.setTitleColor(Color.BLACK);
		processListJPanel.setBorder(processListTitle);

		statusTableModel = new StatusTableModel();
		statusTable = new JTable(statusTableModel);
		
		statusTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		statusTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		statusTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		statusTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		statusTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		statusTable.getColumnModel().getColumn(5).setPreferredWidth(250);
		//statusTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer cellrender = new DefaultTableCellRenderer();
		cellrender.setHorizontalAlignment(SwingConstants.CENTER);
		statusTable.setDefaultRenderer(String.class, cellrender);
		
		statusScrollPane1 = new JScrollPane(statusTable);
		JScrollPane scrollPane = new JScrollPane(statusTable);

		progressBarObservable = new ProgressBarObservable();
		ProgressBarObserver progressBarObserver = new ProgressBarObserver();
		progressBarObservable.addObserver(progressBarObserver);

		processListJPanel.add(scrollPane);

		centerFrame.add(processListJPanel);

		centerFrame.add(processListJPanel);
		this.add(centerFrame, BorderLayout.CENTER);
		this.add(addPath(), BorderLayout.SOUTH);
	}

	private class InputNumListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// System.out.println(jtfMessage.getText());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// System.out.println(jtfMessage.getText());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// System.out.println(jtfMessage.getText());
			find = createProcessJTextField.getText();
			isNum(find);
		}

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == createProcessJButton) {

				if (createProcessJTextField.getText().trim().length() == 0) {

					JOptionPane.showMessageDialog(null, "��������Ҫ�������̵ĸ�����",
							"ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				createRamdonProcess(inputNumber);// inputNumber

			} else if (e.getSource() == startProcessJButton) {

				if (arraylist.size() == 0) {

					JOptionPane.showMessageDialog(null, "���ȴ���������̣�", "ϵͳ��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (firstRun) {
					firstRun = false;
					Runnable time = new Timeing(total_time_label);
					Thread thread = new Thread(time);
					thread.start();
				}

				if (timeRadioButton.isSelected()) {
					proc = new ProgressBarThread1(statusTable, arraylist);
					p1 = new Thread(proc);
					p1.start();
				} else if (priorityRadioButton.isSelected()) {
					proc = new ProgressBarThread2(statusTable, arraylist);
					p1 = new Thread(proc);
					p1.start();
				} else if (SPNRadioButton.isSelected()) {
					proc = new ProgressBarThread3(statusTable, arraylist);
					p1 = new Thread(proc);
					p1.start();
				} else if (SRTRadioButton.isSelected()) {
					proc = new ProgressBarThread4(statusTable, arraylist);
					p1 = new Thread(proc);
					p1.start();
				}

			} else if (e.getSource() == resetProcessJButton) {
				resetRow();
			} else if (e.getSource() == clearProcessJButton) {
				clearRow();
			}
		}
	}

	void createRamdonProcess(int number) {

		object = new Object[number + 1][5];

		int numberCount = number;
		int[] array_processName = new int[numberCount + 1];
		int processName = 0; // ���̱�ʶ��
		int i = 1, count = 0;
		while (count < numberCount) {
			processName = 1 + (int) (Math.random() * 1000);
			if (!set.contains(processName)) {
				set.add(processName);
				array_processName[i++] = processName;
			}
			count++;
		}
		for (i = 1; i <= numberCount; i++) {
			int processPri = (int) (Math.random() * 5); // ���ȼ�
			int processTime = 1 + (int) (Math.random() * 7); // ����ʱ��
			PCB pcb = new PCB(array_processName[i], 1, processPri, processTime,
					processTime, new JProgressBar());
			PCB pcb2 = new PCB(array_processName[i], 1, processPri, processTime,
					processTime);

			// "���̱�ʶ��", "����״̬", "����������", "������ʱ��","ʣ������ʱ��","����״̬��"
			object[i] = new Object[] { pcb.name, "����̬", pcb.pri, pcb.total,
					pcb.time, 0 };

			statusTableModel.addProgressBar(object[i]);

			ProgressBarRenderer progressBarRenderer = new ProgressBarRenderer();
			TableColumn statusColumn = statusTable.getColumn("����״̬��");
			statusColumn.setCellRenderer(progressBarRenderer);

			resetArraylist.add(pcb2);
			arraylist.add(pcb);

			if (!firstRun) {

				if (timeRadioButton.isSelected()) {// 1

					((ProgressBarThread1) proc).size = arraylist.size();
					/*
					 * proc = new ProgressBarThread1(statusTable, arraylist); //
					 * p1.interrupt(); p1.stop();
					 * 
					 * p1 = new Thread(proc); p1.start();
					 */
				} else if (priorityRadioButton.isSelected()) {// 2
					proc = new ProgressBarThread2(statusTable, arraylist);
					p1.stop();
					p1 = new Thread(proc);
					p1.start();
				} else if (SPNRadioButton.isSelected()) {// 3
					((ProgressBarThread3) proc).runInterupt = true;
					// if(((ProgressBarThread3) proc).IsRunningOver()) {
					/*
					 * try { Thread.sleep(1000); } catch (InterruptedException
					 * ex) {
					 * 
					 * }
					 */
					// }
					((ProgressBarThread3) proc).map.put(pcb.name,
							arraylist.size() - 1);
					Collections.sort(arraylist, new Comparator<Object>() {
						@Override
						public int compare(Object o1, Object o2) {
							return ((PCB) o1).time - (((PCB) o2).time);
						}
					});
					ProgressBarThread3.runNum = 0;
					((ProgressBarThread3) proc).size = ((ProgressBarThread3) proc).arraylistTemp
							.size();

				} else if (SRTRadioButton.isSelected()) {// 4
					((ProgressBarThread4) proc).map.put(pcb.name,
							arraylist.size() - 1);
					Collections.sort(arraylist, new Comparator<Object>() {
						@Override
						public int compare(Object o1, Object o2) {
							return ((PCB) o1).time - (((PCB) o2).time);
						}
					});
					ProgressBarThread4.runNum = 0;
					((ProgressBarThread4) proc).size = ((ProgressBarThread4) proc).arraylistTemp
							.size();
				}

			}
		}
	}

	void isNum(String f) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(f);
		if (!isNum.matches()) {
			JOptionPane.showMessageDialog(null, "������벻��������", "ϵͳ��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			this.createProcessJTextField.setText("");
			inputNumber = 0;
		} else {
			inputNumber = Integer.parseInt(f);
		}
	}

	private class MenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == about) {

				ImageIcon myicon = new ImageIcon(getClass().getResource(
						"/OS/icon.gif"));
				JLabel lb1 = new JLabel("��л����ʹ�ã�");
				JLabel lb2 = new JLabel("����汾�ţ�v.25.12.2014");
				// JLabel lb5 = new JLabel("�ƻ�ʵ�ֵĹ��ܣ��Զ��崰��͸����");
				JLabel lb3 = new JLabel(
						"���ſ�(laudukang@gmail.com)                                              ");
				JLabel lb4 = new JLabel("2012���������ѧ�뼼��3��");
				MyLink mylink = new MyLink("�ҵ�����΢��", "http://weibo.com/adulte/");
				JPanel all = new JPanel(new GridLayout(6, 1, 10, 10));
				all.add(lb1);
				all.add(lb2);
				// all.add(lb5);
				all.add(lb3);
				all.add(lb4);
				all.add(mylink);
				showConfirmDialog(null, all, "����", JOptionPane.DEFAULT_OPTION,
						INFORMATION_MESSAGE, myicon);
			}
		}

	}

	private void resetRow() {
		if (arraylist.size() == 0) {
			JOptionPane.showMessageDialog(null, "���ȴ���������̣�", "ϵͳ��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (p1.isAlive()) {
			//System.out.println("p1.isAlive()");
			p1.stop();
		}
		arraylist = new ArrayList<PCB>();
		//arraylist = resetArraylist;
		for (int i = 0; i < resetArraylist.size(); i++) {
			statusTable.setValueAt(resetArraylist.get(i).total, i, 4);
			statusTable.setValueAt("����̬", i, 1);
			statusTable.setValueAt(0, i, 5);
			statusTable.setValueAt(resetArraylist.get(i).pri, i, 2);
			PCB pcb = new PCB(resetArraylist.get(i).name, 1, resetArraylist.get(i).pri, resetArraylist.get(i).total,
					resetArraylist.get(i).time, new JProgressBar());
			arraylist.add(pcb);
			//arraylist.get(i).time = resetArraylist.get(i).total;
		//	arraylist.get(i).pri = resetArraylist.get(i).pri;
		}
		MainFrame.start_time = true;
		firstRun = true;
		if (Thread.currentThread().isAlive()) {
			Thread.currentThread().stop();
		}
		total_time_label.setText("����ʱ��");
		if (timeRadioButton.isSelected()) {// 1
			((ProgressBarThread1) proc).size = arraylist.size();
		} else if (priorityRadioButton.isSelected()) {// 2

		} else if (SPNRadioButton.isSelected()) {// 3
			((ProgressBarThread3) proc).runInterupt = false;
			ProgressBarThread3.runNum = 0;
			((ProgressBarThread3) proc).size = arraylist.size();
		} else if (SRTRadioButton.isSelected()) {// 4
			ProgressBarThread4.runNum = 0;
			((ProgressBarThread4) proc).size = arraylist.size();
		}
	}

	private void clearRow() {
		if (arraylist.size() == 0) {
			JOptionPane.showMessageDialog(null, "���ȴ���������̣�", "ϵͳ��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		DefaultTableModel m = (StatusTableModel) statusTable.getModel();
		m.setRowCount(0);
		m.fireTableDataChanged();
		arraylist = new ArrayList<PCB>();
		resetArraylist = new ArrayList<PCB>();
		MainFrame.start_time = true;
		firstRun = true;

		if (p1.isAlive()) {
			//System.out.println("p1.isAlive()");
			p1.stop();
		}
		if (Thread.currentThread().isAlive()) {
			Thread.currentThread().stop();
		}
		total_time_label.setText("����ʱ��");
	}
}
