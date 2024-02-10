import {Component, OnInit} from '@angular/core';
import {PostService} from "../../services/post-service/post.service";
import {Post} from "../../models/post";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  post!: Post;
  errorMessage!: string;
  postId!: number;

  constructor(private postService: PostService,
              private activatedRoute: ActivatedRoute) {
    this.postId = +this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.getPostById();
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
        this.errorMessage = err.error;
      }
    });
  }

}
