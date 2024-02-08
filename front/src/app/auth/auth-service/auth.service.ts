import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoginRequest} from "../login-register-request/login-request";
import {SessionInformation} from "../../models/session-information";
import {RegisterRequest} from "../login-register-request/register-request";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl = 'http://localhost:9000';

  constructor(private http: HttpClient) { }

  public register(registerRequest: RegisterRequest): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/auth/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<SessionInformation> {
    return this.http.post<SessionInformation>(`${this.baseUrl}/auth/login`, loginRequest);
  }
}
