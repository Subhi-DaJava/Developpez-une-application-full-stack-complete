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
    const username = this.sessionService.getUsername()
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
        this.handleError(err);
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
        this.handleError(err);
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
        this.sessionService.updateUsername(userUpdate.username);
        this.getUser();
        this.initUserProfileForm(this.user);
        this.errorMessage = '';

      },
      error: (err: any) => {
        if(err.status === 400) {
          this.errorMessage = "Error, remove your password if you don't want to update, else Password should be at least 8 chars, 1 digit, 1 lowercase, 1 uppercase & 1 special char!!";
          return;
        }
        this.handleError(err);
      }
    });
  }

  private handleError(err: any) {
    this.errorMessage = err.error;
  }

}
