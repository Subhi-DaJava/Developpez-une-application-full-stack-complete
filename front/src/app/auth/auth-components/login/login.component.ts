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
  public hide = true;
  public onError = false;

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router,
              private sessionService: SessionService) { }

  public form = this.formBuilder.group({
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
        Validators.minLength(8)
      ]
    ]
  });

  public onSubmit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.logIn(response);
        this.router.navigate(['/topics']).then();
      },
      error: error => this.onError = true,
    });
  }

}
