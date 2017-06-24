import {StoreDevtoolsModule} from '@ngrx/store-devtools';
export const environment: {production: boolean, endpoint: string,imports: any[]} = {
  production: false,
  endpoint: 'http://localhost:8080',
  imports: [
    // no op
  ],
};
