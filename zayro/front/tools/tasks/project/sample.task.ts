import * as gulp from 'gulp';
import { join } from 'path';
import * as gulpLoadPlugins from 'gulp-load-plugins';

import Config from '../../config';

const plugins = <any>gulpLoadPlugins();

/**
 * This sample task copies all TypeScript files over to the appropriate `dist/dev|prod|test` directory, depending on the
 * current application environment.
 */
export = () => {
  let vulcanizeTasks:any[] = [];
  for(var source of Config.VULCANIZE_SOURCES){
     let task = gulp.src(source)
    .pipe(plugins.plumber())
    .pipe(plugins.vulcanize(Config.getPluginConfig('gulp-vulcanize')))
    .pipe(gulp.dest(Config.POLYMER_BUNDLES_DEST));
    vulcanizeTasks.push(task);
  }
  return vulcanizeTasks;
};
