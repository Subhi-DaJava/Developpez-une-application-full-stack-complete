import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthRoutingModule} from "./auth-routing.module";
import {LoginComponent} from "../auth-components/login/login.component";
import {RegisterComponent} from "../auth-components/register/register.component";
import {ResourceModule} from "../resource-routing-modules/resource.module";

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent
  ],
    imports: [
        AuthRoutingModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ResourceModule
    ]
})
export class AuthModule { }
