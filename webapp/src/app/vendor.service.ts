import { Injectable } from '@angular/core';
import { Vendor } from './vendor.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticateService } from './authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class VendorService {
  token:string;
  vendorSelected:string='DTH';
  constructor(private httpClient: HttpClient,private authService:AuthenticateService) { }

  authenticate(vendor:Vendor):Observable<any> {
    return this.httpClient.post<any>("http://localhost:8083/users/vendor",vendor);
  }

  getVendorByVendorTypeAndUser(vendorType:string,username:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/vendor/vendor-type/${vendorType}/${username}`,{headers})
  }

  getVendorByVendorName(vendorName:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/vendor/${vendorName}`,{headers})
  }

  getVendorByFlag(flag:number){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/vendor/flag/${flag}`,{headers})
  }

  acceptVendor(vendorId:number){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.put<void>(`http://localhost:8083/vendor/accept/${vendorId}`,{headers})    
  }

  declineVendor(vendorId:number){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.delete<void>(`http://localhost:8083/vendor/decline/${vendorId}`,{headers})    
  }

  sentBackForCorrection(vendorId:number){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.put<void>(`http://localhost:8083/vendor/sentbackforcorrection/${vendorId}`,{headers})    
  }

  updateVendor(vendor:Vendor){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.put<void>("http://localhost:8083/vendor/editvendor", vendor, { headers });  
  }

}
