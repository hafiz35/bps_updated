import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './site/signup/signup.component';
import { LoginComponent } from './site/login/login.component';
import { VendorSignupComponent } from './site/vendor-signup/vendor-signup.component';
import { FaqComponent } from './site/faq/faq.component';
import { VendorApprovalComponent } from './site/vendor-approval/vendor-approval.component';
import { VendorEditComponent } from './vendor/vendor-edit/vendor-edit.component';
import { AuthGuardService } from './auth-guard.service';
import { PaymentCardComponent } from './payment/payment-card/payment-card.component';
import { PaymentPaytmComponent } from './payment/payment-paytm/payment-paytm.component';
import { PaymentAmazonpayComponent } from './payment/payment-amazonpay/payment-amazonpay.component';
import { ReportissueComponent } from './site/reportissue/reportissue.component';
import { VendorsComponent } from './payment/vendors/vendors.component';


const routes: Routes = [
  {path:'',redirectTo:'login',pathMatch:'full'},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'vendorsignup',component:VendorSignupComponent},
  {path:'faq',component:FaqComponent},
  {path:'vendor-approval',component:VendorApprovalComponent,canActivate:[AuthGuardService]},
  {path:'vendor-edit',component:VendorEditComponent,canActivate:[AuthGuardService]},
  {path:'payment-card',component:PaymentCardComponent,canActivate:[AuthGuardService]},
  {path:'payment-paytm',component:PaymentPaytmComponent,canActivate:[AuthGuardService]},
  {path:'payment-amazonpay',component:PaymentAmazonpayComponent,canActivate:[AuthGuardService]},
  {path:'report-issue',component:ReportissueComponent,canActivate:[AuthGuardService]},
  {path:'vendors',component:VendorsComponent,canActivate:[AuthGuardService]},
  {path:'**',redirectTo:'login',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
