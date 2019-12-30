import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish.model';
import * as $ from 'jquery';
import { FoodRecipeService } from '../food-recipe-service/food-recipe-service.service';
import { Category } from '../model/category.model';
import { Ingredient } from '../model/ingredient.model';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.css']
})
export class EditRecipeComponent implements OnInit {

  dish: Dish;

  categoryList: string[] = [];
  ingredientList: string[] = [];

  selectedCategory: any;
  selectedIngredients: any;

  constructor(private service: FoodRecipeService) { 
    // this.dish = service.transitDish;
    this.dish = new Dish();

    service.loadAllCategories().forEach(category => this.categoryList.push(category.categoryName));
    service.loadAllIngredients().forEach(ingredient => this.ingredientList.push(ingredient.ingredientName));
  }

  ngOnInit() {
    $('div.file-field').hover(
      function() {
        $('.file-upload-image').addClass('deep-blur');
        $('.file-upload-image-plus').addClass('increase-opacity');
      }, function() {
        $('.file-upload-image').removeClass('deep-blur');
        $('.file-upload-image-plus').removeClass('increase-opacity');
    });
  }

  uploadDish(): void {
    let selectedCategoryName = this.selectedCategory[0].display;
    this.dish.category = this.service.loadAllCategories().filter(category => category.categoryName === selectedCategoryName)[0];

    if (this.dish.category === undefined)
      this.dish.category = new Category(0, selectedCategoryName);
      
    
  }

}
