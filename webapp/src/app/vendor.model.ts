import { Payment } from './payment.model';

export interface Vendor{
    vendorName?:string,
    vendorRegNo?:string,
    vendortype?:string,
    vendorImage?:string,
    password?:string,
    vendorAddress?:string,
    vendorCountry?:string,
    vendorState?:string,
    vendorEmail?:string,
    vendorContactNo?:string,
    vendorWebSite?:string,
    vendorCertificationIssueDate?:Date,
    vendorCertificationValidityDate?:Date,
    vendorYearOfEstablishment?:number,
    paymentgateway?:string[],
    isUsingPaytm?:boolean,
    isUsingCard?:boolean,
    isUsingAmazonPay?:boolean,
    flag?:number,
    paymentListEmpty?:boolean,
    paymentList?:Payment[]
}