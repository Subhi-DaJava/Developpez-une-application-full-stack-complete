import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ResourceRoutingModule } from './resource-routing.module';
import {TopicComponent} from "../../pages/topic/topic.component";
import {UserComponent} from "../../pages/user/user.component";
import {ReactiveFormsModule} from "@angular/forms";
import {TopicListComponent} from "../../pages/topic-list/topic-list.component";
import {PostComponent} from "../../pages/post/post.component";
import { PostListComponent } from '../../pages/post-list/post-list.component';



@NgModule({
  declarations: [
    TopicComponent,
    UserComponent,
    TopicListComponent,
    PostComponent,
    PostListComponent
  ],
  imports: [
    CommonModule,
    ResourceRoutingModule,
    ReactiveFormsModule
  ]
})
export class ResourceModule { }
