import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticateService } from '../authenticate.service';
import { Observable } from 'rxjs';
import { Issue } from '../issue.model';

@Injectable({
  providedIn: 'root'
})
export class IssueService {
  username: string;
  token:string;
  constructor(private httpClient: HttpClient, private authService: AuthenticateService) { }

  reportIssue(issue: string){
    this.username = this.authService.userAuthenticated.username;
    this.token = this.authService.accessToken;
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.token);
    return this.httpClient.post<any>(`http://localhost:8083/issue/${this.username}/${issue}`, {headers});
  }

  getByUser(username:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/issue/user/${username}`, {headers})
  }

  getAllIssues(){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/issue`, {headers})
  }

  getIssueByIssueId(issueId:number){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/issue/${issueId}`, {headers})
  }

  updateIssueDetails(issue:Issue){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.put<void>(`http://localhost:8083/issue/answer`,issue, {headers})
  }

}
