package com.vn.rbk.repository.impl;

import com.mongodb.BasicDBObject;
import com.vn.rbk.MongoConfig;
import com.vn.rbk.model.UserDTO;
import com.vn.rbk.repository.MongoManager;
import com.vn.rbk.repository.base.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Slf4j
public class UserRepoImpl implements UserRepo {
    public static MongoManager mongo = new MongoManager();
    @Autowired
    MongoConfig mongoConfig;

    @Override
    public boolean login(UserDTO userDTO) {
        try {
            BasicDBObject document = new BasicDBObject();
            document.put("userid", userDTO.getUserId());
            document.put("email", userDTO.getEmail());
            document.put("displayname", userDTO.getDisplayName());
            document.put("accesstoken", userDTO.getAccessToken());
            Date now = new Date();
            document.put("timeCreated", now.getTime());
            int count = mongo.user(mongoConfig).find(new BasicDBObject("userid", userDTO.getUserId())).count();
            if (count < 1) {
                mongo.user(mongoConfig).insert(document);
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
