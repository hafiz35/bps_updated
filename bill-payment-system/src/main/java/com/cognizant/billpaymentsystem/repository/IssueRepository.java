/**
 * 
 */
package com.cognizant.billpaymentsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.billpaymentsystem.model.Issue;
import com.cognizant.billpaymentsystem.model.User;

/**
 * @author 810512
 *
 */
public interface IssueRepository extends JpaRepository<Issue, Integer>{
	List<Issue> findByUser(User user);
	List<Issue> findByIsAnswered(boolean isAnswered);
	Issue findByIssueId(int issueId);
}
