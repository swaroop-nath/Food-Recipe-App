import { Component, OnInit } from '@angular/core';
import { FoodRecipeService } from '../food-recipe-service/food-recipe-service.service';
import { Dish } from '../model/dish.model';
import { Category } from '../model/category.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  popularDishes: Dish[];
  categoryOneDishes: Dish[];
  categoryTwoDishes: Dish[];
  categoryOne: Category;
  categoryTwo: Category;

  imageViewIndex: number = 0;

  POPULAR_DISHES_COUNT = 5;

  constructor(private service: FoodRecipeService, private router: Router) { 
    this.popularDishes = [];
    this.categoryOneDishes = [];
    this.categoryTwoDishes = [];
    this.categoryOne = new Category();
    this.categoryTwo = new Category();
  }

  ngOnInit() {
    this.service.loadAllRecipes().subscribe(fetchedDishes => this.setUpData(fetchedDishes));
  }

  private setUpData(fetchedDishes: Dish[]): void {
    this.service.setDishes(fetchedDishes);
    this.popularDishes = this.service.getPopularDishes(this.POPULAR_DISHES_COUNT);
    console.log(fetchedDishes);
    
    [this.categoryOne, this.categoryTwo] = this.service.loadRandomCategories()
    this.categoryOneDishes = this.service.loadCategoryWiseDishes(this.categoryOne.categoryId);
    this.categoryTwoDishes = this.service.loadCategoryWiseDishes(this.categoryTwo.categoryId);
  }

  showDish(selectedDish: Dish): void {
    this.service.transitDish = selectedDish;
    this.router.navigate(['/view-recipe']);
  }

}
