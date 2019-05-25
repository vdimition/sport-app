package com.sport.app.controller.application_user;

import com.backend.controller.MessageResponse;
import com.backend.exception.NotFoundException;
import com.backend.repository.entity.ApplicationUser;
import com.backend.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("application/user/")
public class ApplicationUserController {

    private ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/save")
    public MessageResponse saveApplicationUser(@RequestBody ApplicationUserDTO dto) {
        applicationUserService.saveUser(new ApplicationUser(dto));
        return new MessageResponse("saved");
    }

    @GetMapping
    public ApplicationUserDTO getApplicationUserByID(@RequestParam int id) {
        ApplicationUser user = applicationUserService.getApplicationUserById(id).orElseThrow(
                () -> new NotFoundException("No such user"));
        return new ApplicationUserDTO(user);
    }

}