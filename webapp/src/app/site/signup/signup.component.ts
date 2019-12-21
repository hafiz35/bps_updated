import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/user.model';
import { UserService } from 'src/app/user.service';
import { AuthenticateService } from 'src/app/authenticate.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm:FormGroup;
  formSubmitted:boolean=false;
  admin:boolean=false;
  user:User;
  userAlreadyExists:boolean=false;
  constructor(private userService:UserService,private authService:AuthenticateService) { }

  ngOnInit() {
    
    this.signupForm=new FormGroup({
      'uname': new FormControl(null, [Validators.required,Validators.maxLength(25)]),
      'fname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'lname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'pswd':new FormControl(null,Validators.required),
      'cpswd':new FormControl(null,[Validators.required,this.confirmPassword.bind(this)]),
      'uage':new FormControl(null,[Validators.required,Validators.min(18),Validators.max(75)]),
      'ugender':new FormControl("Male",Validators.required),
      'ucontactnumber':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(12)]),
      'uaadharnumber':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.minLength(16),Validators.maxLength(16)]),
      'upan':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.minLength(12),Validators.maxLength(12)]),
      'isAdminChecked':new FormControl(null)
    });
  }

  userExist() {
    if(this.signupForm.get('uname').value.length>0){
    
    
    this.userService.doesUserExist(this.signupForm.get('uname').value).subscribe(data=>{
      this.userAlreadyExists=data;
    });
  }}

  getIsAdminUser(){
    this.admin=this.signupForm.get('isAdminChecked').value;
  }

  confirmPassword(formControl:FormControl){
    if(this.signupForm){
      if(formControl.value && formControl.value.length>0 && formControl.value!==this.signupForm.get('pswd').value){
        return { 'nomatch' : true };
      }
    }
    return null;
  }

  onSignUp(){
    this.formSubmitted=true;
    let username=this.signupForm.get('uname').value;
    let firstname=this.signupForm.get('fname').value;
    let lastname=this.signupForm.get('lname').value;
    let password=this.signupForm.get('pswd').value;
    let age=this.signupForm.get('uage').value;
    let gender=this.signupForm.get('ugender').value;
    let contactnumber=this.signupForm.get('ucontactnumber').value;
    let aadharnumber=this.signupForm.get('uaadharnumber').value;
    let pan=this.signupForm.get('upan').value;
    let admin=this.admin;
    this.user = {username:username,firstname:firstname,lastname:lastname,password:password,age:age,gender:gender,
                        contactNumber:contactnumber,aadharNumber:aadharnumber,pan:pan,admin:admin};
    this.userAlreadyExists=false;
    this.userService.authenticate(this.user).subscribe((data)=>{
        console.log(data);
      },
      (error)=>{
        if(error['error']['message']==='User Already Exist'){
          this.userAlreadyExists=true;
        }        
      }
      );
      this.signupForm.reset();
  }
}
