package sche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		List<Task> pList = retriveInput();
		roundRobin(pList,2);

	}

	private static void roundRobin(List<Task> pList, int quatm) {
		// TODO Auto-generated method stub
		int currTime = 0;
		List<ScheTask> scheTaskList = new ArrayList<ScheTask>();
		List<Task> availableTaskList = new ArrayList<Task>();
		Task curr = null;
		while (!pList.isEmpty() || !availableTaskList.isEmpty()) { 
			
			if (!pList.isEmpty() &&pList.get(0).getStartTime() <= currTime) {
				curr = pList.get(0);
				pList.remove(curr);
				//availableTaskList.add(curr);
			} else {
				if(!availableTaskList.isEmpty() && availableTaskList.get(0).getStartTime()<=currTime) {
					curr = availableTaskList.get(0);
					availableTaskList.remove(curr);
				}else {
					currTime++;
					if(pList.isEmpty() && availableTaskList.isEmpty() )
					{
						break;
					}
					else 
						continue;
				}
				
			}

				if (curr.getBurstTime() <= quatm) {
					ScheTask scheTask = new ScheTask();
					scheTask.setStartTime(currTime);
					scheTask.setExecTime(curr.getBurstTime());
					scheTask.setPid(curr.getpID());
					scheTask.setWaitTime(currTime - curr.getLastExecTime());
					scheTaskList.add(scheTask);
					currTime += curr.getBurstTime();//process
					//availableTaskList.add(curr);
				} else {
					ScheTask scheTask = new ScheTask();
					scheTask.setStartTime(currTime);
					scheTask.setExecTime(quatm);
					scheTask.setPid(curr.getpID());
					scheTask.setWaitTime(currTime - curr.getLastExecTime());
					scheTaskList.add(scheTask);
					curr.setLastExecTime(currTime);
					curr.setBurstTime(curr.getBurstTime()-quatm);
					availableTaskList.add(curr);
					currTime += quatm;//process
				}
			}
		//}
		for (ScheTask scheTask : scheTaskList) {
			System.out.println(scheTask);
		}
	}

	public static List<Task> retriveInput() {

		List<Task> taskList = new ArrayList<Task>();
		try {
			File file = new File("input.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			String[] arr;
			while ((st = br.readLine()) != null) {
				arr = st.split(" ");
				taskList.add(new Task(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]),
						Integer.parseInt(arr[1])));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (Task temp : taskList) {
//			System.out.print(temp);
//			System.out.println(temp.getLastExecTime());
//		}
		return taskList;
	}

}
