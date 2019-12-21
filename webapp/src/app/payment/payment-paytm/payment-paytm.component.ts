import { Component, OnInit } from '@angular/core';
import { Payment } from 'src/app/payment.model';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { PaymentService } from '../payment.service';
import { AuthenticateService } from 'src/app/authenticate.service';
import { VendorService } from 'src/app/vendor.service';

@Component({
  selector: 'app-payment-paytm',
  templateUrl: './payment-paytm.component.html',
  styleUrls: ['./payment-paytm.component.css']
})
export class PaymentPaytmComponent implements OnInit {

  username:string='';
  vendor:string='';
  spinTheSpinner:boolean=false;
  displayStatus:boolean=false;
  originalAmount:number=360;
  monthsSelected:number=1;
  amountToPay:number=360;
  disableOnMinMonths:boolean=true;
  disableOnMaxMonths:boolean=false;
  formSubmitted:boolean=false;
  paymentDetails:Payment;
  resentOtp:boolean=false;
  paymentForm:FormGroup;
  proceedtopaymentForm:FormGroup;
  paymentmode:string="Paytm";
  proceedpay:boolean=false;

  constructor(private paymentService:PaymentService,private authService:AuthenticateService,private vendorService:VendorService) { }

  ngOnInit() {
    this.paymentForm=new FormGroup({
      'pname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'pnumber': new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(12)]),
      'ppassword':new FormControl(null,Validators.required)
    });
    this.proceedtopaymentForm=new FormGroup({
      'potp':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.minLength(4),Validators.maxLength(4)])
    })
    this.vendor=this.vendorService.vendorSelected;
    this.username=this.authService.userAuthenticated.username;
    this.paymentService.getPaymentDetailsByVendorAndUserAndPaymentGateway(this.vendor,this.username,this.paymentmode).subscribe((previousPayment:Payment) => {
      this.paymentDetails=previousPayment;
      
      if (this.paymentDetails) {        
        this.paymentForm.patchValue({
          pname: this.paymentDetails.nameOnCard,
          pnumber: this.paymentDetails.mobileNumber
        });
      }
    });
  }

  increaseMonths(){
    this.disableOnMinMonths=false;
    if(this.monthsSelected===9)
      this.disableOnMaxMonths=true;
    this.monthsSelected++;
    this.amountToPay=this.monthsSelected*this.originalAmount;
  }
  decreaseMonths(){
    this.disableOnMaxMonths=false;
    if(this.monthsSelected===2)
      this.disableOnMinMonths=true;
    this.monthsSelected--;
    this.amountToPay=this.monthsSelected*this.originalAmount;
  }
  proceedToPay(){
    this.spinTheSpinner=true;
    setTimeout(()=>{
      this.spinTheSpinner=false;
      this.proceedpay=true;
    },1000);
  }
  resendOtp(){
    this.resentOtp=true;
  }
  onPaid(){
    let username=this.username;
    let vendorSelected=this.vendor;
    let paymentGatgewayName="Paytm";
    let monthsPaid=this.monthsSelected;
    let amountPaid=this.amountToPay;
    let nameOnCard = this.paymentForm.get('pname').value;
    let number = this.paymentForm.get('pnumber').value;
    this.paymentDetails = {
      username:username,vendorName:vendorSelected,billpaymentGateway:paymentGatgewayName,
      monthsPaid:monthsPaid, nameOnCard:nameOnCard, mobileNumber:number,billAmount:amountPaid
    };

    this.paymentService.doPayment(this.paymentDetails).subscribe();
    this.paymentForm.reset();
    this.formSubmitted=true;

    this.spinTheSpinner=true;
    setTimeout(()=>{
      this.spinTheSpinner=false;
      this.displayStatus=true;
    },1000);
  }

}
