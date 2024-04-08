import { Component } from '@angular/core';
import { async, TestBed } from '@angular/core/testing';

import { ArticlesModule } from './articles.module';

export function main() {
  describe('Articles component', () => {
    // Setting module for testing
    // Disable old forms

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TestComponent],
        imports: [ArticlesModule]
      });
    });

    it(
      'should work',
      async(() => {
        TestBed.compileComponents().then(() => {
          const fixture = TestBed.createComponent(TestComponent);
          const articlesDOMEl = fixture.debugElement.children[0].nativeElement;

          expect(articlesDOMEl.querySelectorAll('h2')[0].textContent).toEqual(
            'Features'
          );
        });
      })
    );
  });
}

@Component({
  selector: 'test-cmp',
  template: '<sd-articles></sd-articles>'
})
class TestComponent {}
