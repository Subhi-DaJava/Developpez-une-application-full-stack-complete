import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor
} from '@angular/common/http';
import {SessionService} from "../services/session-service/session.service";

@Injectable({providedIn: 'root'})
export class JwtInterceptor implements HttpInterceptor {

  constructor(private sessionService: SessionService) {}

  public intercept(request: HttpRequest<any>, next: HttpHandler) {

    if(this.sessionService.isLogin()) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${sessionStorage.getItem('token')}`
        }
      });
    }
    return next.handle(request);
  }
}
