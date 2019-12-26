import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { ViewRecipeComponent } from '../view-recipe/view-recipe.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent
  },
  {
    path: 'view-recipe',
    component: ViewRecipeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class NavigatorRoutingModule { }
