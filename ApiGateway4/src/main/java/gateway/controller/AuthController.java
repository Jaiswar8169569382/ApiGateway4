package gateway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user , Model model
			)
	{
		AuthResponse auth = new AuthResponse();
		auth.setUserId(user.getEmail());
		auth.setAccessToken(client.getAccessToken().getTokenValue());
		auth.setRefreshToken(client.getRefreshToken().getTokenValue());
		auth.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		List<String> authorities=user.getAuthorities().stream().map(grantedAuthority -> {
			return grantedAuthority.getAuthority();
		}).collect(Collectors.toList());
		
		
		auth.setAuthorities(authorities);
		return new ResponseEntity<AuthResponse>(auth,HttpStatus.OK);
	}
}
