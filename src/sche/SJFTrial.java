package sche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SJFTrial {

	private static double alpha = 0;
	private final static double initalEstimate = 5;
	private static HashMap<Integer,Estimate> estmap = new HashMap<Integer, Estimate>();
	public static void main(String[] args) {
		for(int i=1; i<100; i++) {
			estmap = new HashMap<Integer, Estimate>();
			alpha= 1.0*i /100;
			System.out.println("for the alpha is " + alpha + " the waiting time is "+sjfTrial());
		}
	}
	public static double sjfTrial() {
		List<Task> pList = retriveInput();
		
		int size = pList.size();
		List<ScheTask> scheTaskList = sjf(pList);
		
		int wait = 0;
		
		for (ScheTask scheTask : scheTaskList) {
			//System.out.println(scheTask);
			wait += scheTask.getWaitTime();
		}
		
		//System.out.println(wait*1.0/size);
		//FileUtil.writeToFile("SJF.txt", scheTaskList, wait*1.0/size);
		return  wait*1.0/size;
	}

	private static List<ScheTask> sjf(List<Task> pList) {
		// TODO Auto-generated method stub
		int currTime = 0;
		List<ScheTask> scheTaskList = new ArrayList<ScheTask>();
		List<Task> availableTaskList = new ArrayList<Task>();
		Task curr = null;
		while (pList.size()+ availableTaskList.size()!=0) { 
			
	        Iterator iterator = pList.iterator();
	  
	        while (pList.size()>0) {
	        	Task task = pList.get(0);
	        	if(task.getStartTime()<= currTime) {
	        		pList.remove(task);
	        		availableTaskList.add(task);
	        	}else {
	        		break;
	        	}
	        }

			Task next = retrieveRemainTime(availableTaskList); // take the next small;
			if(next ==null) {
				if(pList.isEmpty())
					break;
				else {
					currTime = pList.get(0).getStartTime();
					continue;
				}
			}else {
				ScheTask scheTask = new ScheTask();
				scheTask.setPid(next.getpID());
				scheTask.setWaitTime(currTime - next.getStartTime());
				scheTask.setStartTime(currTime);
				scheTask.setExecTime(next.getBurstTime());
				scheTaskList.add(scheTask);
				availableTaskList.remove(next);
				currTime+= scheTask.getExecTime();
				
			}
		}
			
			

		return scheTaskList;
	}


	private static Task retrieveRemainTime(List<Task> availableTaskList) {
		// TODO Auto-generated method stub
		if(availableTaskList.size()==0) {
			return null;
		}else {
			Task taskSel = availableTaskList.get(0);
			double estimationSel = calculateEstimation(taskSel);
//			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i =1;i < availableTaskList.size();i++ ) {
				
				Task temp =  availableTaskList.get(i);
//				if(!map.containsKey(temp.getpID())) {
//					continue;
//				}else {
					double tempEst= calculateEstimation(temp);
//					map.put(temp.getpID(),1);
					if(tempEst < estimationSel) {
						estimationSel = tempEst;
						taskSel = temp;
					}
//				}

			}
			if(estmap.containsKey(taskSel.getpID())) {
				Estimate temp = estmap.get(taskSel.getpID());
				temp.setActual(taskSel.getBurstTime());
			}else {
				Estimate temp = new Estimate(taskSel.getpID(), 5, taskSel.getBurstTime());
				estmap.put(taskSel.getpID(), temp);		
			}
			return taskSel;
			
				
		}
	}

	private static double calculateEstimation(Task task) {
		// TODO Auto-generated method stub
		if(estmap.containsKey(task.getpID())) {
			Estimate temp = estmap.get(task.getpID());
			return alpha * temp.getActual()+ (1-alpha)* temp.getHeur();
		}else {
			return initalEstimate;
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
		return taskList;
	}

}
