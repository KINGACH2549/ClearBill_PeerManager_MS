package com.clearbill.peermanager.ms.repository;

import com.clearbill.peermanager.ms.entities.FriendShip;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableCouchbaseRepositories
public interface FriendShipRepository extends CouchbaseRepository<FriendShip,String> {

    @Query("SELECT META(`friendShip`).id AS __id , friendShip.* FROM friendShip WHERE category = $1 AND createdByCustomer=$2 AND ANY c IN  customersInvolved SATISFIES c.id =$3 END")
    Optional<FriendShip> getFriendShipByCategoryAndListOfCustomers(String category ,String createdByCustomer, String customerInvolved);

    @Query("SELECT META(`friendShip`).id AS __id , friendShip.* FROM friendShip WHERE  status = $2 AND ANY c IN  customersInvolved SATISFIES c.id =$1 END")
    List<FriendShip> getFriendShipsByCustomerId(String customerId,String status);

    @Query("SELECT META(`f`).id AS __id,f.*, ARRAY_AGG(c) AS friendDetails FROM friendShip AS f LEFT JOIN customer AS c ON META(c).id IN f.customersInvolved[*].id WHERE f.status=$2 AND ANY cus IN  f.customersInvolved SATISFIES cus.id =$1 END GROUP BY f")
    List<FriendShip> getFriendShipsByCustomerIdAndStatus(String customerId,String status);

}
