package com.example.VPN_Clone.Controller;

import com.example.VPN_Clone.Model.User;
import com.example.VPN_Clone.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String countryName) throws Exception{
        /* creating a user of given country. The originalIp of the user will be "countryCode.userId" and return the user.
        since the user is not connected to any vpn so connected would be false and maskedIp would be null*/
        User user = userService.register(username, password, countryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/subscribe")
    public void subscribe(@RequestParam Integer userId, @RequestParam Integer serviceProviderId){
        /*subscribing to the serviceProvider by adding it to the list of providers and return updated User*/
        User user = userService.subscribe(userId, serviceProviderId);
    }
}
