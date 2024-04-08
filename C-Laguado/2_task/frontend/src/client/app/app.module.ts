import { APP_BASE_HREF } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AboutModule } from './about/about.module';
import { ArticlesModule } from './articles/articles.module';
import { CoreModule } from './core/core.module';
import { HomeModule } from './home/home.module';
import { SharedModule } from './shared/shared.module';


@NgModule({
  imports: [BrowserModule, CoreModule,
    HttpClientModule, AppRoutingModule,
    AboutModule, HomeModule, ArticlesModule,
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
