package com.example.VPN_Clone.Controller;

import com.example.VPN_Clone.Model.User;
import com.example.VPN_Clone.Service.Impl.ConnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection")
public class ConnectionController {
    @Autowired
    ConnectionServiceImpl connectionService;

    @PostMapping("/connect")
    public ResponseEntity<Void> connect(@RequestParam int userId, @RequestParam String countryName) throws Exception{
        /*Connecting the user to a vpn by considering the following priority order.

        1. If the user is already connected to any service provider, throw "Already connected" exception.
        2. Else if the countryName is same to the original country of the user, return user as it is.
        3. Else, the user should be subscribed under a serviceProvider having option to connect to the given country.
        4. If the connection can not be made (if user does not have a serviceProvider or serviceProvider does not have given country, throw "Unable to connect" exception).
        5. At last, establish the connection where the maskedIp is "updatedCountryCode.serviceProviderId.userId" and return the updated user.
        6. If multiple service providers allows to connect to the country then using the service provider having the smallest id.*/
        User user = connectionService.connect(userId, countryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/disconnect")
    public ResponseEntity<Void> disconnect(@RequestParam int userId) throws Exception{
        /*If the given user was not connected to a vpn, throw "Already disconnected" exception.
        Else, disconnect from vpn, make masked Ip as null, update relevant attributes and return updated user.*/
        User user = connectionService.disconnect(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/communicate")
    public ResponseEntity<Void> communicate(@RequestParam int senderId, @RequestParam int receiverId) throws Exception{
        /* Establishing a connection between sender and receiver users having the following conditions
        1. To communicate to the receiver, sender should be in the current country of the receiver.
        2. If the receiver is connected to a vpn, his current country is the one he is connected to.
        3. If the receiver is not connected to vpn, his current country is his original country.
        4. If the sender's original country does not match receiver's current country, we need to connect the sender to a suitable vpn.
           if there are multiple options, then connect using the service provider having the smallest id
        5. If the sender's original country matches receiver's current country,return the sender as it is.
        6. If communication can not be established due to any reason, throw "Cannot establish communication" exception*/
        User updatedSender = connectionService.communicate(senderId, receiverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
