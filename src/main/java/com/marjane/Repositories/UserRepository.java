package com.marjane.Repositories;

import com.marjane.libs.RepositoryImplementation;
import com.marjane.models.Person;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends RepositoryImplementation<Person> {
}