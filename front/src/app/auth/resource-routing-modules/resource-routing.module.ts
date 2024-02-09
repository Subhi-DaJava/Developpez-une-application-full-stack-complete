import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserComponent} from "../../pages/user/user.component";
import {TopicListComponent} from "../../pages/topic-list/topic-list.component";

const routes: Routes = [
  { path: '', title: 'Topics', component: TopicListComponent },
  { path: 'user/:username', title: 'User-Profile', component: UserComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResourceRoutingModule { }
