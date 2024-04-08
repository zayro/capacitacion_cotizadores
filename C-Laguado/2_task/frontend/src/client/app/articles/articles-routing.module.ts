import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ArticlesComponent } from './articles.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: 'articles', component: ArticlesComponent }
    ])
  ],
  exports: [RouterModule]
})
export class ArticlesRoutingModule { }
