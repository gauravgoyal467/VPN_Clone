package com.example.VPN_Clone.Service;

import com.example.VPN_Clone.Model.User;

public interface UserService {

    public User register(String username, String password, String countryName) throws Exception;

    public User subscribe(Integer userId, Integer serviceProviderId);
}
