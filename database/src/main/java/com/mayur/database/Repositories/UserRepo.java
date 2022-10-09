package com.mayur.database.Repositories;



import com.mayur.database.Entities.WebSocketUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<WebSocketUser, String> {
}
