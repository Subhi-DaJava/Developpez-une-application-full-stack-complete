import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Comment} from "../../models/comment";
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class CommentService {
  baseUrl = 'http://localhost:9000';
  constructor(private http: HttpClient) { }

  public createComment(comment: Comment): Observable<Comment> {

    return this.http.post<Comment>(`${this.baseUrl}/comment`, comment);
  }
}
