/**
 * 
 */
package com.cognizant.billpaymentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.billpaymentsystem.model.Issue;
import com.cognizant.billpaymentsystem.service.IssueService;

/**
 * @author 810512
 *
 */

@RestController
@RequestMapping("/issue")
public class IssueController {
	
	@Autowired
	IssueService issueService;
	
	@PostMapping("/{username}/{issue}")
    public void reportIssue(@PathVariable String username,@PathVariable String issue) {
		issueService.reportIssue(username,issue);
    }
	
	@GetMapping("/user/{username}")
	public List<Issue> getByUser(@PathVariable String username){
		return issueService.getByUser(username);
	}
	
	@GetMapping
	public List<Issue> getAllIssues() {
		return issueService.getAllIssuesByisAnswered();
	}
	
	@GetMapping("/{issueId}")
	public Issue getIssueByIssueId(@PathVariable int issueId) {
		return issueService.getIssueById(issueId);
	}
	
	@PutMapping("/answer")
	public void updateIssueDetails(@RequestBody Issue issue) {
		issueService.updateIssueDetails(issue);
	}
	
}
