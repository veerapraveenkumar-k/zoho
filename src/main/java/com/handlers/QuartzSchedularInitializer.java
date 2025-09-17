package com.handlers;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import com.dao.InstanceDao;
import com.models.Instance;

import java.util.List;

public class QuartzSchedularInitializer {
	private static Scheduler scheduler;
	public static void startScheduler() throws Exception{
		scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		
		List<Instance> instances = InstanceDao.getAllInstanceList();
		
		for(Instance instance: instances) {
			JobDetail job = JobBuilder.newJob(UserImportJob.class)
					.withIdentity("Job_" + instance.getId(), "importJobs")
					.usingJobData("instanceId", instance.getId())
					.build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger_" + instance.getId(), "importTriggers")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInMinutes(3)
					.repeatForever())
					.build();
			scheduler.scheduleJob(job, trigger);
		}
	}
	
	public static void stopScheduler() throws Exception {
		if(scheduler != null) {
			scheduler.shutdown();
		}
	}
}
