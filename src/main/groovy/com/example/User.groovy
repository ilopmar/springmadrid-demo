package com.example

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@ToString
class User {

    @Id
    @GeneratedValue
    Long id

    String name
}
