package com.clearbill.peermanager.ms.services;

import com.clearbill.peermanager.ms.dto.frienship.FriendShipRequestDTO;
import com.clearbill.peermanager.ms.dto.frienship.FriendShipResponseDTO;
import com.clearbill.peermanager.ms.dto.frienship.ListOfFriendShipResponseDTO;

import java.util.HashMap;
import java.util.List;

public interface ManageFriendShipServices {

     FriendShipResponseDTO addFriend(FriendShipRequestDTO friendShipRequestDTO);
     FriendShipResponseDTO updateFriendShip(FriendShipRequestDTO friendShipRequestDTO,String friendShipId);
     List<ListOfFriendShipResponseDTO> getFriendShipById(String friendShipId);
     List<ListOfFriendShipResponseDTO> getFriendShipByIdByCustomerId(HashMap<String,Object> requestParams);
}
