import { Component, OnInit } from '@angular/core';
import { IssueService } from '../issue.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Issue } from 'src/app/issue.model';
import { AuthenticateService } from 'src/app/authenticate.service';

@Component({
  selector: 'app-reportissue',
  templateUrl: './reportissue.component.html',
  styleUrls: ['./reportissue.component.css']
})
export class ReportissueComponent implements OnInit {
  reportIssueForm:FormGroup;
  issueListOfUser:Issue[];
  username:string;
  issueReported:boolean=false;
  issueListOfUserEmpty:boolean=false;

  constructor(private issueService:IssueService,private authService:AuthenticateService) { }

  ngOnInit() {

    this.reportIssueForm=new FormGroup({
      'issue':new FormControl(null,Validators.required)
    })
    this.username=this.authService.userAuthenticated.username;
    this.issueService.getByUser(this.username).subscribe((issueList:Issue[])=>{
      this.issueListOfUser=issueList;
      if(this.issueListOfUser.length===0){
        this.issueListOfUserEmpty=true;
      }
      else{
        this.issueListOfUserEmpty=false;
      }
    })

  }

  submitIssue(){
    
    this.issueService.reportIssue(this.reportIssueForm.get('issue').value).subscribe((data)=>{
      this.issueReported=true;
      this.refreshPage();
    });
  }

  reportIssue(){
    this.issueReported=false;
  }
  refreshPage(){
    this.username=this.authService.userAuthenticated.username;
    this.issueService.getByUser(this.username).subscribe((issueList:Issue[])=>{
      this.issueListOfUser=issueList;
      if(this.issueListOfUser.length===0){
        this.issueListOfUserEmpty=true;
      }
      else{
        this.issueListOfUserEmpty=false;
      }
    })
  }

}
