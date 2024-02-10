import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {TopicListComponent} from "../../pages/topic-list/topic-list.component";
import {PostComponent} from "../../pages/post/post.component";
import {PostListComponent} from "../../pages/post-list/post-list.component";

const routes: Routes = [
  { path: '', title: 'Posts', component: PostListComponent },
  { path: 'detail/:id', title: 'Post Detail', component: PostComponent },
  { path: 'topics', title: 'Topics', component: TopicListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResourceRoutingModule { }
