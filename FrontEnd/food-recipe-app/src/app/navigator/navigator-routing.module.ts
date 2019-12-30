import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { ViewRecipeComponent } from '../view-recipe/view-recipe.component';
import { ViewUserComponent } from '../view-user/view-user.component';
import { EditRecipeComponent } from '../edit-recipe/edit-recipe.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent
  },
  {
    path: 'view-recipe',
    component: ViewRecipeComponent
  },
  {
    path: 'view-user',
    component: ViewUserComponent
  },
  {
    path: 'create-recipe',
    component: EditRecipeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class NavigatorRoutingModule { }
