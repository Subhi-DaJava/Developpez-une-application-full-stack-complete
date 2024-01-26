import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Topic} from "../models/topic";
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
}
