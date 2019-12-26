import { Component } from '@angular/core';
import * as $ from 'jquery';
import { FoodRecipeService } from './food-recipe-service/food-recipe-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  isSignInVisible = false;

  constructor(private service: FoodRecipeService) {}

  searchRecipe(): void {

  }

  showAbout(): void {

  }

  signIn(): void {
    this.isSignInVisible = !this.isSignInVisible;
    $('div').toggleClass('blur');
  }

  closeSignIn(): void {
    this.isSignInVisible = false;
    $('div').toggleClass('blur');
  }

  setUpView(token, userDetails): void {
    if (!(token === this.service.getJWToken() && userDetails != null))
      alert("User Not Logged In. Please Login");
    else {
      // Show specific details
    }
  }
}
