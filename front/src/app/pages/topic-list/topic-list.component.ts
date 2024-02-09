import {Component, OnInit} from '@angular/core';
import {Topic} from "../../models/topic";
import {TopicService} from "../../services/topic-service/topic.service";
import {UserService} from "../../services/user-service/user.service";
import {User} from "../../models/user";

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
              private userService: UserService) { }
  ngOnInit(): void {
    this.getTopics();
  }

  getTopics() {

    this.topicService.getTopics().subscribe({
      next: topics => {
        const username =sessionStorage.getItem('username')
        if (!username) {
          this.errorMessage = "You must be logged in to see your topics";
          return;
        }
        this.userService.getUser(username).subscribe({
          next: user => {
            this.user = user;
            this.topics = topics;
          },
          error: err => {
            this.errorMessage = err.error;
          }
        });
      },
      error: err => {
        this.errorMessage = err.error;
      }
    });
  }

}
