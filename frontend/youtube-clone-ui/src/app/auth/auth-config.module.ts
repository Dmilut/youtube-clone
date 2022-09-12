import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-p06nzbvu.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'SYsR4uU0Wr6k17N36BIok1uvJ4tYvLVD',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
