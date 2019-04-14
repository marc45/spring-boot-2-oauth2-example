package com.example.oauthexample.configure;

import com.example.oauthexample.oauth.SparklrUserApprovalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class UserApprovalConfigure {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Bean
    public UserApprovalHandler userApprovalHandler() {
        TokenApprovalStore approvalStore = new TokenApprovalStore();
        approvalStore.setTokenStore(tokenStore);

        SparklrUserApprovalHandler handler = new SparklrUserApprovalHandler();
        handler.setApprovalStore(approvalStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        handler.setUseApprovalStore(true);

        return handler;
    }

}
