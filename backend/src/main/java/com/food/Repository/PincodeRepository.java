package com.food.Repository;

import com.food.Entities.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PincodeRepository extends JpaRepository<Pincode,Integer>
{

    Optional<Pincode> findByPincodeNumber(String pincodeNumber);
}
