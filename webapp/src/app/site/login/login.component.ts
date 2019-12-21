import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticateService } from 'src/app/authenticate.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoginValid = true;
  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthenticateService) {
  }

  ngOnInit() {
    if(this.authService.loggedIn){
      if (this.authService.role === 'ROLE_USER') {
        this.authService.redirectUrl = "/vendors";
    } else if(this.authService.role === 'ROLE_ADMIN'){
      this.authService.redirectUrl = "/vendor-approval";
    }else{
      this.authService.redirectUrl = "/vendor-edit";
    }
    this.router.navigate([this.authService.redirectUrl]);
  }
}

  onSubmit(form: NgForm) {
    const username = form.value.uname;
    const password = form.value.pass;
    if (username == 'john123') {
      this.isLoginValid = false;
    } else {
      this.authService.authenticate(username, password).subscribe(data => {
          this.authService.accessToken = data['token'];
          this.authService.role = data['role'];
          if (this.authService.role === 'ROLE_USER') {
            this.authService.isAdmin = false;
            this.authService.userAuthenticated = {username:username,firstname:username,lastname:username,role:'user'};
            this.authService.redirectUrl = "/vendors";
            this.router.navigate([this.authService.redirectUrl]);
          } else if(this.authService.role === 'ROLE_ADMIN'){
            this.authService.isAdmin = true;
            this.authService.userAuthenticated = {username:'Admin',firstname:'Admin',lastname:'User',role:'admin'}
            this.authService.redirectUrl = "/vendor-approval";
            this.router.navigate([this.authService.redirectUrl]);
          }else{
            this.authService.isAdmin=false;
            this.authService.userAuthenticated = {username:username,firstname:username,lastname:username,role:'vendor'}
            this.authService.redirectUrl = "/vendor-edit";
            this.router.navigate([this.authService.redirectUrl]);
          }
          this.authService.loggedIn = true;
          console.log(this.authService.loggedIn);
          
      },
      (error)=>{
        if(error['status']===401){
          this.isLoginValid=false;
        }
      }
      );;
    }
  }

}
