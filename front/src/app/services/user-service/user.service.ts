import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user";
import {UserUpdate} from "../../models/user-update";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.baseUrl + '/user';
  constructor(private http: HttpClient) { }

  getUser(username: string) {
    return this.http.get<User>(`${this.baseUrl}/${username}`);
  }

  updateUser(user: UserUpdate, username: string) {
    return this.http.put<void>(`${this.baseUrl}/${username}`, user);
  }
}
