import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {UnAuthGuard} from "./auth/gurards/un-auth.guard";
import {AuthGuard} from "./auth/gurards/auth.guard";

const routes: Routes = [
  { path: '', component: HomeComponent },

  { path: '', canActivate: [UnAuthGuard],
    loadChildren: () => import('./auth/auth-routing-modules/auth.module').then(m => m.AuthModule) },


  { path: 'topics',
    canActivate: [AuthGuard],
    loadChildren: () => import('./auth/resource-routing-modules/resource.module').then(m => m.ResourceModule) },

  { path: 'user/: username', title: 'User-Profile',
    canActivate: [AuthGuard],
    loadChildren: () => import('./auth/resource-routing-modules/resource.module').then(m => m.ResourceModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
