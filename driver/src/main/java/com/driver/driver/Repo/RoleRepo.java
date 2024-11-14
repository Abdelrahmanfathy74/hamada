//package com.driver.driver.Repo;
//
//import com.driver.driver.model.RoleClass;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.Set;
//
//@Repository
//public interface RoleRepo extends JpaRepository<RoleClass, Long> {
//@Query("select r from Role r where r.name= :name")
//    Optional<Set<RoleClass>> findByName(@Param("name") String name);
//}
