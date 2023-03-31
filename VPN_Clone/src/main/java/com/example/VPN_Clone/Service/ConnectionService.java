package com.example.VPN_Clone.Service;

import com.example.VPN_Clone.Model.User;

public interface ConnectionService {
    public User connect(int userId, String countryName) throws Exception;
    public User disconnect(int userId) throws Exception ;
    public User communicate(int senderId, int receiverId) throws Exception ;
}
