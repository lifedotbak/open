package com.spyker.iot.vmp.service.impl;

import com.spyker.iot.vmp.service.IUserService;
import com.spyker.iot.vmp.storager.dao.UserMapper;
import com.spyker.iot.vmp.storager.dao.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired private UserMapper userMapper;

    @Override
    public User getUser(String username, String password) {
        return userMapper.select(username, password);
    }

    @Override
    public boolean changePassword(int id, String password) {
        User user = userMapper.selectById(id);
        user.setPassword(password);
        return userMapper.update(user) > 0;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public int addUser(User user) {
        User userByUsername = userMapper.getUserByUsername(user.getUsername());
        if (userByUsername != null) {
            return 0;
        }
        return userMapper.add(user);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public int updateUsers(User user) {
        return userMapper.update(user);
    }

    @Override
    public boolean checkPushAuthority(String callId, String sign) {

        List<User> users = userMapper.getUsers();
        if (users.size() == 0) {
            return false;
        }
        for (User user : users) {
            if (user.getPushKey() == null) {
                continue;
            }
            String checkStr =
                    callId == null ? user.getPushKey() : (callId + "_" + user.getPushKey());
            String checkSign = DigestUtils.md5DigestAsHex(checkStr.getBytes());
            if (checkSign.equals(sign)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int changePushKey(int id, String pushKey) {
        return userMapper.changePushKey(id, pushKey);
    }
}