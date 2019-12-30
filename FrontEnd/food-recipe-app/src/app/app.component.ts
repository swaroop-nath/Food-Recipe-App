import { Component } from '@angular/core';
import * as $ from 'jquery';
import { FoodRecipeService } from './food-recipe-service/food-recipe-service.service';
import { User } from './model/user.model';
import { Role } from './model/role.enum';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  isSignInVisible = false;
  isUserLoggedIn =false;
  isAdminLoggedIn = true;

  constructor(private service: FoodRecipeService, private router: Router) {}

  searchRecipe(): void {

  }

  showAbout(): void {

  }

  signIn(): void {
    this.isSignInVisible = !this.isSignInVisible;
    $('div').toggleClass('blur');
  }

  signOut(): void {
    // Sign the user out - unset session storage item and unset session in service.
    this.service.invalidateSession();
    
    this.isUserLoggedIn = false;
    this.isAdminLoggedIn = false;
  }

  closeSignIn(): void {
    this.isSignInVisible = false;
    $('div').removeClass('blur');
  }

  setUpView(token: string, userDetails: User): void {
    if (token === this.service.getJWToken() && userDetails != null) {
      // Show details only visible to user.
    }
  }

  showMoreMenuItems(): void {
    $('div#menu').toggleClass('more-visible');
    $('div#menu > button.user-action').toggleClass('menu-items-visible'); // Doesn't work
  }

  showProfile(): void {
    this.router.navigate(['view-user']);
  }

  createRecipe(): void {
    this.router.navigate(['create-recipe']);
  }
}
