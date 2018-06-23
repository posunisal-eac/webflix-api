package com.jetherrodrigues.webflix.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jetherrodrigues.webflix.domain.User;
import com.jetherrodrigues.webflix.repository.UserRepository;

@Service
public class UserService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9044441985294788487L;

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	public User findById(String id) {
		return this.userRepository.findOne(id);
	}
	
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
}
