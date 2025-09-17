package com.handlers;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.services.ImportUserService;

public class UserImportJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int instanceId = context.getJobDetail().getJobDataMap().getInt("instanceId");
		boolean result = false;
		try {
			result = ImportUserService.importUsers(instanceId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(result) {
			System.out.println("Instance " + instanceId + " imported Successfully");
		}else {
			System.out.println("Instance " + instanceId + " imported Failed");
		}
		
	}
}
