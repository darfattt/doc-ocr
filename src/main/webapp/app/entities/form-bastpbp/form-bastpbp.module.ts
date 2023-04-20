import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FormBASTPBPComponent } from './list/form-bastpbp.component';
import { FormBASTPBPDetailComponent } from './detail/form-bastpbp-detail.component';
import { FormBASTPBPUpdateComponent } from './update/form-bastpbp-update.component';
import { FormBASTPBPDeleteDialogComponent } from './delete/form-bastpbp-delete-dialog.component';
import { FormBASTPBPRoutingModule } from './route/form-bastpbp-routing.module';

@NgModule({
  imports: [SharedModule, FormBASTPBPRoutingModule],
  declarations: [FormBASTPBPComponent, FormBASTPBPDetailComponent, FormBASTPBPUpdateComponent, FormBASTPBPDeleteDialogComponent],
})
export class FormBASTPBPModule {}
