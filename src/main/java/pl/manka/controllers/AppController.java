
package pl.manka.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;

import pl.manka.app.repo.PostDomain;

import pl.manka.app.service.PostService;
import pl.manka.app.service.SessionService;
import pl.manka.app.service.Sha256;
import pl.manka.app.service.UserService;

@Controller
public class AppController {

	@Autowired
	SessionService sessionService;
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	@Autowired
	ServletContext servletContext;

	boolean xssProtect = false;

	@RequestMapping(value = "/login")
	public String loginpage(HttpServletResponse response) {
		if (xssProtect)
			response.setHeader("Content-Security-Policy", "default-src 'self'");
		return "loginPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginpage(@RequestParam("login") String login, @RequestParam("password") String password,
			HttpServletResponse response) {
		UUID uuid = UUID.randomUUID();
		String passwordHash = Sha256.sha256(password);

		if (passwordHash.equals(userService.getUser(login).getHashPassword())) {
			sessionService.add(uuid.toString());
			sessionService.setProperty(uuid.toString(), "login", login);
			sessionService.setProperty(uuid.toString(), "token", UUID.randomUUID().toString());
			Cookie cookie = new Cookie("sid", uuid.toString());
		
			
			response.addCookie(cookie);
			if (xssProtect)
				response.setHeader("Content-Security-Policy", "default-src 'self'");
			return "redirect:/post";
		} else
			return "redirect:/login";

	}

	@RequestMapping("/post")
	public String posts(Model model, @CookieValue(value = "sid", defaultValue = "") String sid,
			HttpServletResponse response) {
		if (sessionService.isLoged(sid)) {
			model.addAttribute("posts", postService.getPosts());
			model.addAttribute("token", sessionService.getProperty(sid, "token"));
			if (xssProtect)
				response.setHeader("Content-Security-Policy", "default-src 'self'");
			return "post";
		} else
			return "redirect:/login";
	}

	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String addPost(@RequestParam("content") String content,
			@RequestParam(value = "token", defaultValue = "") String token,
			@CookieValue(value = "sid", defaultValue = "") String sid, HttpServletResponse response) {
		if (sessionService.isLoged(sid)) {
			if (sessionService.getProperty(sid, "token").equals(token)) {
				PostDomain pd = new PostDomain();
				pd.setId(UUID.randomUUID().toString());
				pd.setAuthor(sessionService.getProperty(sid, "login"));
				pd.setContent(content);
				postService.addPost(pd);

				if (xssProtect)
					response.setHeader("Content-Security-Policy", "default-src 'self'");

				return "redirect:/post";
			} else {
				return "redirect:/post";
			}
		} else
			return "redirect:/login";
	}
	
	@RequestMapping(value = "/xssProtect")
	public String xssProt() {
		if( xssProtect)
			xssProtect = false;
		else
			xssProtect = true;
		
		return "redirect:/post";
		
	}
	
}
