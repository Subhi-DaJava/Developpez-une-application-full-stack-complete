import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user";
import {UserUpdate} from "../../models/user-update";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:9000/user';
  constructor(private http: HttpClient) { }

  getUser(username: string) {
    return this.http.get<User>(`${this.baseUrl}/${username}`);
  }

  updateUser(user: UserUpdate, username: string) {
    return this.http.put<void>(`${this.baseUrl}/${username}`, user);
  }
}
