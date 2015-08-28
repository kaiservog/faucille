package br.com.faucille.daemon;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Daemon {
	private static Daemon instance;
	private JobDetail job;
	private Trigger trigger;
	
	private Daemon() {
		job = newJob(UpdateJob.class)
				.withIdentity("updateJob", "group1")
				.usingJobData("type","FULL")
				.build();
		
		trigger = newTrigger()
			      .withIdentity("updateTrigger", "group1")
			      .startNow()
			      .withSchedule(SimpleScheduleBuilder.simpleSchedule()
			          //.withIntervalInHours(1)
			    	  .withIntervalInSeconds(5)
			          .repeatForever())
			      .build();
			
	}

	public static Daemon getInstance() {
		if (instance == null) {
			instance = new Daemon();
		}

		return instance;
	}
	
	public void start() {
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);		
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
