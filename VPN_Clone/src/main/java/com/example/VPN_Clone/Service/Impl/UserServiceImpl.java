package com.example.VPN_Clone.Service.Impl;

import com.example.VPN_Clone.Model.Country;
import com.example.VPN_Clone.Model.CountryName;
import com.example.VPN_Clone.Model.ServiceProvider;
import com.example.VPN_Clone.Model.User;
import com.example.VPN_Clone.Repository.CountryRepository;
import com.example.VPN_Clone.Repository.ServiceProviderRepository;
import com.example.VPN_Clone.Repository.UserRepository;
import com.example.VPN_Clone.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        if(countryName.equalsIgnoreCase("ind") || countryName.equalsIgnoreCase("usa") || countryName.equalsIgnoreCase("aus")||countryName.equalsIgnoreCase("jpn")||countryName.equalsIgnoreCase("chi")) {

            User user = new User();
            user.setPassword(password);
            user.setUsername(username);

            Country country = new Country();

            if (countryName.equalsIgnoreCase("ind")) {
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
            }
            if (countryName.equalsIgnoreCase("usa")) {
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
            }
            if (countryName.equalsIgnoreCase("aus")) {
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
            }
            if (countryName.equalsIgnoreCase("jpn")) {
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
            }
            if (countryName.equalsIgnoreCase("chi")) {
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
            }

            country.setUser(user);
            user.setOriginalCountry(country);
            user.setConnected(false);

            String IP = country.getCode() +"."+ userRepository3.save(user).getId();
            user.setOriginalIp(IP);

            userRepository3.save(user);
            return user;
        }
        else
            throw new Exception("Country not found");
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        User user = userRepository3.findById(userId).get();
        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).get();

        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);

        serviceProviderRepository3.save(serviceProvider);
        return user;
    }
}