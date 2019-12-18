import { Category } from './category.model';
import { Ingredient } from './ingredient.model';
import { Review } from './review.model';
import { User } from './user.model';
import { Image } from './image.model';

export class Dish {
    constructor(
        public dishId: number = 0,
        public dishName: string = '',
        public description: string = '',
        public cookingProcedure: string = '',
        public category: Category = null,
        public ingredients: Ingredient[] = [],
        public images: Image[] = [],
        public reviews: Review[] = [],
        public author: User = null
    ) {}
}