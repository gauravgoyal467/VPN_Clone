package com.example.VPN_Clone.Service.Impl;

import com.example.VPN_Clone.Model.Admin;
import com.example.VPN_Clone.Model.Country;
import com.example.VPN_Clone.Model.CountryName;
import com.example.VPN_Clone.Model.ServiceProvider;
import com.example.VPN_Clone.Repository.AdminRepository;
import com.example.VPN_Clone.Repository.CountryRepository;
import com.example.VPN_Clone.Repository.ServiceProviderRepository;
import com.example.VPN_Clone.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;
    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setUsername(username);
        adminRepository1.save(admin);
        return admin;
    }
    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin = adminRepository1.findById(adminId).get();
        ServiceProvider serviceProvider = new ServiceProvider();

        serviceProvider.setName(providerName);
        serviceProvider.setAdmin(admin);


        admin.getServiceProviders().add(serviceProvider);
        adminRepository1.save(admin);

        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{

        if(countryName.equalsIgnoreCase("ind") || countryName.equalsIgnoreCase("usa") || countryName.equalsIgnoreCase("aus")||countryName.equalsIgnoreCase("jpn")||countryName.equalsIgnoreCase("chi")){

            Country country = new Country();
            ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();

            if (countryName.equalsIgnoreCase("ind")){
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
            }
            if (countryName.equalsIgnoreCase("usa")){
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
            }
            if (countryName.equalsIgnoreCase("aus")){
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
            }
            if (countryName.equalsIgnoreCase("jpn")){
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
            }
            if (countryName.equalsIgnoreCase("chi")){
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
            }
            country.setServiceProvider(serviceProvider);
            serviceProvider.getCountryList().add(country);
            serviceProviderRepository1.save(serviceProvider);

            return serviceProvider;
        }
        else
            throw new Exception("Country not found");

    }
}