import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Topic} from "../../models/topic";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  baseUrl = 'http://localhost:9000';

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
