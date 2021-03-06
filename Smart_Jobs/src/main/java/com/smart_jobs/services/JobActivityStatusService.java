package com.smart_jobs.services;

import java.util.Date;
import java.util.List;

import com.smart_jobs.exceptions.AlreadyApplied;
import com.smart_jobs.exceptions.JobActivityStatusNotFound;
import com.smart_jobs.exceptions.JobPostNotFound;
import com.smart_jobs.web.model.JobActivityStatus;
import com.smart_jobs.web.model.JobPost;
/*
 * @author Mehul Thakor
 * @version 1.0
 * @creation date 9-sept-2021
 * @copyright Smart Jobs Ltd.
 * @description services for job activity status
 * 
 */
public interface JobActivityStatusService {

	public JobActivityStatus findActivityStatus(Long id) throws JobActivityStatusNotFound;
	
//	public List<JobActivityStatus> findStatusByJobId(int jid) throws JobActivityStatusNotFound;
	
	public List<JobActivityStatus> findActivityStatusByStatus(String astatus) throws JobActivityStatusNotFound;
	
	//create job activity status for user
	public String addActivityStatus(JobActivityStatus activity) ;
	
	//Update job activity status for user
	public String updateActivityStatus(String status);
	
	//Add reject date for rejected user
	public String AddRejectDate(Date date);
	
	/*
	 * @author bhargav.parmar@stltech.in
	 * @description Adding findAppliedJobs method to find the 
	 * 			applied jobs by job seeker
	 * 
	 *
	 */
	
	public String deleteActivityStatus(Long jobASId) throws JobActivityStatusNotFound;
	public List<JobPost> findAppliedJobs() throws JobPostNotFound;
	
	public void applyJobs(JobActivityStatus appliedRequest) throws AlreadyApplied;
}
