import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { FoodRecipeService } from '../food-recipe-service/food-recipe-service.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor(private service: FoodRecipeService, private appComponent: AppComponent) { }

  ngOnInit() {}

  login(): void {
    this.service.logInUser().subscribe(token => {
      this.service.setJWToken(token);
      this.service.getUserDetails(token).subscribe(userDetails => {
        this.service.setSession(userDetails);
        this.appComponent.setUpView(token, userDetails);
      });
    });
  }

  signUpUser(): void {
    this.service.signUpUser().subscribe(token => {
      this.service.setJWToken(token);
      this.service.getUserDetails(token).subscribe(userDetails => {
        this.service.setSession(userDetails);
        this.appComponent.setUpView(token, userDetails);
      });
    });
  }

  closeSignIn(): void {
    this.appComponent.closeSignIn();
  }
}
