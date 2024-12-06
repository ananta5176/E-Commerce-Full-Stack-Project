import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  BASEURL:string="http://localhost:8080/api/";
  cartDataList:any=[]
  cartItemCount=new BehaviorSubject(0)
  userRole=new BehaviorSubject('G')
  constructor(private http:HttpClient) { }

  getRole(){
    return sessionStorage.getItem('role') || 'G'
  }

  validate(data:any){
    return this.http.post<any>(this.BASEURL+"customers/validate",data);
  }

  validateadmin(data:any){
    return this.http.post<any>(this.BASEURL+"admin/validate",data);
  }

  vendorvalidate(data:any){
    return this.http.post<any>(this.BASEURL+"vendors/validate",data);
  }

  dashboard(){
    return this.http.get<any>(this.BASEURL+"admin/dashboard");
  }

  //categories
  savecategory(data:any){
    return this.http.post<any>(this.BASEURL+"categories",data);
  }

  listcategories(){
    return this.http.get<any[]>(this.BASEURL+"categories");
  }

  deletecategory(id:number){
    return this.http.delete<any>(this.BASEURL+"categories/"+id)
  }

  //customer
  register(data:any){
    return this.http.post<any>(this.BASEURL+"customers",data);
  }

  listcustomers(){
    return this.http.get<any[]>(this.BASEURL+"customers")
  }
  
  getcustomerdetails(id:any){
    return this.http.get<any>(this.BASEURL+"customers/"+id)
  }

  //products
  saveproduct(data:any){
    //new HttpHeaders().set("Accept", "application/json");
    return this.http.post<any>(this.BASEURL+"products",data)
  }

  updateproduct(id:string,data:any){
    return this.http.put<any>(this.BASEURL+"products/"+id,data)
  }

  listproducts(){
    return this.http.get<any[]>(this.BASEURL+"products")
  }

  vendorproducts(vid:string){
    return this.http.get<any[]>(this.BASEURL+"products/vendors?vid="+vid)
  }

  catproducts(catid:number){
    return this.http.get<any[]>(this.BASEURL+"products/cats?catid="+catid)
  }

  searchproducts(search:string){
    return this.http.get<any[]>(this.BASEURL+"products?search="+search)
  }

  deleteproduct(id:number){
    return this.http.delete<any>(this.BASEURL+"products/"+id)
  }

  updatecartCount(id:any){
    this.getcart(id).subscribe({
      next:resp=>
      {
        const count=resp.items.length || 0
        this.cartItemCount.next(count)
      },
      error:err=>console.log(err.error)
    })
  }

  getCartCount(){
    return this.cartItemCount
  }

  //cart
  addtocart(data:any){
    return this.http.post<any>(this.BASEURL+"cart",data)
  }

  getcart(id:any){
    return this.http.get<any>(this.BASEURL+"cart?custid="+id)
  }

  deletefromcart(id:any){
    return this.http.delete<any>(this.BASEURL+"cart/"+id)
  }

  updateqty(id:number,qty:number){
    return this.http.put<any>(this.BASEURL+"cart/"+id+"/"+qty,null);
  }

  //orders
  placeorder(data:any){
    return this.http.post<any>(this.BASEURL+"orders",data);
  }

  orderhistory(custid:any){
    return this.http.get<any[]>(this.BASEURL+"orders?custid="+custid);
  }

  allorders(){
    return this.http.get<any[]>(this.BASEURL+"orders");
  }
}
