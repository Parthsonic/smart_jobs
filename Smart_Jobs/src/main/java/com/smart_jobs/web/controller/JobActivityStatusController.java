package com.smart_jobs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart_jobs.exceptions.AlreadyApplied;
import com.smart_jobs.exceptions.JobPostNotFound;
import com.smart_jobs.repository.JobActivityRepository;
import com.smart_jobs.services.JobActivityStatusService;
import com.smart_jobs.web.model.JobActivityStatus;
import com.smart_jobs.web.model.JobPost;

@RestController
@RequestMapping("/api/v1")
public class JobActivityStatusController {
	
	/*
	 * JobSeeker and Manager will be calling the same Api
	 * to find out who applied for job.
	 */

	@Autowired
	private JobActivityStatusService activityStatus;
	
	@Autowired
	private JobActivityRepository activityRepo;

	@PostMapping("/applyJob")
	public ResponseEntity<String> applyJob(@RequestBody JobActivityStatus applyingJobRequest) {
		try {
			activityStatus.applyJobs(applyingJobRequest);
		} catch (AlreadyApplied e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Sorry Already Applied", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Applied Successfully", HttpStatus.OK);

	}

	@GetMapping("/getAppliedJobs")
	public List<JobPost> findAppliedJobs() throws JobPostNotFound {
		return activityStatus.findAppliedJobs();
	}
	
	@PostMapping("/find")
	public JobActivityStatus findjob(@RequestParam("jbid") Long jbId,@RequestParam("jsid") Long jsId) {
		return activityRepo.findByJobPost_JobPostIdAndJspersonal_SrNo(jbId, jsId);
	}

}
