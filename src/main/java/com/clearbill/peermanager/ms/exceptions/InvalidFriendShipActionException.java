package com.clearbill.peermanager.ms.exceptions;

import org.apache.coyote.BadRequestException;

public class InvalidFriendShipActionException extends RuntimeException {

    public InvalidFriendShipActionException(String message) {
        super(message);
    }


}
