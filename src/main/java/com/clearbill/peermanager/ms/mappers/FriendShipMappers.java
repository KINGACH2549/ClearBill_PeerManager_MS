package com.clearbill.peermanager.ms.mappers;

import com.clearbill.peermanager.ms.dto.frienship.FriendShipResponseDTO;
import com.clearbill.peermanager.ms.dto.frienship.ListOfFriendShipResponseDTO;
import com.clearbill.peermanager.ms.entities.FriendShip;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendShipMappers {

    @Autowired
    ModelMapper modelMapper;
//
//    public FriendShipMappers(){
//        modelMapper.getConfiguration()
//                .setFieldMatchingEnabled(true)
//                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
//    }

    public FriendShipResponseDTO FriendShipEntityToFriendShipResponse(FriendShip friendShip){
        return modelMapper.map(friendShip,FriendShipResponseDTO.class);

    }

    public List<ListOfFriendShipResponseDTO> ListOfFriendShipEntityToFriendShipResponse(List<FriendShip> friendShips){

        return modelMapper.map(friendShips, new TypeToken<List<ListOfFriendShipResponseDTO>>() {}.getType());
    }

}
