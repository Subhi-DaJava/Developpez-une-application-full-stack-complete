import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {TopicComponent} from "../../pages/topic/topic.component";
import {UserComponent} from "../../pages/user/user.component";

const routes: Routes = [
  { path: '', title: 'Topics', component: TopicComponent },
  { path: 'user/:username', title: 'User-Profile', component: UserComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResourceRoutingModule { }
