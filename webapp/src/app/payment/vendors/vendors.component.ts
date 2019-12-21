import { Component, OnInit } from '@angular/core';
import { Vendor } from 'src/app/vendor.model';
import { Router } from '@angular/router';
import { VendorTypeService } from 'src/app/vendor-type.service';
import { VendorService } from 'src/app/vendor.service';
import { AuthenticateService } from 'src/app/authenticate.service';
import { PaymentService } from '../payment.service';
import { Payment } from 'src/app/payment.model';

@Component({
  selector: 'app-vendors',
  templateUrl: './vendors.component.html',
  styleUrls: ['./vendors.component.css']
})
export class VendorsComponent implements OnInit {
  vendorTypeList: Vendor[];

  vendorTypeName: string = '';
  username: string = '';
  vendorList: Vendor[];
  tempVendorList: Vendor[];
  paymentList: Payment[];

  constructor(private vendorTypeService: VendorTypeService, private router: Router, private vendorService: VendorService, private authService: AuthenticateService, private paymentService: PaymentService) { }

  ngOnInit() {
    this.vendorTypeService.getAllVendorTypes().subscribe((vend: Vendor[]) => {
      this.vendorTypeList = [...vend];
    })
    this.vendorTypeName = this.vendorTypeService.vendortypeSelected;
    this.username = this.authService.userAuthenticated.username;
    this.vendorService.getVendorByVendorTypeAndUser(this.vendorTypeName, this.username).subscribe((vendList: Vendor[]) => {
      this.vendorList = vendList;
      this.tempVendorList=vendList;
    })
  }

  getVendors(vendortype: string) {
    this.vendorTypeService.vendortypeSelected = vendortype;
    this.vendorTypeName = this.vendorTypeService.vendortypeSelected;
    this.username = this.authService.userAuthenticated.username;
    this.vendorService.getVendorByVendorTypeAndUser(this.vendorTypeName, this.username).subscribe((vendList: Vendor[]) => {
      this.vendorList = vendList;
      this.tempVendorList=vendList;
    })
  }

  fetchBillDetails(vendorSelected: string) {
    this.vendorService.vendorSelected = vendorSelected;
    this.paymentService.getPaymentDetailsByVendorAndUser(vendorSelected, this.username).subscribe((paymentListRecieved: Payment[]) => {
      this.paymentList = paymentListRecieved;
      this.vendorList.forEach(element => {
        if (element.vendorName == this.vendorService.vendorSelected) {
          element.paymentList = this.paymentList;
          if (element.paymentList.length === 0) {
            element.paymentListEmpty = true;
          }
          else {
            element.paymentList.forEach(singlePayment => {
              singlePayment.dateOfPay = new Date(singlePayment.dateOfPay);
              singlePayment.dateOfPayString = singlePayment.dateOfPay.toISOString().slice(0, 10);
            });
          }
          element.paymentgateway.forEach(paymentGatewayName => {
            if (paymentGatewayName === "Paytm") {
              element.isUsingPaytm = true;
            }
            if (paymentGatewayName === "Card") {
              element.isUsingCard = true;
            }
            if (paymentGatewayName === "AmazonPay") {
              element.isUsingAmazonPay = true;
            }
          });
        }
      });
    });
  }
  onSearchText(event : any){
    if(event.target.value!==''){
      const result = this.tempVendorList.filter(filtervendor => filtervendor.vendorName.toLowerCase().includes(event.target.value.toLowerCase()));
      this.vendorList=result?result:[];
    }
    else{
      this.vendorList=[...this.tempVendorList];
    }
  }
  onPaytmSelected(vendorSelected: string) {
    this.vendorService.vendorSelected = vendorSelected;
    this.router.navigate(['/payment-paytm']);
  }
  onCardSelected(vendorSelected: string) {
    this.vendorService.vendorSelected = vendorSelected;
    this.router.navigate(['/payment-card']);
  }
  onAmazonPaySelected(vendorSelected: string) {
    this.vendorService.vendorSelected = vendorSelected;
    this.router.navigate(['/payment-amazonpay']);
  }

}