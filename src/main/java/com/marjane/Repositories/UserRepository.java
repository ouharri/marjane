package com.marjane.Repositories;

import com.marjane.libs.RepositoryImplementation;
import com.marjane.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends RepositoryImplementation<User> {
}
