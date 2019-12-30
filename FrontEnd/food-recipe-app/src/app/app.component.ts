import { Component } from '@angular/core';
import * as $ from 'jquery';
import { FoodRecipeService } from './food-recipe-service/food-recipe-service.service';
import { User } from './model/user.model';
import { Role } from './model/role.enum';
import { Router } from '@angular/router';
import { Dish } from './model/dish.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  isSignInVisible = false;
  isUserLoggedIn =true;
  isAdminLoggedIn = false;

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
      this.isSignInVisible = false;
      if (userDetails.roles[0] === Role.ROLE_ADMIN)
        this.isAdminLoggedIn = true;
      else
        this.isUserLoggedIn = true;
    }
  }

  showMoreMenuItems(): void {
    $('div#menu').toggleClass('more-visible');
    // $('div#menu > button.user-action').toggleClass('menu-items-visible'); // Doesn't work
  }

  showProfile(): void {
    $('div#menu').toggleClass('more-visible');
    this.router.navigate(['view-user']);
  }

  createRecipe(): void {
    $('div#menu').toggleClass('more-visible');
    this.service.transitDish = new Dish();
    this.router.navigate(['create-recipe']);
  }
}
