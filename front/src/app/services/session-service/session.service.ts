import { Injectable } from '@angular/core';
import {SessionInformation} from "../../models/session-information";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged = false;
  public sessionInformation: SessionInformation | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public $isLogged() {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(jwtToken: SessionInformation) {
    this.sessionInformation = jwtToken;
    this.isLogged = true;
    this.next();
  }

  public logOut() {
    this.sessionInformation = undefined;
    this.isLogged = false;
    this.next();
  }

  private next() {
    this.isLoggedSubject.next(this.isLogged);
  }

}
