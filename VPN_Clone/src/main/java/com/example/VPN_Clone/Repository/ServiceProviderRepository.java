package com.example.VPN_Clone.Repository;

import com.example.VPN_Clone.Model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Integer> {
}
