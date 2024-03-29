import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../../services/post-service/post.service";
import {Router} from "@angular/router";
import {PostLight} from "../../models/post-light";
import {TopicService} from "../../services/topic-service/topic.service";
import {Topic} from "../../models/topic";
import {SessionService} from "../../services/session-service/session.service";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit {

  public postForm: FormGroup | undefined;
  errorMessage!: string;
  topics: Topic[] = [];

  constructor(private formBuilder: FormBuilder,
              private postService: PostService,
              private topicService: TopicService,
              private sessionService: SessionService,
              private router: Router) { }

  ngOnInit(): void {
    this.initForm();
    this.getTopics();
  }

  initForm() {

    this.postForm = this.formBuilder.group({

      title: ['', Validators.compose([Validators.required, Validators.minLength(3)]),
      ],
      content: ['', Validators.compose([Validators.required, Validators.minLength(3)])],

      topicName: ['', [Validators.required]
      ]
    });
  }

  public createPost() {
    const post = this.postForm?.value as PostLight;
    const username = this.sessionService.getUsername();

    if (!username) {
      this.router.navigate(['/login']).then();
      return;
    }
    post.authorUsername = username;

    this.postService.createPost(post).subscribe({
      next: () => {
        this.router.navigate(['posts']).then();
      },
      error: err => {
        this.handleError(err);
      }
    });
  }

  getTopics() {
    this.topicService.getTopics().subscribe({
      next: (topics) => {
        this.topics = topics;
      },
      error: err => {
        this.handleError(err);
      }
    });
  }

  private handleError(err: any) {
    this.errorMessage = err.error;
  }
}
