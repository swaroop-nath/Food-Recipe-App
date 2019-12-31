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
  ingredientGrid: Ingredient[][] = [];
  selectedIngredientFlag: Map<Ingredient, Boolean>;

  selectedCategory: any[];

  constructor(private service: FoodRecipeService) { 
    // this.dish = service.transitDish;
    this.dish = new Dish();

    service.loadAllCategories().forEach(category => this.categoryList.push(category.categoryName));
    this.makeGridOfIngredients(service.loadAllIngredients());
    
    this.selectedIngredientFlag = new Map<Ingredient, Boolean>();
  }

  makeGridOfIngredients(loadedIngredients: Ingredient[]): void {
    let index = 0;
    let limit = loadedIngredients.length;

    while (index < limit) {
      let indicator = 0;
      let tempArray = [];
      
      while (indicator < 4 && index < limit) {
        tempArray.push(loadedIngredients[index]);
        index += 1;

        let randomNumber = Math.floor(Math.random() * 4);
        
        if (randomNumber == 0)
          break;
      }

      this.ingredientGrid.push(tempArray);
    }
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

    $('td').click(function() {
      console.log('hello');
      
    })
  }
  
  clickOnIngredient(row: number, col: number): void {
    this.selectedIngredientFlag.set(this.ingredientGrid[row][col], !this.selectedIngredientFlag.get(this.ingredientGrid[row][col]));
  }

  uploadDish(): void {
    let selectedCategoryName = this.selectedCategory[0].display;
    this.dish.category = this.service.loadAllCategories().filter(category => category.categoryName === selectedCategoryName)[0];

    if (this.dish.category === undefined)
      this.dish.category = new Category(0, selectedCategoryName);

    
  }

}
