import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user-service/user.service";
import {User} from "../../models/user";
import {SessionService} from "../../services/session-service/session.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserUpdate} from "../../models/user-update";
import {Topic} from "../../models/topic";
import {TopicService} from "../../services/topic-service/topic.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  user!: User;
  errorMessage!: string;
  topics: Topic[] = [];
  public userForm: FormGroup | undefined;

  constructor(private userService: UserService,
              private sessionService: SessionService,
              private router: Router,
              private formBuilder: FormBuilder,
              private topicService: TopicService) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    const username = sessionStorage.getItem('username');
    if (!username) {
      this.errorMessage = "You must be logged in to see your posts";
      return;
    }
    this.userService.getUser(username).subscribe({
      next: userData => {
        this.user = userData;
        this.topics = this.user.topics;
        this.initUserProfileForm(this.user);
      },
      error: (err: any) => {
        this.errorMessage = err.error;
      }
    });
  }

  initUserProfileForm(user?: User) {
    this.userForm = this.formBuilder.group({
      username: [
        user ? user.username : '', Validators.compose([Validators.required, Validators.minLength(3)])
      ],
      email: [
        user ? user.email : '', Validators.compose([Validators.required, Validators.email])],
      password: ['******']
    });
  }

  unsubscribeFromTopic(topicName: string) {
    this.topicService.unsubscribe(topicName, this.user.username).subscribe({
      next: () => {
        this.topics = this.topics.filter(topic => topic.name !== topicName);
      },
      error: (err: any) => {
        this.errorMessage = err.error;
      }
    });
  }

  userLogout() {
    this.sessionService.logOut();
    this.router.navigate(['']).then();
  }

  updateUser() {
    const userUpdate = this.userForm?.value as UserUpdate;
    const username = this.user.username;
    this.userService.updateUser(userUpdate, username).subscribe({
      next: data => {
        sessionStorage.setItem('username', userUpdate.username);
      },
      error: (err: any) => {
        this.errorMessage = err.error;
      }
    });
  }

}
