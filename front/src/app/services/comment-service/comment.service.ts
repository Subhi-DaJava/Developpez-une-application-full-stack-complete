import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Comment} from "../../models/comment";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public createComment(comment: Comment): Observable<Comment> {

    return this.http.post<Comment>(`${this.baseUrl}/comment`, comment);
  }
}
