/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.billpaymentsystem.model.Issue;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.repository.IssueRepository;
import com.cognizant.billpaymentsystem.repository.UserRepository;

/**
 * @author 810512
 *
 */

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	/*
	 * @see com.cognizant.billpaymentsystem.service.IssueService#reportIssue(java.lang.String, java.lang.String)
	 */
	@Override
	public void reportIssue(String username, String issue) {
		User user = userRepository.findByUsername(username);
		Issue newIssue = new Issue();
		newIssue.setUser(user);
		newIssue.setIssue(issue);
		newIssue.setAnswered(false);
		issueRepository.save(newIssue);
	}

	@Override
	public List<Issue> getAllIssuesByisAnswered() {
		return issueRepository.findByIsAnswered(false);
	}

	@Override
	public Issue getIssueById(int issueId) {
		return issueRepository.findByIssueId(issueId);
	}

	@Override
	public void updateIssueDetails(Issue issue) {
		Issue issueToUpdate=issueRepository.findByIssueId(issue.getIssueId());
		issueToUpdate.setAnswer(issue.getAnswer());
		issueToUpdate.setAnswered(true);
		issueRepository.save(issueToUpdate);
	}

	@Override
	public List<Issue> getByUser(String username) {
		User user=userRepository.findByUsername(username);
		return issueRepository.findByUser(user);
	}

}

 

