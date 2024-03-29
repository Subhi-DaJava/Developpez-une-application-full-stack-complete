import {Component, Input, OnInit} from "@angular/core";
import {Topic} from "../../models/topic";
import {TopicService} from "../../services/topic-service/topic.service";
import {SessionService} from "../../services/session-service/session.service";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent  implements OnInit {
  isSubscribed = false;

  constructor(private topicService: TopicService,
              private sessionService: SessionService) {}

  @Input() topic!: Topic;
  @Input() subscribedTopics!: Topic[];
  errorMessage!: string;

  ngOnInit(): void {
    this.checksSubscription();
  }

  checksSubscription() {
    this.isSubscribed = this.subscribedTopics.some(topic => topic.name === this.topic.name);
  }

  onSubscribe(topicName: string) {
    const username = this.sessionService.getUsername();
    if (!username) {
      this.errorMessage = "You must be logged in to subscribe to a topic";
      return;
    }

    const action = this.isSubscribed ? this.topicService.unsubscribe(topicName, username) : this.topicService.subscribe(topicName, username);

    action.subscribe({
      next: () => {
        this.isSubscribed = !this.isSubscribed;
      },
      error: err => {
        this.errorMessage = err.error;
      }
    });
  }
}
