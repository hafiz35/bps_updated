import { Component, OnInit } from '@angular/core';
import { Vendor } from 'src/app/vendor.model';
import { VendorService } from 'src/app/vendor.service';
import { AuthenticateService } from 'src/app/authenticate.service';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { findLast } from '@angular/compiler/src/directive_resolver';

@Component({
  selector: 'app-vendor-edit',
  templateUrl: './vendor-edit.component.html',
  styleUrls: ['./vendor-edit.component.css']
})
export class VendorEditComponent implements OnInit {

  sentBackForApproval:boolean=false;
  adminAccepted:boolean=false;
  pendingRequest:boolean=false;
  formSubmitted:boolean=false;
  editOpen: boolean = false;
  vendor: Vendor;
  vendorName: string;
  paymentgateway: string[];
  editVendorForm: FormGroup;
  paymentGatewayChecked: string[];
  minDate:Date;
  maxDate:Date;
  constructor(private vendorService: VendorService, private authService: AuthenticateService) {

  }

  ngOnInit() {
    this.paymentgateway = ['Paytm', 'Card','AmazonPay'];
    this.editVendorForm = new FormGroup({
      'vname': new FormControl(null, [Validators.required, Validators.maxLength(25)]),
      'vcompanyregno': new FormControl(null, [Validators.required, Validators.maxLength(45)]),
      'vcountry': new FormControl(null, Validators.required),
      'vstate': new FormControl("Male", Validators.required),
      'vaddress': new FormControl(null, Validators.required),
      'vemail': new FormControl(null, [Validators.required, Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$")]),
      'vcontactnumber': new FormControl(null, [Validators.required, Validators.pattern('^[0-9]+$'), Validators.maxLength(10)]),
      'vwebsite': new FormControl(null, [Validators.required, Validators.pattern('^(http:\/\/|https:\/\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$')]),
      'vcertificateissuedate':new FormControl(null,[Validators.required,this.onIssueDateSelected.bind(this)]),
      'vcertificatevaliditydate':new FormControl(null,[Validators.required,this.validityDate.bind(this)]),
      'vyearofestablishment': new FormControl(null,[Validators.required,Validators.min(1900),Validators.max(2019)]),
      'pGateway': new FormArray([])
    });


    this.vendorName = this.authService.userAuthenticated.username;
    this.vendorService.getVendorByVendorName(this.vendorName).subscribe((vend: Vendor) => {
      vend.vendorCertificationIssueDate = new Date(vend.vendorCertificationIssueDate);
      vend.vendorCertificationValidityDate = new Date(vend.vendorCertificationValidityDate);
      this.vendor = vend;
      if (this.vendor) {
        
        if(this.vendor.flag===2)            this.sentBackForApproval=true;
        else if(this.vendor.flag===1)       this.adminAccepted=true;
        else                                this.pendingRequest=true;
        this.paymentGatewayChecked = this.vendor.paymentgateway;
        this.editVendorForm.patchValue({
          vname: this.vendorName,
          vcompanyregno: this.vendor.vendorRegNo,
          vcountry: this.vendor.vendorCountry,
          vstate: this.vendor.vendorState,
          vaddress: this.vendor.vendorAddress,
          vemail: this.vendor.vendorEmail,
          vcontactnumber: this.vendor.vendorContactNo,
          vwebsite: this.vendor.vendorWebSite,
          vcertificateissuedate: this.vendor.vendorCertificationIssueDate.toISOString().slice(0, 10),
          vcertificatevaliditydate: this.vendor.vendorCertificationValidityDate.toISOString().slice(0, 10),
          vyearofestablishment: this.vendor.vendorYearOfEstablishment
        });
        this.addCheckBoxes();

      }
    });
    
  }

  addCheckBoxes() {
    this.paymentgateway.forEach((o, i) => {

      const control = new FormControl();
      if (this.paymentGatewayChecked.includes(o)) {
        control.setValue(true);
      }
      (this.editVendorForm.controls.pGateway as FormArray).push(control);
    });
  }

  onIssueDateSelected(formControl:FormControl){
    this.minDate=formControl.value;
    return true;
  }

  validityDate(formControl:FormControl){
    this.maxDate=formControl.value;
    return true;
  }

  get pGateway() {
    return this.editVendorForm.get('pGateway') as FormArray
  }

  onEdit() {
    let vendorname = this.editVendorForm.get('vname').value;
    let companyregno = this.editVendorForm.get('vcompanyregno').value;
    let address = this.editVendorForm.get('vaddress').value;
    let country = this.editVendorForm.get('vcountry').value;
    let state = this.editVendorForm.get('vstate').value;
    let emailaddress = this.editVendorForm.get('vemail').value;
    let contactnumber = this.editVendorForm.get('vcontactnumber').value;
    let website = this.editVendorForm.get('vwebsite').value;
    let certificateissuedate = this.editVendorForm.get('vcertificateissuedate').value;
    let certificatevaliditydate = this.editVendorForm.get('vcertificatevaliditydate').value;
    let yearofestablishment = this.editVendorForm.get('vyearofestablishment').value;
    let paymentgateway = this.paymentGatewayChecked;
    let flag = 0;
    this.pendingRequest=true;
    this.sentBackForApproval=false;
    this.adminAccepted=false;
    this.vendor = {
      vendorName: vendorname, vendorRegNo: companyregno, vendorAddress: address, vendorCountry: country, vendorState: state,
      vendorEmail: emailaddress, vendorContactNo: contactnumber, vendorWebSite: website, vendorCertificationIssueDate: certificateissuedate, vendorCertificationValidityDate: certificatevaliditydate,
      vendorYearOfEstablishment: yearofestablishment, paymentgateway: paymentgateway, flag: flag
    };

    this.vendorService.updateVendor(this.vendor).subscribe();
    this.editVendorForm.reset();
    this.formSubmitted=true;
  }

  getSelectedPaymentGateway(event: string) {

    const foundIndex = this.paymentGatewayChecked.findIndex(paygateway => paygateway == event);
    if (foundIndex !== -1) {
      this.paymentGatewayChecked.splice(foundIndex, 1);
    }
    else {
      this.paymentGatewayChecked.push(event);
    }

  }

}
