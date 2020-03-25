package com.kzadha.service.impl;

import com.kzadha.dao.UserDAO;
import com.kzadha.mapper.UserMapper;
import com.kzadha.model.User;
import com.kzadha.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private static final String ACTIVE_STATUS = "ACTIVE";
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private static final String GOOGLE_CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserDAO userDao;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public void registerUser(User user) {
        logger.info("Creating user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(mapper.toEntity(user));


    }


    @Override
    public boolean isUserExists(String email) {
        return userDao.findByEmail(email)!=null;
    }


}
