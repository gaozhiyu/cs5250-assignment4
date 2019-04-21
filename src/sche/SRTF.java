package sche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SRTF {

	public static void main(String[] args) {
		List<Task> pList = retriveInput();
		int size = pList.size();
		List<ScheTask> scheTaskList = srtf(pList);
		
		int wait = 0;
		
		for (ScheTask scheTask : scheTaskList) {
			System.out.println(scheTask);
			wait += scheTask.getWaitTime();
		}
		
		System.out.println(wait*1.0/size);
		FileUtil.writeToFile("SRTF.txt", scheTaskList, wait*1.0/size);

	}

	private static List<ScheTask> srtf(List<Task> pList) {
		// TODO Auto-generated method stub
		int currTime = 0;
		List<ScheTask> scheTaskList = new ArrayList<ScheTask>();
		List<Task> availableTaskList = new ArrayList<Task>();
		Task curr = null;
		while (!pList.isEmpty() || !availableTaskList.isEmpty()) { 
			
			if (!pList.isEmpty() && pList.get(0).getStartTime() <= currTime) {
				curr = pList.get(0);
				pList.remove(curr);
				availableTaskList.add(curr);
			}
			
			
			//retrieve the shortest from the list if null
			
			Task next = retrieveRemainTime(availableTaskList); // take the next small;
			if(next ==null) {
				if(pList.isEmpty())
					break;
				else {
					currTime = pList.get(0).getStartTime();
					continue;
				}
			}
			//Do the comparing(next.getBurstTime(); ) 
			if(compareTime(next,pList,currTime)) {//next.get 
				availableTaskList.remove(next);
				ScheTask scheTask = new ScheTask();
				scheTask.setPid(next.getpID());
				scheTask.setWaitTime(currTime - next.getLastExecTime());
				scheTask.setStartTime(currTime);
				scheTask.setExecTime(next.getBurstTime());
				scheTaskList.add(scheTask);
				currTime+= scheTask.getExecTime();
			} else {
				int execTime = pList.get(0).getStartTime()- currTime;
				ScheTask scheTask = new ScheTask();
				scheTask.setPid(next.getpID());
				scheTask.setWaitTime(currTime - next.getLastExecTime());
				scheTask.setStartTime(currTime);
				scheTask.setExecTime(execTime);
				scheTaskList.add(scheTask);
				next.setBurstTime(next.getBurstTime()-execTime);
				currTime += execTime;
			}
		}
			
		for (ScheTask scheTask : scheTaskList) {
			System.out.println(scheTask);
		}
		return scheTaskList;
	}

	private static Task retrieveRemainTime(List<Task> availableTaskList) {
		// TODO Auto-generated method stub
		if(availableTaskList.size()==0) {
			return null;
		}else {
			Task t = availableTaskList.get(0);
			for (int i =0; i< availableTaskList.size();i++) {
				if(t.getBurstTime()>availableTaskList.get(i).getBurstTime()) {
					t = availableTaskList.get(i);
				}
			}
			return t;
		}
	}

	private static boolean compareTime(Task next, List<Task> pList, int currTime) {
		// TODO Auto-generated method stub
		if(pList.size()==0) {
			return true;
		}else {
			if(next.getBurstTime() + currTime <= pList.get(0).getStartTime()) {
				return true;
			}
		}
		return false;
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
