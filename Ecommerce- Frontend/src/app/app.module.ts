import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';
import { NavbarComponent } from './navbar/navbar.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { CategoryComponent } from './category/category.component';
import { ProductsComponent } from './products/products.component';
import { UsersComponent } from './users/users.component';
import { OrdersComponent } from './orders/orders.component';
import { ViewcartComponent } from './viewcart/viewcart.component';

import { ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { ApiService } from './api.service';
import { ProfileComponent } from './profile/profile.component';
import { OrderhistoryComponent } from './orderhistory/orderhistory.component';

@NgModule({ declarations: [
        AppComponent,
        NavbarComponent,
        HeaderComponent,
        LoginComponent,
        RegisterComponent,
        DashboardComponent,
        HomeComponent,
        CategoryComponent,
        ProductsComponent,
        UsersComponent,
        OrdersComponent,
        ViewcartComponent,
        ProfileComponent,
        OrderhistoryComponent,
    ],
    bootstrap: [AppComponent], 
    imports: [CommonModule,
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        ToastrModule.forRoot()], 
    providers: [ApiService, provideHttpClient(withInterceptorsFromDi())] })
export class AppModule { }
