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
  isSorted = false;
  originalPosts: PostLight[] = [];

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
        this.originalPosts = posts ? [...posts] : [];
      },
      error: err => {
        this.errorMessage = err.error;
      }
    });
  }

  createPost() {
    this.router.navigate(['resources/post/form']).then();
  }

  sortByCreatedAt() {
    if (this.isSorted) {
      this.posts = [...this.originalPosts];
      this.isSorted = false;
    } else {
      this.posts.sort((a, b) => {
        if (!a.createdAt || !b.createdAt) {
          return 1;
        }
        return Date.parse(a.createdAt.toString()) - Date.parse(b.createdAt.toString());
      });
      this.isSorted = true;
    }
  }
}
