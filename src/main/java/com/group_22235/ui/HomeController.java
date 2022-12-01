package com.group_22235.ui;

import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.group_22235.user.UserService;
import com.group_22235.user.UserDto;
import com.group_22235.user.User;
import com.group_22235.generics.ABaseServiceImpl;
import com.group_22235.services_management.Train;
import com.group_22235.services_management.TrainController;
import com.group_22235.services_management.TrainService;




@Controller
public class HomeController {
    private UserService userService;
    private TrainService trainService;
    private TrainController trainController;


    public HomeController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/BookTicket")
    public String bookTicket() {
        return "BookTicket";
    }

    @GetMapping("/Index")
    public String index() {
        return "Index";
    }

    @GetMapping("/IndexUser")
    public String indexUser() {
        return "IndexUser";
    }

    // @GetMapping("/Admin")
    // public String admin() {
    //     return "Admin";
    // }

    @GetMapping("/Admin")
    public String add(Model model) throws Exception {
        System.out.println("TESTINGGG");
        //List<Train> listTrain = trainController.getAll();
        //model.addAttribute("listTrain", listTrain);
        //model.addAttribute("train", new Train());
        return "Admin";
    }

    // @RequestMapping("/edit/{trainid}")
    // public ModelAndView showEditTrainPage(@PathVariable(name="id") int id) {
    //     ModelAndView mav = new ModelAndView("new");
    //     TrainController trainID = trainController.getByID(id);
    //     mav.addObject("train", trainID);
    //     return mav;
    // }

    @GetMapping("/LoginTest")
    public String loginTest() {
        return "LoginTest";
    }
}