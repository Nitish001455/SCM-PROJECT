package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")

    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handler");
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("YoutubeChannel","Learn code with durgesh");
        model.addAttribute("githubRepo", "http:/github.com/learncodewithdurgesh/");
        return "home";


    }

    // about route
    @RequestMapping("/about")
   public String aboutPage(Model model){
    model.addAttribute("isLogin", true);
    System.out.println("About page loading");
    return "about";
   }
    

    // services

    @RequestMapping("/services")
   public String servicesPage(){
    System.out.println("services page loading");
    return "services";
   }

   // contact page

   @GetMapping("/contact")
   public String contact() {
       return"contact";
   }

   @GetMapping("/login")
   public String login() {
       return "login";
   }

   @GetMapping("/register")
   public String register(Model model) {
    UserForm userForm = new UserForm();
   
    model.addAttribute("userForm", userForm);
       return "register";
   }
   
   // processing register

   @RequestMapping(value = "/do-register",method = RequestMethod.POST)
   public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
    System.out.println("Processing registration");
  
    //fetch the data
    //userform
     System.out.println(userForm);

    // validation form data
    if (rBindingResult.hasErrors()) {
        return "register";
        
    }

    //TODO::Validate userform[next part]

    //save to database


    // //userservice
    // User user=User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .password(userForm.getPassword())
    // .about(userForm.getAbout())
    // .phoneNumber(userForm.getPhoneNumber())
    // .prifilePic(null)
    // .build();

    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setPrifilePic("file:/C:/Users/NITISH/Downloads/pexels-soldiervip-1374510.jpg");

    User savedUser=userService.saveUser(user);
    System.out.println("user saved :");

    // message = "registration success"

    //add the message:
    Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
    session.setAttribute("message", message);



    return "redirect:/register";
   }
   
   
    
}
