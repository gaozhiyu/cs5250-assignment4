package sche;

public class Task {
	
	private int pID;
	private int startTime;
	private int burstTime;
	private int lastExecTime;
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	@Override
	public String toString() {
		return "Task [pID=" + pID + ", startTime=" + startTime + ", burstTime=" + burstTime + "]";
	}
	
	public Task(int pID, int startTime, int burstTime, int lastExecTime) {
		super();
		this.pID = pID;
		this.startTime = startTime;
		this.burstTime = burstTime;
		this.lastExecTime = lastExecTime;
	}
	public int getLastExecTime() {
		return lastExecTime;
	}
	public void setLastExecTime(int lastExecTime) {
		this.lastExecTime = lastExecTime;
	}
	
	
	

}
