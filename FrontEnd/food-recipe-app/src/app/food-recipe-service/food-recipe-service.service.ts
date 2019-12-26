import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dish } from '../model/dish.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Category } from '../model/category.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class FoodRecipeService {

  private dishesMaster: Dish[];
  private categoriesMaster: Category[];

  private jwToken: string = '';
  private loggedInUser: User = null;

  private AUTHORIZATION_TOKEN: string = 'Authorization';

  transitDish: Dish = null;

  private FETCH_ALL_RECIPES_URL: string = 'http://localhost:8180/recipe-api/fetch-all';
  private SIGN_UP_USER_URL: string = 'http://localhost:8180/auth-api/sign-up';
  private LOG_IN_USER_URL: string = 'http://localhost:8180/auth-api/authenticate';
  private GET_USER_DETAILS_URL: string = 'http://localhost:8180/auth-api/get-user';

  constructor(private httpClient: HttpClient) {
    this.dishesMaster = [];
    this.categoriesMaster = [];
  }
  
  loadAllRecipes(): Observable<Dish[]> {
    return this.httpClient.get<Dish[]>(this.FETCH_ALL_RECIPES_URL);
  }

  setDishes(downloadedDishes: Dish[]): void {
    this.dishesMaster = downloadedDishes;
    this.extractCategories();
  }

  private extractCategories(): void {
    let foundCategories: Category[] = [];
    this.dishesMaster.forEach(dish => foundCategories.push(dish.category));
    this.categoriesMaster = Array.from(new Set(foundCategories));
  }

  getPopularDishes(numDishesWanted: number): Dish[] {
    let dishesSortedByReview = this.dishesMaster.sort((dishOne, dishTwo) => dishOne.reviews.length - dishTwo.reviews.length)
    return dishesSortedByReview.slice(dishesSortedByReview.length - numDishesWanted - 1, dishesSortedByReview.length);
  }

  loadRandomCategories(): Category[] {
    let numOne = Math.floor(Math.random() * this.categoriesMaster.length)
    let numTwo = numOne;
    let i = 1;

    // TODO: Change the following algorithm
    while (numTwo == numOne) {
      numTwo = Math.floor(Math.random() * this.categoriesMaster.length)
      if (i == 4)
        break;
      i += 1;
    }

    return [this.categoriesMaster[numOne], this.categoriesMaster[numTwo]]
  }

  loadCategoryWiseDishes(categoryId: number): Dish[] {
    let dishes: Dish[] = [];

    this.dishesMaster.forEach(dish => {
      if (dish.category.categoryId == categoryId) {
        dishes.push(dish)
      }
    });
    return dishes;
  }


  logInUser(): Observable<string> {
    return this.httpClient.get<string>(this.LOG_IN_USER_URL);
  }

  signUpUser(): Observable<string> {
    return this.httpClient.get<string>(this.SIGN_UP_USER_URL);
  }

  setJWToken(token: string): void {
    this.jwToken = token;
    sessionStorage.setItem('token', this.jwToken);
  }

  getJWToken(): string {
    return this.jwToken;
  }

  getUserDetails(token: string): Observable<User> {
    let headers = new HttpHeaders();
    headers.append(this.AUTHORIZATION_TOKEN, token);
    return this.httpClient.get<User>(this.GET_USER_DETAILS_URL, {headers: headers});
  }

  setSession(userDetails: User): void {
    this.loggedInUser = userDetails;
  }

  getSession(): User {
    return this.loggedInUser;
  }
}
