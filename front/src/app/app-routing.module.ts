import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {UnAuthGuard} from "./auth/gurards/un-auth.guard";
import {AuthGuard} from "./auth/gurards/auth.guard";
import {UserComponent} from "./pages/user/user.component";
import {NotFoundComponent} from "./pages/not-found/not-found.component";

const routes: Routes = [
  { path: '', component: HomeComponent },

  { path: '',
    canActivate: [UnAuthGuard],
    loadChildren: () => import('./auth/auth-routing-modules/auth.module').then(m => m.AuthModule) },

  { path: 'posts', title: 'Post-list',
    canActivate: [AuthGuard],
    loadChildren: () => import('./auth/resource-routing-modules/resource.module').then(m => m.ResourceModule) },

  { path: 'me', title: 'User-Profile', canActivate: [AuthGuard],
    component: UserComponent },

  { path: '404', title: 'Not Found', component: NotFoundComponent},
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
