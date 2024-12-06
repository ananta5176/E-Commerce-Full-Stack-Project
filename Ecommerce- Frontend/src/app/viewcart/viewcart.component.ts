import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-viewcart',
  templateUrl: './viewcart.component.html',
  styleUrls: ['./viewcart.component.css']
})
export class ViewcartComponent implements OnInit {
  total:number=0;
  list:any[]
  count:number=0
  custid=sessionStorage.getItem('id')
  fg: UntypedFormGroup;
  constructor(private api:ApiService,private toast:ToastrService,private fb:UntypedFormBuilder,
    private route:Router) { }

  ngOnInit(): void {
    this.loadData()
    this.createForm()
  }

  createForm(){
    this.fg=this.fb.group({
      'state':['',Validators.required],
      'city':['',Validators.required],
      'country':['',Validators.required],
      'zip':['',Validators.required],
      'cardno':['',Validators.required],
      'nameoncard':['',Validators.required],
    })
  }

  removefromcart(id:any){
    const custid=sessionStorage.getItem('id')
    this.api.deletefromcart(id).subscribe({
      next:resp=>{
        this.toast.success('Item deleted from cart')
        this.loadData()
        this.api.updatecartCount(custid)
      }
    })
  }

  placeorder(values:any){
    const custid=sessionStorage.getItem('id')
    console.log(values)
    if(this.fg.valid){
      this.api.placeorder({
        customerid:custid, 
        payment:{
          cardno:values.cardno,
          nameoncard:values.nameoncard,
          amount:this.total
        },
        address:{
          state:values.state,
          city:values.city,
          country:values.country,
          zip:values.zip
        }
      }).subscribe({
        next:resp=>{  
          this.api.updatecartCount(custid)      
          this.toast.success('Order placed successfully')
          this.route.navigate(['/history'])
        },
        error:err=>console.log(err)
      })
    }
  }

  updateqty(id:number,qty:number){
    const custid=sessionStorage.getItem('id')
    if(qty==0)
      this.toast.error('Cannot reduce quantity')
    else{
      this.api.updateqty(id,qty).subscribe({
        next:resp=>{
          this.toast.success('Quantity updated')
          this.loadData()
        },
        error:err=>console.log("error")
      });
    }
  }

  loadData(){
    this.api.getcart(sessionStorage.getItem('id')).subscribe({
      next:resp=>{
        this.list=resp
        this.count=resp.length || 0
        this.total=resp.reduce((sum:number,x:any)=>sum+x.qty*x.product.price,0)
      }
    })
  }

}
