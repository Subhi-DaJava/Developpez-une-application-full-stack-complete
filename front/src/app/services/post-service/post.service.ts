import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../../models/post";
import {PostLight} from "../../models/post-light";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = environment.baseUrl + '/post';
  constructor(private http: HttpClient) { }


  public getPostBy(postId: number): Observable<Post> {
    return this.http.get<Post>(`${this.baseUrl}/${postId}`);
  }

  public createPost(post: PostLight): Observable<Post> {

    return this.http.post<Post>(this.baseUrl, post);
  }

  public getAllPostsSubscribedThemesChronologicallyByUser(username: string): Observable<Post[]> {
    const params = new HttpParams()
      .set('username', username);

    return this.http.get<Post[]>(`${this.baseUrl}`, {params});
  }
}
