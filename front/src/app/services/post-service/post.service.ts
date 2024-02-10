import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../../models/post";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private url = 'http://localhost:9000/post';

  constructor(private http: HttpClient) { }


  public getPostBy(postId: number): Observable<Post> {
    return this.http.get<Post>(`${this.url}/${postId}`);
  }

  public createPost(post: Post): Observable<Post> {

    return this.http.post<Post>(this.url, post);
  }

  public getAllPostsSubscribedThemesChronologicallyByUser(username: string): Observable<Post[]> {
    const params = new HttpParams()
      .set('username', username);

    return this.http.get<Post[]>(`${this.url}`, {params});
  }
}
