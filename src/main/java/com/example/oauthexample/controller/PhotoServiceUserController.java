package com.example.oauthexample.controller;
import java.security.Principal;

import com.example.oauthexample.bean.PhotoServiceUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Michael Lavelle
 * 
 * Added to provide an endpoint from which Spring Social can obtain authentication details
 */
@RequestMapping("/me")
@Controller
public class PhotoServiceUserController {
	
	@ResponseBody
	@RequestMapping("")
	public PhotoServiceUser getPhotoServiceUser(Principal principal)
	{
		return new PhotoServiceUser(principal.getName(),principal.getName());
	}
}
