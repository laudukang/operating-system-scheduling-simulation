package OS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;

//import  OS_UI.MainFrame.statusTableModel;
public class ProgressBarThread4 extends Thread {

	public static int runNum = 0;

	public static int getRunNum() {
		return runNum;
	}

	public static void setRunNum(int runNum) {
		ProgressBarThread4.runNum = runNum;
	}

	ArrayList<PCB> arraylistTemp;
	JTable statusTable;
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	int size = 0;

	@SuppressWarnings("unchecked")
	public ProgressBarThread4(JTable statusTable, ArrayList<PCB> arraylist) {
		// System.out.println("in ProgressBarThread3");
		// map = new HashMap<Integer, Integer>();
		int row = 0;
		// System.out.println("map=" + arraylist.get(0).time);
		this.arraylistTemp = arraylist;
		for (PCB pcb : arraylistTemp) {
			map.put(pcb.name, row);
			row++;
			// System.out.println(pcb.time);
		}

		this.statusTable = statusTable;
		Collections.sort(arraylistTemp, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return ((PCB) o1).time - (((PCB) o2).time);
			}
		});
		// System.out.println("map=" + arraylistTemp.get(0).time);
		/*
		 * for (PCB pcb : arraylistTemp) { System.out.println(pcb.time); }
		 */
	}

	@Override
	public void run() {

		runNum = 0;
		int end = 0;
		int stopCount = 1;
		size = arraylistTemp.size();

		while (end == 0) {

			while (arraylistTemp.get(runNum).time != 0) {

				// System.out.println(runNum + "  " +
				// arraylistTemp.get(runNum).time);
				arraylistTemp.get(runNum).time--;
				// "进程标识符", "进程状态", "进程优先数", "总运行时间","剩余运行时间","进程状态条"
				System.out.println("runNum=" + runNum + "   "
						+ map.get(arraylistTemp.get(runNum).name) + "  map="
						+ map.size());
				statusTable.setValueAt(arraylistTemp.get(runNum).time,
						map.get(arraylistTemp.get(runNum).name), 4);
				statusTable.setValueAt("运行态",
						map.get(arraylistTemp.get(runNum).name), 1);

				statusTable.setValueAt(
						(arraylistTemp.get(runNum).total - arraylistTemp
								.get(runNum).time)
								* 100
								/ arraylistTemp.get(runNum).total, map
								.get(arraylistTemp.get(runNum).name), 5);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}
				if (arraylistTemp.get(runNum).time == 0) {
					statusTable.setValueAt("终止态",
							map.get(arraylistTemp.get(runNum).name), 1);
					runNum++;
				} else {
					statusTable.setValueAt("就绪态",
							map.get(arraylistTemp.get(runNum).name), 1);
				}
				if (runNum >= arraylistTemp.size()) {
					System.out.println("runNum 000=" + runNum);
					runNum = 0;
					System.out.println("number0 ="
							+ arraylistTemp.get(runNum).time);
				}

			}

			stopCount = 0;
			while (arraylistTemp.get(runNum).time == 0) {
				System.out.println(stopCount + "  runNum in while=" + runNum
						+ " time=" + arraylistTemp.get(runNum).time);
				end = 0;
				stopCount++;
				runNum++;
				if (runNum == arraylistTemp.size())
					runNum = 0;
				if (stopCount > size) {
					System.out.println("stopCount=" + stopCount);
					end = 1;
					MainFrame.start_time = false;
					break;
				}
			}
			// System.out.println(stopCount + "  runNum out while=" + runNum);
		}

	}

	public boolean IsRunningOver() {
		System.out.println("in IsRunningOver()");
		if (arraylistTemp.get(runNum).time != 0) {
			return true;
		} else {
			return false;
		}

	}
}