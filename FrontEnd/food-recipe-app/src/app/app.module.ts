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
import { NgImageSliderModule } from 'ng-image-slider';
import { AuthComponent } from './auth/auth.component';
// import 'materialize-css';
// import { MaterializeModule } from "angular2-materialize";
import { MatInputModule, MatButtonModule, MatSelectModule, MatIconModule } from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    BrowseCategryComponent,
    EditRecipeComponent,
    MiscComponent,
    HomeComponent,
    ListRecipeComponent,
    ViewRecipeComponent,
    ViewUserComponent,
    AuthComponent,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule
    // MaterializeModule
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NavigatorRoutingModule,
    HttpClientModule,
    // SliderModule,
    // BrowserAnimationsModule
    NgImageSliderModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
