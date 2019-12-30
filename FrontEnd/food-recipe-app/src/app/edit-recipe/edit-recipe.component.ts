import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish.model';
import * as $ from 'jquery';
import { FoodRecipeService } from '../food-recipe-service/food-recipe-service.service';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.css']
})
export class EditRecipeComponent implements OnInit {

  dish: Dish;
  // games = ["Baseball","Tennis","Golf","Cricket","Football","Hockey","Badminton","Volleyball","Boxing","Kabaddi","Chess","Long Jump","High Jump","Racing","Handball","Swimming","Wrestling"];
  categoryInput: string = '';
  
  ingredientList: string[] = ['Salt', 'Sugar', 'Rice', 'Pepper'];

  constructor(private service: FoodRecipeService) { 
    // this.dish = service.transitDish;
    this.dish = new Dish();
  }

  ngOnInit() {

    $('.chips').material_chip();
    $('.chips-placeholder').material_chip({
      placeholder: 'Enter a tag',
      secondaryPlaceholder: '+Tag',
    });
    
  }

}
