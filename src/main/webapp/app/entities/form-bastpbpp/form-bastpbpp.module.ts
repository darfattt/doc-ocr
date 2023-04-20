import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FormBASTPBPPComponent } from './list/form-bastpbpp.component';
import { FormBASTPBPPDetailComponent } from './detail/form-bastpbpp-detail.component';
import { FormBASTPBPPUpdateComponent } from './update/form-bastpbpp-update.component';
import { FormBASTPBPPDeleteDialogComponent } from './delete/form-bastpbpp-delete-dialog.component';
import { FormBASTPBPPRoutingModule } from './route/form-bastpbpp-routing.module';

@NgModule({
  imports: [SharedModule, FormBASTPBPPRoutingModule],
  declarations: [FormBASTPBPPComponent, FormBASTPBPPDetailComponent, FormBASTPBPPUpdateComponent, FormBASTPBPPDeleteDialogComponent],
})
export class FormBASTPBPPModule {}
