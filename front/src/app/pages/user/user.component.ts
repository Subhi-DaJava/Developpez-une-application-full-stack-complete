import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user-service/user.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  user!: User;
  errorMessage!: string;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUser(1);
  }
  getUser(id: number) {
    this.userService.getUser(id).subscribe({
      next: data => {
        this.user = data;
      },
      error: (err: any) => {
        this.errorMessage = err.error;
      }
    });
  }
}
