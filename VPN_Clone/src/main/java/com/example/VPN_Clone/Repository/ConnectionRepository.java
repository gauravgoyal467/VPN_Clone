package com.example.VPN_Clone.Repository;

import com.example.VPN_Clone.Model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection,Integer> {
}
