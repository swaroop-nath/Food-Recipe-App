import { TestBed } from '@angular/core/testing';

import { FoodRecipeService } from './food-recipe-service.service';

describe('FoodRecipeServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FoodRecipeService = TestBed.get(FoodRecipeService);
    expect(service).toBeTruthy();
  });
});
