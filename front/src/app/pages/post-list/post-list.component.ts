import { Component, OnInit } from '@angular/core';
import {PostService} from "../../services/post-service/post.service";
import {PostLight} from "../../models/post-light";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {
  posts: PostLight[] = [];
  errorMessage!: string;
  constructor(private postService: PostService,
              private router: Router) { }

  ngOnInit(): void {
    this.getPosts();
  }


  getPosts() {
    const username = sessionStorage.getItem('username');
    if (!username) {
      this.errorMessage = "You must be logged in to see your posts";
      return;
    }

    this.postService.getAllPostsSubscribedThemesChronologicallyByUser(username).subscribe({
      next: posts => {
        this.posts = posts;
      },
      error: err => {
        this.errorMessage = err.error;
      }
    });
  }

  createPost() {
    this.router.navigate(['posts/form']).then();
  }
}
