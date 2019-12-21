import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticateService } from '../authenticate.service';
import { Payment } from '../payment.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  token:string='';
  constructor(private httpClient:HttpClient,private authService:AuthenticateService) { }

  getPaymentDetailsByVendorAndUserAndPaymentGateway(vendorName:string,username:string,billPaymentGateway:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/payment/${vendorName}/${username}/${billPaymentGateway}`,{headers})
  }

  getPaymentDetailsByVendorAndUser(vendorName:string,username:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/payment/${vendorName}/${username}`,{headers})
  }

  doPayment(payment:Payment){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.post<any>(`http://localhost:8083/payment`,payment,{headers});
  }

}
