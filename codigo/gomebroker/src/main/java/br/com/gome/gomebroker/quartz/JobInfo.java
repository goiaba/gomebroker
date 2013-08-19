package br.com.gome.gomebroker.quartz;

import java.io.Serializable;
import java.util.Date;

public class JobInfo implements Serializable, Comparable<JobInfo> {

	private static final long serialVersionUID = 1L;
	private String triggerName;
	private String jobName;
	private String groupName;
	private Date nextFireTime;
	private Date previousFireTime;
	private String jobExpression;
	private boolean valid;
	private String cronExpression;

	public String getTriggerName() {
		return this.triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getNextFireTime() {
		return this.nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Date getPreviousFireTime() {
		return this.previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	public String getJobExpression() {
		return this.jobExpression;
	}

	public void setJobExpression(String jobExpression) {
		this.jobExpression = jobExpression;
	}

	public boolean isValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getCronExpression() {
		return this.cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int compareTo(JobInfo jobInfo) {
		if (this.jobExpression == null)
			return -1;
		if (jobInfo.getJobExpression() == null) {
			return 1;
		}
		return this.jobExpression.compareTo(jobInfo.getJobExpression());
	}
}
