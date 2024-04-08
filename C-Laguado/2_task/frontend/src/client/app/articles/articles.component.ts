import { Component } from '@angular/core';
import { Articles } from '../shared/models/articles.model';
import { ArticlesService } from '../shared/services/articles/articles.service';

/**
 * This class represents the lazy loaded ArticlesComponent.
 */
@Component({
  moduleId: module.id,
  selector: 'sd-articles',
  templateUrl: 'articles.component.html',
  styleUrls: ['../../../../node_modules/bootstrap/dist/css/bootstrap.css'],
})
export class ArticlesComponent  implements OnInit {
  articles: Articles[] = [];

  displayedColumns: string[] = ['id', 'name', 'idType', 'isActive'];
  dataSource: any;
  //clickedRows = new Set<Articles>();

  constructor(public articlesService: ArticlesService) {}

  ngOnInit() {
    this.getArticles()
  }

  getArticles() {
    this.articlesService.getArticles()
      .subscribe((_res) => {
          console.log(_res);
          this.articles = _res;
          this.dataSource = this.articles;
          console.log("dataSource: ", this.dataSource);
        }
      );
  }

}
