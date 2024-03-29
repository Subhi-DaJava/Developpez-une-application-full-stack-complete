import {Component, OnInit} from '@angular/core';
import {Topic} from "../../models/topic";
import {TopicService} from "../../services/topic-service/topic.service";
import {UserService} from "../../services/user-service/user.service";
import {User} from "../../models/user";
import {SessionService} from "../../services/session-service/session.service";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.scss']
})
export class TopicListComponent implements OnInit {
  topics: Topic[] = [];
  errorMessage!: string;
  user!: User;
  constructor(private topicService: TopicService,
              private userService: UserService,
              private sessionService: SessionService) { }
  ngOnInit(): void {
    this.getTopics();
  }

  getTopics() {
    const username = this.sessionService.getUsername();
    if (!username) {
      this.errorMessage = "You must be logged in to see your topics";
      return;
    }
    this.userService.getUser(username).pipe(
      switchMap(user => {
        this.user = user;
        return this.topicService.getTopics();
      })
    ).subscribe({
      next: topics => {
        this.topics = topics;
      },
      error: err => {
        this.errorMessage = err.error;
      }
    });
  }
}
