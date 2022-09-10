/*
 * package com.furnity.furnity.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import com.furnity.furnity.model.RegistrationForm; import
 * com.furnity.furnity.repository.UserRepository;
 * 
 * @Controller
 * 
 * @RequestMapping("/registration") public class RegistrationController {
 * 
 * @Autowired private PasswordEncoder passwordEncoder;
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @GetMapping public String registration(){ return "registration"; }
 * 
 * @PostMapping public String registrationProcess( RegistrationForm form ){
 * 
 * userRepository.save(form.toUser(passwordEncoder)); return "redirect:/login";
 * } }
 */