import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhipsterMicronautSharedModule } from 'app/shared/shared.module';

import { ConfigurationComponent } from './configuration.component';

import { configurationRoute } from './configuration.route';

import { KeyStartsWith } from './configuration.filter';

@NgModule({
  imports: [JhipsterMicronautSharedModule, RouterModule.forChild([configurationRoute])],
  declarations: [ConfigurationComponent, KeyStartsWith]
})
export class ConfigurationModule {}
