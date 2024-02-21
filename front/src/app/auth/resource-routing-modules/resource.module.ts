import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ResourceRoutingModule } from './resource-routing.module';
import {TopicComponent} from "../../pages/topic/topic.component";
import {UserComponent} from "../../pages/user/user.component";
import {ReactiveFormsModule} from "@angular/forms";
import {TopicListComponent} from "../../pages/topic-list/topic-list.component";
import {PostComponent} from "../../pages/post/post.component";
import { PostListComponent } from '../../pages/post-list/post-list.component';
import {PostFormComponent} from "../../pages/post-form/post-form.component";
import { HeaderComponent } from '../../pages/header/header.component';
import {MatButtonModule} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";



@NgModule({
  declarations: [
    TopicComponent,
    UserComponent,
    TopicListComponent,
    PostComponent,
    PostListComponent,
    PostFormComponent,
    HeaderComponent
  ],
  exports: [
    HeaderComponent
  ],
    imports: [
        CommonModule,
        ResourceRoutingModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatSidenavModule,
        MatIconModule,
        MatListModule
    ]
})
export class ResourceModule { }
