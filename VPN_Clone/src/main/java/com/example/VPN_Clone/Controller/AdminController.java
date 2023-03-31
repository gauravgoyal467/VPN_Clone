package com.example.VPN_Clone.Controller;

import com.example.VPN_Clone.Model.Admin;
import com.example.VPN_Clone.Model.ServiceProvider;
import com.example.VPN_Clone.Service.Impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerAdmin(@RequestParam String username, @RequestParam String password){
        //creating an admin and return
        Admin admin = adminService.register(username, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addProvider")
    public ResponseEntity<Void> addServiceProvider(@RequestParam int adminId, @RequestParam String providerName){
        //Adding a serviceProvider under the admin and return updated admin
        Admin admin = adminService.addServiceProvider(adminId, providerName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addCountry")
    public ResponseEntity<Void> addCountry(@RequestParam int serviceProviderId, @RequestParam String countryName) throws Exception{
        /*Adding a country under the serviceProvider and return the respective service provider
        Country name would be a 3-character string out of ind, aus, usa, chi, jpn.
        so creating a new Country object based on the given country name and adding it to the country list of the service provider.
        In case country name is not amongst the above-mentioned strings, throw "Country not found" exception */
        ServiceProvider serviceProvider = adminService.addCountry(serviceProviderId, countryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
