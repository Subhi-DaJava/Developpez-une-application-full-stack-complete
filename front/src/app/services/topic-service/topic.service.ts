import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Topic} from "../../models/topic";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.baseUrl + '/topic');
  }

  subscribe(topicName: string, username: string): Observable<void> {
    const params = new HttpParams()
      .set('topicName', topicName)
      .set('username', username);
    return this.http.post<void>(this.baseUrl + '/topic/subscribe', {}, {params});
  }

  unsubscribe(topicName: string, username: string): Observable<void> {
    const params = new HttpParams()
      .set('topicName', topicName)
      .set('username', username);
    return this.http.post<void>(this.baseUrl + '/topic/unsubscribe', {},{params});
  }
}
