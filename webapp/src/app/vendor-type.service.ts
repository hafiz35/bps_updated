import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticateService } from './authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class VendorTypeService {

  token:string='';
  vendortypeSelected:string='DTH';
  constructor(private httpClient:HttpClient,private authService:AuthenticateService) { }

  getAllVendorTypes(){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8083/vendor-types`,{headers})
  }

}
