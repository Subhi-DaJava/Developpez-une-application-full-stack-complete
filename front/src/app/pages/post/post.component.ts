import {Component, OnInit} from '@angular/core';
import {PostService} from "../../services/post-service/post.service";
import {Post} from "../../models/post";
import {ActivatedRoute, Router} from "@angular/router";
import {CommentService} from "../../services/comment-service/comment.service";
import {Comment} from "../../models/comment";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SessionService} from "../../services/session-service/session.service";
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  post!: Post;
  errorMessage!: string;
  postId!: number;
  commentForm: FormGroup | undefined

  constructor(private postService: PostService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private commentService: CommentService,
              private sessionService: SessionService,
              private formBuilder: FormBuilder) {
    this.postId = +this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.getPostById();
    this.initForm();
  }

  public getPostById() {
    if(isNaN(this.postId)) {
      this.errorMessage = "Invalid post ID";
      return;
    }

    return this.postService.getPostBy(this.postId).subscribe({
      next: post => {
        this.post = post;
      },
      error: err => {
        this.handleError(err);
      }
    });
  }

  initForm() {
    this.commentForm = this.formBuilder.group({
      content: ['', Validators.compose([Validators.required, Validators.minLength(3)])]
    });
  }

  createComment() {
    const comment = this.commentForm?.value as Comment;
    const username = this.sessionService.getUsername();
    if(!username) {
      this.router.navigate(['/login']).then();
      return;
    }
    comment.authorUsername = username;
    comment.postId = this.postId;

    this.commentService.createComment(comment).subscribe({
      next: (data) => {
        this.getPostById();
        this.commentForm?.reset();
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
