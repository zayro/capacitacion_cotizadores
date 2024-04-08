import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { APP_BASE_HREF } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ServiceWorkerModule } from '@angular/service-worker';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';

import { AboutModule } from './about/about.module';
import { EmpleadosModule } from './empleados/empleados.module';
import { HomeModule } from './home/home.module';
import { SharedModule } from './shared/shared.module';
import { CoreModule } from './core/core.module';


@NgModule({
  imports: [BrowserModule, CoreModule, FormsModule,
    HttpClientModule, AppRoutingModule,
    AboutModule, EmpleadosModule, HomeModule,
    SharedModule.forRoot(),
    /**
     * In order to start the Service Worker in Production located at "/ngsw-worker.js"
     * uncomment this line. More about Service Workers here
     * https://developer.mozilla.org/en-US/docs/Web/API/Service_Worker_API/Using_Service_Workers
     */
    // ServiceWorkerModule.register('ngsw-worker.js', { enabled: String('<%= BUILD_TYPE %>') === 'prod' })
  ],
  declarations: [AppComponent],
  providers: [{
    provide: APP_BASE_HREF,
    useValue: '<%= APP_BASE %>'
  }],
  bootstrap: [AppComponent]

})
export class AppModule { }
