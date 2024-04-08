import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Articles } from '../../models/articles.model';

/**
 * This class provides the Articles service with methods to read names and add names.
 */
@Injectable({ providedIn: 'root' })
export class ArticlesService {

  /**
   * Creates a new ArticlesService with the injected HttpClient.
   * @param {HttpClient} http - The injected HttpClient.
   * @constructor
   */
  constructor(private http: HttpClient) {}


  getArticles(): Observable<Articles> {
    return this.http.get<Articles>("http://localhost:9000/query/articles");
  }

}

