import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:9000/user';
  constructor(private http: HttpClient) { }

  getUser(id: number) {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }
}
