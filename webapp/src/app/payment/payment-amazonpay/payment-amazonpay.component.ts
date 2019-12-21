import { Component, OnInit } from '@angular/core';
import { Payment } from 'src/app/payment.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PaymentService } from '../payment.service';
import { AuthenticateService } from 'src/app/authenticate.service';
import { VendorService } from 'src/app/vendor.service';

@Component({
  selector: 'app-payment-amazonpay',
  templateUrl: './payment-amazonpay.component.html',
  styleUrls: ['./payment-amazonpay.component.css']
})
export class PaymentAmazonpayComponent implements OnInit {
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
  paymentForm:FormGroup;
  paymentmode:string="AmazonPay";

  constructor(private paymentService:PaymentService,private authService:AuthenticateService,private vendorService:VendorService) { }

  ngOnInit() {
    this.paymentForm=new FormGroup({
      'apname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'apemail': new FormControl(null,[Validators.required,Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$")]),
      'appassword':new FormControl(null,Validators.required),
    });
    this.vendor=this.vendorService.vendorSelected;
    this.username=this.authService.userAuthenticated.username;
    this.paymentService.getPaymentDetailsByVendorAndUserAndPaymentGateway(this.vendor,this.username,this.paymentmode).subscribe((previousPayment:Payment) => {
      this.paymentDetails=previousPayment;
      
      if (this.paymentDetails) {        
        this.paymentForm.patchValue({
          apname: this.paymentDetails.nameOnCard,
          apemail: this.paymentDetails.email
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
  onPaid(){
    let username=this.username;
    let vendorSelected=this.vendor;
    let paymentGatgewayName="AmazonPay";
    let monthsPaid=this.monthsSelected;
    let amountPaid=this.amountToPay;
    let nameOnCard = this.paymentForm.get('apname').value;
    let email = this.paymentForm.get('apemail').value;
    this.paymentDetails = {
      username:username,vendorName:vendorSelected,billpaymentGateway:paymentGatgewayName,
      monthsPaid:monthsPaid, nameOnCard:nameOnCard, email:email,billAmount:amountPaid
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
