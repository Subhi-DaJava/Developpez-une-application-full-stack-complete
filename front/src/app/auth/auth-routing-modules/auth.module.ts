import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthRoutingModule} from "./auth-routing.module";
import {LoginComponent} from "../auth-components/login/login.component";
import {RegisterComponent} from "../auth-components/register/register.component";
import {ResourceModule} from "../resource-routing-modules/resource.module";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";

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
        ResourceModule,
        MatInputModule,
        MatButtonModule
    ]
})
export class AuthModule { }
