package sche;

public class ScheTask {
	private int Pid;
	private int startTime;
	private int execTime;
	private int waitTime;
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getExecTime() {
		return execTime;
	}
	public void setExecTime(int execTime) {
		this.execTime = execTime;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public ScheTask(int pid, int startTime, int execTime, int waitTime) {
		super();
		Pid = pid;
		this.startTime = startTime;
		this.execTime = execTime;
		this.waitTime = waitTime;
	}
	
	public ScheTask() {
		super();
	}
	@Override
	public String toString() {
		return "ScheTask [Pid=" + Pid + ", startTime=" + startTime + ", execTime=" + execTime + ", waitTime=" + waitTime
				+ "]";
	}
	
	
	
	
}
