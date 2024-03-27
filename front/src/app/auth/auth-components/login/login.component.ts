import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {SessionService} from "../../../services/session-service/session.service";
import {AuthService} from "../../auth-service/auth.service";
import {LoginRequest} from "../../login-register-request/login-request";
import {SessionInformation} from "../../../models/session-information";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public onError = false;
  public errorMessage = '';

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router,
              private sessionService: SessionService) { }

  public loginForm = this.formBuilder.group({
    usernameOrEmail: [
      '',
      [
        Validators.required
      ]
    ],
    password: [
      '',
      [
        Validators.required,
      ]
    ]
  });

  public onSubmit(): void {

    if (this.loginForm.invalid) {
      this.onError = true;
      this.errorMessage = '';
      return;
    }

    const loginRequest = this.loginForm.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.logIn(response);
        this.router.navigate(['/resources/posts']).then();
      },
      error: error => {
        if(error.status === 401){
          this.errorMessage = 'Error, Credentials not correct !!';
          this.onError = false;
        } else {
          this.errorMessage = error.error;
        }
      }
    });
  }
}
