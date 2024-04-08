import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed, async } from '@angular/core/testing';
import { ArticlesService } from './articles.service';

import { Observable } from 'rxjs';

export function main() {
  describe('Articles Service', () => {

    let articlesService: ArticlesService;
    let httpMock: HttpTestingController;

    beforeEach(() => {

      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [ArticlesService]
      });

      articlesService = TestBed.get(ArticlesService);
      httpMock = TestBed.get(HttpTestingController);
    });

    it('should return an Observable when get called', async(() => {
      expect(TestBed.get(ArticlesService).get()).toEqual(jasmine.any(Observable));
    }));

    it('should resolve to list of articles when get called', async(() => {

    }));
  });
}
