import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowseCategryComponent } from './browse-categry/browse-categry.component';
import { EditRecipeComponent } from './edit-recipe/edit-recipe.component';
import { MiscComponent } from './misc/misc.component';
import { HomeComponent } from './home/home.component';
import { ListRecipeComponent } from './list-recipe/list-recipe.component';
import { ViewRecipeComponent } from './view-recipe/view-recipe.component';
import { ViewUserComponent } from './view-user/view-user.component';
import { NavigatorRoutingModule } from './navigator/navigator-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SliderModule } from 'angular-image-slider';
import { NgImageSliderModule } from 'ng-image-slider';

@NgModule({
  declarations: [
    AppComponent,
    BrowseCategryComponent,
    EditRecipeComponent,
    MiscComponent,
    HomeComponent,
    ListRecipeComponent,
    ViewRecipeComponent,
    ViewUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NavigatorRoutingModule,
    HttpClientModule,
    // SliderModule,
    // BrowserAnimationsModule
    NgImageSliderModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
