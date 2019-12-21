/**
 * 
 */
package com.cognizant.billpaymentsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author 810512
 *
 */

@Entity
@Table(name="issue")
public class Issue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="issue_id")
	private int issueId;

	@Column(name="issue")
	private String issue;

	@Column(name="answer")
	private String answer;

	@Column(name="is_answered")
	private boolean isAnswered;

	@ManyToOne
	@JoinColumn(name="issue_user_id")
	private User user;
	
	public Issue() {
		super();
	}

	public Issue(int issueId, String issue, String answer, User user) {
		super();
		this.issueId = issueId;
		this.issue = issue;
		this.answer = answer;
		this.user = user;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

