import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ResourceRoutingModule } from './resource-routing.module';
import {TopicComponent} from "../../pages/topic/topic.component";
import {UserComponent} from "../../pages/user/user.component";
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    TopicComponent,
    UserComponent
  ],
  imports: [
    CommonModule,
    ResourceRoutingModule,
    ReactiveFormsModule
  ]
})
export class ResourceModule { }
