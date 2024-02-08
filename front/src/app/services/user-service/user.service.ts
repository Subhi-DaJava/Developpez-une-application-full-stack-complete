import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:9000/user';
  constructor(private http: HttpClient) { }

  getUser(username: string) {
    return this.http.get<User>(`${this.baseUrl}/${username}`);
  }

  updateUser(user: User) {
    return this.http.put<void>(`${this.baseUrl}/${user.username}`, user);
  }
}
