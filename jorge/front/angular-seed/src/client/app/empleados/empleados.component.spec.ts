import { Component } from '@angular/core';
import { async, TestBed } from '@angular/core/testing';

import { EmpleadosModule } from './empleados.module';

export function main() {
  describe('Empleados component', () => {
    // Setting module for testing
    // Disable old forms

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TestComponent],
        imports: [EmpleadosModule]
      });
    });

    it(
      'should work',
      async(() => {
        TestBed.compileComponents().then(() => {
          const fixture = TestBed.createComponent(TestComponent);
          const aboutDOMEl = fixture.debugElement.children[0].nativeElement;

          expect(aboutDOMEl.querySelectorAll('h2')[0].textContent).toEqual(
            'Empleados'
          );
        });
      })
    );
  });
}

@Component({
  selector: 'test-cmp',
  template: '<sd-about></sd-about>'
})
class TestComponent {}
