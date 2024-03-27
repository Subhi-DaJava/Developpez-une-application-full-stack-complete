import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {SessionService} from "../../services/session-service/session.service";

@Injectable({
  providedIn: 'root'
})
export class UnAuthGuard implements CanActivate {

  constructor(private router: Router,
              private sessionService: SessionService) { }


  public canActivate(): boolean {
    if (this.sessionService.isLogin()) {
      this.router.navigate(['resources/posts']).then();
      return false;
    }
    return true;
  }

}
