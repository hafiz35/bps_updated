import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Payment } from 'src/app/payment.model';
import { PaymentService } from '../payment.service';
import { AuthenticateService } from 'src/app/authenticate.service';
import { VendorService } from 'src/app/vendor.service';

@Component({
  selector: 'app-payment-card',
  templateUrl: './payment-card.component.html',
  styleUrls: ['./payment-card.component.css']
})
export class PaymentCardComponent implements OnInit {
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
  paymentmode:string="Card";
  monthList:string[]=["January","February","March","April","May","June","July","August","September","October","November","December"];
  yearList:number[]=[2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030];

  constructor(private paymentService:PaymentService,private authService:AuthenticateService,private vendorService:VendorService) { }

  ngOnInit() {
    this.paymentForm=new FormGroup({
      'cname': new FormControl(null, [Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'cnumber': new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.minLength(10),Validators.maxLength(10)]),
      'cmonthofexpiry': new FormControl(null,Validators.required),
      'cyearofexpiry': new FormControl(null,Validators.required),
      'csecuritycode':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.minLength(3),Validators.maxLength(3)]),
      'cpostalcode':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(6)]),
    });
    this.vendor=this.vendorService.vendorSelected;
    this.username=this.authService.userAuthenticated.username;
    this.paymentService.getPaymentDetailsByVendorAndUserAndPaymentGateway(this.vendor,this.username,this.paymentmode).subscribe((previousPayment:Payment) => {
      this.paymentDetails=previousPayment;
      
      if (this.paymentDetails) {        
        this.paymentForm.patchValue({
          cname: this.paymentDetails.nameOnCard,
          cnumber: this.paymentDetails.cardNumber,
          cmonthofexpiry: this.paymentDetails.expirationMonth,
          cyearofexpiry: this.paymentDetails.expirationYear,
          cpostalcode: this.paymentDetails.postalCode
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
    let paymentGatgewayName="Card";
    let monthsPaid=this.monthsSelected;
    let amountPaid=this.amountToPay;
    let nameOnCard = this.paymentForm.get('cname').value;
    let cardNumber = this.paymentForm.get('cnumber').value;
    let expirationMonth = this.paymentForm.get('cmonthofexpiry').value;
    let expirationYear = this.paymentForm.get('cyearofexpiry').value;
    let postalCode = this.paymentForm.get('cpostalcode').value;
    this.paymentDetails = {
      username:username,vendorName:vendorSelected,billpaymentGateway:paymentGatgewayName,
      monthsPaid:monthsPaid, nameOnCard:nameOnCard, cardNumber:cardNumber, expirationMonth:expirationMonth, expirationYear:expirationYear,
      postalCode:postalCode,billAmount:amountPaid
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
