import {Component, Input, OnInit} from "@angular/core";
import {Topic} from "../../models/topic";
import {TopicService} from "../../services/topic-service/topic.service";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent  implements OnInit {
  isSubscribed = false;

  constructor(private topicService: TopicService) {}

  ngOnInit(): void {
    this.isSubscribed = this.subscribedTopics.some(topic => topic.name === this.topic.name);
  }

  @Input() topic!: Topic;
  @Input() subscribedTopics!: Topic[];
  errorMessage!: string;

  onSubscribe(topicName: string) {
    const username = sessionStorage.getItem('username');
    if (!username) {
      this.errorMessage = "You must be logged in to subscribe to a topic";
      return;
    }

    if (this.isSubscribed) {
      this.topicService.unsubscribe(topicName, username).subscribe({
        next: () => {
          this.isSubscribed = false;
        },
        error: err => {
          this.errorMessage = err.error;
        }
      });
    } else {
      this.topicService.subscribe(topicName, username).subscribe({
        next: () => {
          this.isSubscribed = true;
        },
        error: err => {
          this.errorMessage = err.error;
        }
      });
    }
  }
}
