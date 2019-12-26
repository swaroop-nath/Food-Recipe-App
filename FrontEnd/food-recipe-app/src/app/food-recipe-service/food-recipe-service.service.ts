import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dish } from '../model/dish.model';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class FoodRecipeService {

  private dishesMaster: Dish[];
  private categoriesMaster: Category[];

  transitDish: Dish = null;

  private FETCH_ALL_RECIPES_URL = 'http://localhost:8180/recipe-api/fetch-all';

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
}
