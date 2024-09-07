package com.clearbill.peermanager.ms.controllers;

import com.clearbill.peermanager.ms.dto.frienship.FriendShipRequestDTO;
import com.clearbill.peermanager.ms.dto.frienship.FriendShipResponseDTO;
import com.clearbill.peermanager.ms.dto.frienship.ListOfFriendShipResponseDTO;
import com.clearbill.peermanager.ms.services.ManageFriendShipServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/friendshipManagement/manageFriendShip")
public class ManageFriendShipController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManageFriendShipController.class);

    @Autowired
    private ManageFriendShipServices manageFriendShipServices;

    @PostMapping
    public ResponseEntity<FriendShipResponseDTO> addFriend(@RequestBody @Valid FriendShipRequestDTO friendShipRequestDTO) {
        LOGGER.info("Request Received to add a Customer");
        LOGGER.trace(friendShipRequestDTO.toString());

        FriendShipResponseDTO responseDTO = manageFriendShipServices.addFriend(friendShipRequestDTO);

        LOGGER.info("FriendShip Created!!!!");
        LOGGER.trace(responseDTO.toString());

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{friendShipId}")
    public ResponseEntity<FriendShipResponseDTO> updateFriendShip(@RequestBody @Valid FriendShipRequestDTO friendShipRequestDTO,@PathVariable String friendShipId) {
        LOGGER.info("Request Received to update FriendShip: "+friendShipId);
        LOGGER.trace(friendShipRequestDTO.toString());

        FriendShipResponseDTO responseDTO = manageFriendShipServices.updateFriendShip(friendShipRequestDTO,friendShipId);

        LOGGER.info("FriendShip Updated!!!!");
        LOGGER.trace(responseDTO.toString());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping({"/{friendShipId}"})
    public ResponseEntity<List<ListOfFriendShipResponseDTO>> getFriendShip(@PathVariable String friendShipId) {
        LOGGER.info("Request Received to get FriendShip By Id: "+friendShipId);

        List<ListOfFriendShipResponseDTO> responseDTO = manageFriendShipServices.getFriendShipById(friendShipId);

        LOGGER.info("FriendShip Response!!!!");
        LOGGER.trace(responseDTO.toString());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/query")
    public ResponseEntity<List<ListOfFriendShipResponseDTO>> getFriendShipByCustomerId(@RequestParam HashMap<String,Object> requestParams){

        LOGGER.info("Request Received to get FriendShip By: "+requestParams.toString());

        List<ListOfFriendShipResponseDTO> responseDTO = manageFriendShipServices.getFriendShipByIdByCustomerId(requestParams);

        LOGGER.info("FriendShip Response!!!!");
        LOGGER.trace(responseDTO.toString());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
