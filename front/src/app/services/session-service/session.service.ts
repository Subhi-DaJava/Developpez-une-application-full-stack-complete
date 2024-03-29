import { Injectable } from '@angular/core';
import {SessionInformation} from "../../models/session-information";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public sessionInformation: SessionInformation | undefined;

  public logIn(jwtToken: SessionInformation) {
    this.sessionInformation = jwtToken;
    this.sessionInformation.username = jwtToken.username;
    sessionStorage.setItem('token', jwtToken.token);
    sessionStorage.setItem('username', jwtToken.username);
  }

  public logOut() {
    this.sessionInformation = undefined;
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
  }

  public isLogin() {
    return sessionStorage.getItem('token') && sessionStorage.getItem('token') !== null;
  }

  public getUsername() {
    return sessionStorage.getItem('username');
  }

  public updateUsername(username: string) {
    sessionStorage.setItem('username', username);
  }

  public getToken() {
    return sessionStorage.getItem('token');
  }

}
