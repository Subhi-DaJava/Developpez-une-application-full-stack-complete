import { Component } from '@angular/core';
import {AuthService} from "../../auth-service/auth.service";
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegisterRequest} from "../../login-register-request/register-request";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router) { }

  public onError = false;
  public errorMessage = '';

  public form = this.formBuilder.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    username: [
      '',
      [
        Validators.required,
        Validators.minLength(3)
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

    if(this.form.invalid) {
      this.onError = true;
      this.errorMessage = '';
      return;
    }

    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
        next: (_: void) => this.router.navigate(['']),
        error: error => {
          if(error.status === 409){
            this.errorMessage = 'Error, User already exists with email or username !!';
            this.onError = false;
          } else if (error.status === 400) {
            this.errorMessage = 'Error, Invalid email or password !!';
            this.onError = false;
          } else {
            this.errorMessage = error.error;
          }
        }
      }
    );
  }
}
