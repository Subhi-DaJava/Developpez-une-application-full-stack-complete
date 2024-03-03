import { Component, OnInit } from '@angular/core';
import {SessionService} from "../../services/session-service/session.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean = false;
  isMenuOpen = false;
  constructor(private sessionService: SessionService) { }

  ngOnInit(): void {
    this.isLogged();
  }

  isLogged() {
    const token = this.sessionService.sessionInformation?.token;
    this.isLoggedIn = sessionStorage.getItem('token') !== null && token !== null;
  }
  toggleMenu(event: Event) {
    event.stopPropagation();
    this.isMenuOpen = !this.isMenuOpen;
    console.log('Menu status: ', this.isMenuOpen);
  }

  closeMenu() {
    this.isMenuOpen = false;
  }
}
