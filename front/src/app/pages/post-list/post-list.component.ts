import { Component, OnInit } from '@angular/core';
import {Post} from "../../models/post";
import {PostService} from "../../services/post-service/post.service";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {
  posts: Post[] = [];
  errorMessage!: string;
  constructor(private postService: PostService) { }

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
}
