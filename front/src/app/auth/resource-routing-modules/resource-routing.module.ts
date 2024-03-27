import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {TopicListComponent} from "../../pages/topic-list/topic-list.component";
import {PostComponent} from "../../pages/post/post.component";
import {PostListComponent} from "../../pages/post-list/post-list.component";
import {PostFormComponent} from "../../pages/post-form/post-form.component";

const routes: Routes = [
  { path: 'posts', title: 'Posts', component: PostListComponent },
  { path: 'post/detail/:id', title: 'Post Detail', component: PostComponent },
  { path: 'topics', title: 'Topics', component: TopicListComponent },
  { path: 'post/form', title: 'Post Form', component: PostFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResourceRoutingModule { }
