import { Dish } from './dish.model';

export class Ingredient {
    constructor(
        public ingredientId: number = 0,
        public ingredientName: string = '',
        public dishes: Dish[] = []
    ) {}
}