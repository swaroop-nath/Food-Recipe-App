import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  isSignInVisible = false;

  searchRecipe(): void {

  }

  showAbout(): void {

  }

  signIn(): void {
    console.log(this.isSignInVisible);
    
    this.isSignInVisible = !this.isSignInVisible;
  }

  login(): void {

  }

  closeSignIn(): void {
    this.isSignInVisible = false;
  }

  signUpUser(): void {

  }
}
