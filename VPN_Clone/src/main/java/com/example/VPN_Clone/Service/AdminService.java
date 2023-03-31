package com.example.VPN_Clone.Service;


import com.example.VPN_Clone.Model.Admin;
import com.example.VPN_Clone.Model.ServiceProvider;

public interface AdminService {
    public Admin register(String username, String password);

    public Admin addServiceProvider(int adminId, String providerName);

    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception;
}
