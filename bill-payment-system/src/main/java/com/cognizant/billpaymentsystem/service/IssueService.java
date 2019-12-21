/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.List;

import com.cognizant.billpaymentsystem.model.Issue;

/**
 * @author 810512
 *
 */
public interface IssueService {
    public void reportIssue(String username,String issue);
    public List<Issue> getByUser(String username);
    public List<Issue> getAllIssuesByisAnswered();
    public Issue getIssueById(int issueId);
    public void updateIssueDetails(Issue issue);
}
