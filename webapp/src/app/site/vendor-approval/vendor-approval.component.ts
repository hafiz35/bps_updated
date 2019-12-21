import { Component, OnInit } from '@angular/core';
import { Vendor } from 'src/app/vendor.model';
import { VendorService } from 'src/app/vendor.service';
import { Issue } from 'src/app/issue.model';
import { IssueService } from '../issue.service';
import { Payment } from 'src/app/payment.model';

@Component({
  selector: 'app-vendor-approval',
  templateUrl: './vendor-approval.component.html',
  styleUrls: ['./vendor-approval.component.css']
})
export class VendorApprovalComponent implements OnInit {

  vendorList: Vendor[];
  vendorsAvailable: Vendor[];
  vendorListsentforcorrection: Vendor[];
  vendorListEmpty: boolean = false;
  vendorsAvailableEmpty: boolean = false;
  vendorSelectedToDelete: number;
  vendorListsentforcorrectionEmpty: boolean = false;
  deletedVendor: boolean = false;
  acceptedVendor: boolean = false;
  sentBackForCorrection: boolean = false;
  isVendorSelected: boolean = true;
  isIssueSelected: boolean = false;
  answer: string = '';
  issueList: Issue[];
  issueListEmpty: boolean = false;
  vendorToDisplay: Vendor;
  issueDate:string;
  validityDate:string;
  constructor(private vendorService: VendorService, private issueService: IssueService) { }

  ngOnInit() {
    this.vendorService.getVendorByFlag(0).subscribe((vend: Vendor[]) => {
      this.vendorList = vend;
      if (this.vendorList.length === 0) {
        this.vendorListEmpty = true;
      }
      else {
        this.vendorListEmpty = false;
      }
    })
    this.vendorService.getVendorByFlag(2).subscribe((vend: Vendor[]) => {
      this.vendorListsentforcorrection = vend;
      if (this.vendorListsentforcorrection.length === 0) {
        this.vendorListsentforcorrectionEmpty = true;
      }
      else {
        this.vendorListsentforcorrectionEmpty = false;
      }
    })
    this.vendorService.getVendorByFlag(1).subscribe((vend: Vendor[]) => {
      this.vendorsAvailable = vend;
      if (this.vendorsAvailable.length === 0) {
        this.vendorsAvailableEmpty = true;
      }
      else {
        this.vendorsAvailableEmpty = false;
      }
    })
  }

  onApproving(vendorId: number) {
    this.acceptedVendor = true;
    this.deletedVendor = false;
    this.sentBackForCorrection = false;
    setTimeout(() => {
      this.acceptedVendor = false;
    }, 1000);
    this.vendorService.acceptVendor(vendorId).subscribe(() => this.refreshPage());
  }

  selectVendorToDelete(vendorId: number) {
    this.vendorSelectedToDelete = vendorId;
  }

  onDeclining(vendorId: number) {
    this.acceptedVendor = false;
    this.deletedVendor = true;
    this.sentBackForCorrection = false;
    setTimeout(() => {
      this.deletedVendor = false;
    }, 1000);
    this.vendorService.declineVendor(vendorId).subscribe(() => this.refreshPage());
  }

  onSendBackForCorrection(vendorId: number) {
    this.acceptedVendor = false;
    this.deletedVendor = false;
    this.sentBackForCorrection = true;
    setTimeout(() => {
      this.sentBackForCorrection = false;
    }, 1000);
    this.vendorService.sentBackForCorrection(vendorId).subscribe(() => this.refreshPage());
  }

  refreshPage() {
    this.vendorService.getVendorByFlag(0).subscribe((vend: Vendor[]) => {
      this.vendorList = vend;
      if (this.vendorList.length === 0) {
        this.vendorListEmpty = true;
      }
      else {
        this.vendorListEmpty = false;
      }
    })
    this.vendorService.getVendorByFlag(2).subscribe((vend: Vendor[]) => {
      this.vendorListsentforcorrection = vend;
      if (this.vendorListsentforcorrection.length === 0) {
        this.vendorListsentforcorrectionEmpty = true;
      }
      else {
        this.vendorListsentforcorrectionEmpty = false;
      }
    })
    this.vendorService.getVendorByFlag(1).subscribe((vend: Vendor[]) => {
      this.vendorsAvailable = vend;
      if (this.vendorsAvailable.length === 0) {
        this.vendorsAvailableEmpty = true;
      }
      else {
        this.vendorsAvailableEmpty = false;
      }
    })
  }

  seeDetails(vendor: Vendor) {
    this.vendorToDisplay = vendor;
    this.vendorToDisplay.vendorCertificationIssueDate = new Date(this.vendorToDisplay.vendorCertificationIssueDate);
    this.vendorToDisplay.vendorCertificationValidityDate = new Date(this.vendorToDisplay.vendorCertificationValidityDate);
    this.issueDate = this.vendorToDisplay.vendorCertificationIssueDate.toISOString().slice(0, 10);
    this.validityDate = this.vendorToDisplay.vendorCertificationValidityDate.toISOString().slice(0, 10);
  }

  vendorSelected() {
    this.isVendorSelected = true;
    this.isIssueSelected = false;
  }

  issuesSelected() {
    this.isVendorSelected = false;
    this.isIssueSelected = true;
    this.issueService.getAllIssues().subscribe((issues: Issue[]) => {
      this.issueList = issues;
      if (this.issueList.length === 0) {
        this.issueListEmpty = true;
      }
      else {
        this.issueListEmpty = false;
      }
    })
  }

  Reply(issueId: number, answer: any) {
    this.issueService.getIssueByIssueId(issueId).subscribe((issue: Issue) => {
      issue.answer = answer.value;
      issue.isAnswered = true;
      this.issueService.updateIssueDetails(issue).subscribe(() => this.issuesSelected());
    })
  }

}
