package br.com.gome.gomebroker.quartz;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.inject.Singleton;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import br.gov.frameworkdemoiselle.lifecycle.Shutdown;
import br.gov.frameworkdemoiselle.lifecycle.Startup;

@Singleton
public class GomebrokerScheduler {
	
	private static final String QUARTZ_PROPERTIES_FILE = "quartz.properties";
	private static final String QUARTZ_ENABLED_KEY = "org.quartz.enabled";
	private static final String JOB_CRON_EXPR = "expr";
	private static final String JOB_GROUP = "grupo";
	private static final String JOB_NAME = "nome";
	
	Properties quartzProperties = new Properties();

	private Scheduler scheduler;

	@Shutdown
	public void destroy() {
		
		if (null != scheduler) {
			
			try {
				
				scheduler.shutdown();
				
			} catch (SchedulerException e) {

				System.err.println("Erro ao efetuar shutdown no scheduler.");
			
			}
			
		}
		
	}
	
	@Startup
	public void init() {
		
		try {
		
			quartzProperties.load(GomebrokerScheduler.class.getClassLoader().getResourceAsStream("/" + QUARTZ_PROPERTIES_FILE));
			
			String quartzEnabled = (String) quartzProperties.getProperty(QUARTZ_ENABLED_KEY, "false");
			
			if (new Boolean(quartzEnabled).booleanValue()) {
			
				SchedulerFactory factory = new StdSchedulerFactory(QUARTZ_PROPERTIES_FILE);
				this.scheduler = factory.getScheduler();
				this.scheduler.start();
				loadJobsFromFile();
				
			}
			
		} catch (SchedulerException e) {

			e.printStackTrace();
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}
		
	}

	public boolean removeJob(String name, String group) {
		
		try {
		
			return getScheduler().deleteJob(new JobKey(name, group));
				
		} catch (SchedulerException e) {
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	public boolean addJob(Class<Job> jobClass, String name, String group, String cronExpr) throws SchedulerException {

		if (!getScheduler().checkExists(new TriggerKey(name, group))) {

			JobDetail job = JobBuilder
					.newJob(jobClass)
					.withIdentity(name, group)
					.build();
	
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(name, group)
					.withSchedule(CronScheduleBuilder.cronSchedule(cronExpr))
					.build();
			
			getScheduler().scheduleJob(job, trigger);
			
			return true;
			
		}
		
		return false;

	}
	
	public Scheduler getScheduler() {
		
		return this.scheduler;
		
	}
	
	private void loadJobsFromFile() {
		
		Map<String, Map<String, String>> jobs = loadJobPropertiesFromFile();
		
		for (Entry<String, Map<String, String>> entry : jobs.entrySet()) {
			
			try {

				@SuppressWarnings("unchecked")
				Class<Job> clazz = (Class<Job>) Class.forName(entry.getKey());

				this.addJob(clazz, entry.getValue().get(JOB_NAME), entry.getValue().get(JOB_GROUP), entry.getValue().get(JOB_CRON_EXPR));
				
			} catch (ClassNotFoundException e) {
				
				// TODO Criar log da exception
				e.printStackTrace();

			} catch (SchedulerException e) {
			
				// TODO Criar log da exception
				e.printStackTrace();
			}
			
		}
		
	}
	
	public Map<String, Map<String, String>> loadJobPropertiesFromFile() {
		
		Map<String, Map<String, String>> jobs = new HashMap<String, Map<String,String>>(0);
		
		for (String key : quartzProperties.stringPropertyNames()) {

			if (key.startsWith(this.getClass().getPackage().getName())) {
				
				String value = quartzProperties.getProperty(key);

				Map<String, String> jobProperties = new HashMap<String, String>();
				
				for (String prop : value.split(";")) {
					
					String[] keyValue = prop.split("=");
					jobProperties.put(keyValue[0].trim(), keyValue[1].trim());
					
				}
				
				jobs.put(key, jobProperties);
				
			}
			
		}
		
		return jobs;
		
	}
	
}
