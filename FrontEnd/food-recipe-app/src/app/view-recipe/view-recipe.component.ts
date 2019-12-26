import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish.model';
import { FoodRecipeService } from '../food-recipe-service/food-recipe-service.service';
import { Router } from '@angular/router';
import { Ingredient } from '../model/ingredient.model';

@Component({
  selector: 'app-view-recipe',
  templateUrl: './view-recipe.component.html',
  styleUrls: ['./view-recipe.component.css']
})
export class ViewRecipeComponent implements OnInit {

  imageViewIndex: number = 0;
  dish:Dish;
  cookingSteps: string[] = [];
  ingredientsGrid: Ingredient[][] = [];
  imageURLs: Array<object> = [];

  constructor(private service: FoodRecipeService, private router: Router) { 
    this.dish = new Dish();
  }

  ngOnInit() {
    this.dish = this.service.transitDish;
    this.cookingSteps = this.dish.cookingProcedure.split("\n");
    this.makeGridOfIngredients();

    let index = 1;
    this.dish.images.forEach(imageObject => {
      this.imageURLs.push({
        image: imageObject.imageURL,
        thumbImage: imageObject.imageURL,
        alt: '',
        title: 'Ingredient ' + index
      });
      index += 1;
    });
  }

  makeGridOfIngredients() {
    let index = 0;
    let limit = this.dish.ingredients.length;

    while (index < limit) {
      let indicator = 0;
      let tempArray = [];
      
      while (indicator < 4 && index < limit) {
        tempArray.push(this.dish.ingredients[index]);
        index += 1;

        let randomNumber = Math.floor(Math.random() * 4);
        
        if (randomNumber == 0)
          break;
      }

      this.ingredientsGrid.push(tempArray);
    }
  }

  showDishesInCategory(): void {
    // Route to list recipe page with dishes in the category
  }

}
