package com.dabenxiang.security.app.authentication.openid;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.HashSet;
import java.util.Set;

/**
 * Date:2018/9/22
 * Author:gyc
 * Desc:
 */
public class OpenIdAuthenticationProvider implements AuthenticationProvider {


    private UsersConnectionRepository usersConnectionRepository;

    private SocialUserDetailsService socialUserDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenIdAuthenticationToken openIdToken = (OpenIdAuthenticationToken) authentication;

        String providerId = openIdToken.getProviderId();

        String openId = (String) openIdToken.getPrincipal();

        Set<String> providerIdSet = new HashSet<>();

        providerIdSet.add(openId);


        Set<String> userIdsConnectedTo = usersConnectionRepository.findUserIdsConnectedTo(providerId, providerIdSet);


        if(userIdsConnectedTo==null || userIdsConnectedTo.isEmpty()){
            throw  new InternalAuthenticationServiceException("找不到的绑定的的用户！");
        }



        String next = userIdsConnectedTo.iterator().next();

        SocialUserDetails socialUserDetails = socialUserDetailsService.loadUserByUserId(next);
        if(socialUserDetails==null){
            throw  new InternalAuthenticationServiceException("验证用户失败");
        }

        return new OpenIdAuthenticationToken(next, socialUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
            return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UsersConnectionRepository getUsersConnectionRepository() {
        return usersConnectionRepository;
    }

    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
        this.usersConnectionRepository = usersConnectionRepository;
    }

    public SocialUserDetailsService getSocialUserDetailsService() {
        return socialUserDetailsService;
    }

    public void setSocialUserDetailsService(SocialUserDetailsService socialUserDetailsService) {
        this.socialUserDetailsService = socialUserDetailsService;
    }
}
