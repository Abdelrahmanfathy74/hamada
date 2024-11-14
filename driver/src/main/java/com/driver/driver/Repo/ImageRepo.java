package com.driver.driver.Repo;

import com.driver.driver.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {
    @Query("select p from Image p where p.user.id= :userId")
    List<Image> findByUserId(@Param("userId")Long userId);
    @Query("SELECT i FROM Image i WHERE i.id = :imageId AND i.user.id = :userId")
    Optional<Image> findByIdAndUserId(@Param("imageId") Long imageId,@Param("userId") Long userId);
}
