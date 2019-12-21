import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/authenticate.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router,private authService:AuthenticateService) { }

  ngOnInit() {
  }

  isAuthenticated(){
    this.authService.authSource="customer";
    return this.authService.loggedIn;
  }
  isAdmin(){
    return this.authService.isAdminUser();
  }
  getUser(){
    return this.authService.userAuthenticated;
  }
  isVendor(){    
    if(this.authService.userAuthenticated.role==='vendor')
      return true;
    return false;
  }
  isUser(){
    if(this.authService.userAuthenticated.role==='user')
      return true;
    return false;
  }
  onSignOut(){
    this.authService.logOut();
    this.router.navigate([this.authService.redirectUrl]);
  }
  homeLink(){    
    if(this.authService.userAuthenticated.role!=='notavailable'){
      if(this.authService.userAuthenticated.role==="admin"){
        this.router.navigate(['/vendor-approval']);
      }
      else if(this.authService.userAuthenticated.role==="user"){
        this.router.navigate(['/vendors']);
      }
      else{
        this.router.navigate(['/vendor-edit']);
      }
    }
    else{
      this.router.navigate(['/login']);
    }
  }

}
