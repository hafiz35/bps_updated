import { Component, OnInit } from '@angular/core';
import * as _ from "lodash";
import { Vendor } from 'src/app/vendor.model';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { AuthenticateService } from 'src/app/authenticate.service';
import { UserService } from 'src/app/user.service';
import { VendorService } from 'src/app/vendor.service';
import { CountriesService } from 'src/app/country.service';
import { Country } from 'src/app/countries.model';

@Component({
  selector: 'app-vendor-signup',
  templateUrl: './vendor-signup.component.html',
  styleUrls: ['./vendor-signup.component.css']
})
export class VendorSignupComponent implements OnInit {
  paymentgateway:string[];
  vtype:string[];
  signupForm:FormGroup;
  formSubmitted:boolean=false;
  vendor:Vendor;
  minDate:Date;
  maxDate:Date;
  pg:string[]=[];
  vendorAlreadyExists:boolean=false;
  countries:Country[];
  states:string[];
  constructor(private userService:UserService,private vendorService:VendorService,private authService:AuthenticateService, private countryService:CountriesService) { }

  ngOnInit() {
    this.getCountries();
    this.paymentgateway=['Paytm','Card','AmazonPay'];
    this.vtype=['DTH','Electricity','Telephone','Insurance','Water','Credit Card','Loan account'];
    this.signupForm=new FormGroup({
      'vname': new FormControl(null, [Validators.required,Validators.maxLength(25)]),
      'vcompanyregno': new FormControl(null,[Validators.required,Validators.maxLength(15)]),
      'vvendortype': new FormControl(null,Validators.required),
      'pswd':new FormControl(null,Validators.required),
      'cpswd':new FormControl(null,[Validators.required,this.confirmPassword.bind(this)]),
      'vcountry':new FormControl("",Validators.required),
      'vstate':new FormControl("",Validators.required),
      'vaddress':new FormControl(null,Validators.required),
      'vemail':new FormControl(null,[Validators.required,Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$")]),
      'vcontactnumber':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(10)]),
      'vwebsite':new FormControl(null,[Validators.required,Validators.pattern('^(http:\/\/|https:\/\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$')]),
      'vcertificateissuedate':new FormControl(null,[Validators.required,this.onIssueDateSelected.bind(this)]),
      'vcertificatevaliditydate':new FormControl(null,[Validators.required,this.validityDate.bind(this)]),
      'vyearofestablishment':new FormControl(null,[Validators.required,Validators.min(1900),Validators.max(2019)]),
      'vpaymentgateway':new FormControl(null,Validators.required)
    });
  }

  getCountries(){
    this.countryService.getCountries().subscribe((countries:Country[])=>{
      this.countries=countries;
      
      
     
      
      
    });
  }

  getStates(){
    let country =this.signupForm.value.vcountry.country;
    this.states=this.signupForm.value.vcountry.states;
   
  }


  vendorExist() {
   if(this.signupForm.get('vname').value.length>0){
    this.userService.doesUserExist(this.signupForm.get('vname').value).subscribe(data=>{
      this.vendorAlreadyExists=data;
    });
  }}

  confirmPassword(formControl:FormControl){
    if(this.signupForm){
      if(formControl.value && formControl.value.length>0 && formControl.value!==this.signupForm.get('pswd').value){
        return { 'nomatch' : true };
      }
    }
    return null;
  }

  resetForm(){
    this.signupForm.reset();
  }

  onIssueDateSelected(formControl:FormControl){
    this.minDate=formControl.value;
    return true;
  }

  getSelectedPaymentGateway(event:string) {
    if(this.signupForm.get('vpaymentgateway').value)
      this.pg.push(event);
    else{
      const fd = this.pg.findIndex(payg => payg == event);
      this.pg.splice(fd,1);
    }
  }

  validityDate(formControl:FormControl){
    this.maxDate=formControl.value;
    return true;
  }

  onSignUp(){
    this.formSubmitted=true;
    let vendorname=this.signupForm.get('vname').value;
    let companyregno=this.signupForm.get('vcompanyregno').value;
    let vendortype=this.signupForm.get('vvendortype').value;
    let password=this.signupForm.get('pswd').value;
    let address=this.signupForm.get('vaddress').value;
    let country=this.signupForm.get('vcountry').value;
    let state=this.signupForm.get('vstate').value;
    let emailaddress=this.signupForm.get('vemail').value;
    let contactnumber=this.signupForm.get('vcontactnumber').value;
    let website=this.signupForm.get('vwebsite').value;
    let certificateissuedate=this.signupForm.get('vcertificateissuedate').value;
    let certificatevaliditydate=this.signupForm.get('vcertificatevaliditydate').value;
    let yearofestablishment=this.signupForm.get('vyearofestablishment').value;
    let paymentgateway=this.pg;
    let flag=0;
    
    this.vendor = {vendorName:vendorname,vendorRegNo:companyregno,vendortype:vendortype,password:password,vendorAddress:address,vendorCountry:country,vendorState:state,
      vendorEmail:emailaddress,vendorContactNo:contactnumber,vendorWebSite:website,vendorCertificationIssueDate:certificateissuedate,vendorCertificationValidityDate:certificatevaliditydate,
      vendorYearOfEstablishment:yearofestablishment,paymentgateway:paymentgateway,flag:flag};
    this.vendorAlreadyExists=false;
    this.vendorService.authenticate(this.vendor).subscribe((data)=>{
      },
      (error)=>{
        if(error['error']['message']==='Vendor Already Exist'){
          this.vendorAlreadyExists=true;
        }        
      }
      );
      this.signupForm.reset();
  }

}
