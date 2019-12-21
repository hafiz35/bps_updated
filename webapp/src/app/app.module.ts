import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './site/header/header.component';
import { FooterComponent } from './site/footer/footer.component';
import { SignupComponent } from './site/signup/signup.component';
import { LoginComponent } from './site/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { VendorSignupComponent } from './site/vendor-signup/vendor-signup.component';
import { FaqComponent } from './site/faq/faq.component';
import { VendorApprovalComponent } from './site/vendor-approval/vendor-approval.component';
import { VendorEditComponent } from './vendor/vendor-edit/vendor-edit.component';
import { PaymentCardComponent } from './payment/payment-card/payment-card.component';
import { PaymentPaytmComponent } from './payment/payment-paytm/payment-paytm.component';
import { PaymentAmazonpayComponent } from './payment/payment-amazonpay/payment-amazonpay.component';
import { ReportissueComponent } from './site/reportissue/reportissue.component';
import { VendorsComponent } from './payment/vendors/vendors.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SignupComponent,
    LoginComponent,
    VendorSignupComponent,
    FaqComponent,
    VendorApprovalComponent,
    VendorEditComponent,
    PaymentCardComponent,
    PaymentPaytmComponent,
    PaymentAmazonpayComponent,
    ReportissueComponent,
    VendorsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
