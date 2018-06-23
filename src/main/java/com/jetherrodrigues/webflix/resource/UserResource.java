package com.jetherrodrigues.webflix.resource;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jetherrodrigues.webflix.domain.User;
import com.jetherrodrigues.webflix.exceptions.WebflixUserNotFound;
import com.jetherrodrigues.webflix.service.UserService;
import static com.jetherrodrigues.webflix.util.ApiVersionUtil.*;

@RestController
@RequestMapping(value={
		REST_APP + VERSION_V1 + USER
})
public class UserResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731366882357556547L;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
				.body(this.userService.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name="id") String id) throws WebflixUserNotFound {
		User user = this.userService.findById(id);
		
		if(user == null) throw new WebflixUserNotFound("There is no image with this id!");
		
		return ResponseEntity.status(HttpStatus.OK)
				.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
				.body(user);
	}
	
	@PostMapping
	public ResponseEntity<Void> saveUser(@Valid @RequestBody User user) {
		user = this.userService.save(user);
		return ResponseEntity
				.created(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri()).build();
	}
}
