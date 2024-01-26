import {Component, OnInit} from "@angular/core";
import {TopicService} from "../services/topic.service";
import {Topic} from "../models/topic";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent implements OnInit {

  topics: Topic[] = [];
  errorMessage!: string;

  constructor(private topicService: TopicService) { }
  ngOnInit(): void {
    this.getTopics();
  }
  getTopics() {
   this.topicService.getTopics().subscribe({
      next: topics => {
        this.topics = topics;
      },
      error: err => {
        this.errorMessage = err.error;
      }
    });
  }

}
