package design.construction.madang.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import design.construction.madang.common.BaseController;

@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/googleLogin", method = {RequestMethod.GET, RequestMethod.POST } )
	public String googleLogin(Model model, HttpSession session) {
		logger.info("========================================= google login requested ========================================= ");
		/* google code generate */
		OAuth2Operations auth2Operations = this.googleConnectionFactory.getOAuthOperations();
		String url = auth2Operations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, this.googleOAuth2Parameters);
		model.addAttribute("google_url", url);
		return "googleLogin";
	}
	
	@RequestMapping(value = "/googleLoginSuccess", method = {RequestMethod.GET, RequestMethod.POST } )
	public String googleLoginSuccess(Model model, @RequestParam String code) {
		logger.info("call googleSuccess");
		return "googleLoginSuccess";
	}
	
	

}
