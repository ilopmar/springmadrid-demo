package com.example

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

@SpringBootApplication
class DemoApplication {

    static void main(String[] args) {
        SpringApplication.run DemoApplication, args
    }

    @Bean
    CommandLineRunner runner(UserRepository userRepository) {
        { args ->
            ['Howard', 'Leonard', 'Raj', 'Sheldon'].each { n ->
                userRepository.save(new User(name: n))
            }

            userRepository.findAll().each { println it }

            println userRepository.findAllByName('Raj')

        }
    }

    @Bean
    HealthIndicator springMadrid() {
        { ->
            Health.status('Hola Spring Madrid :)')
                .withDetail('timestamp', System.currentTimeMillis())
                .build()
        }
    }
}

@RepositoryRestResource
interface UserRepository extends JpaRepository<User, Long> {

    // select * from user where name = :n
    @RestResource(path = 'by-name')
    Collection<User> findAllByName(@Param('name') String n)

}




//@RestController
//class UserRestController {
//
//    @Autowired
//    UserRepository userRepository
//
//    @RequestMapping(path = '/users')
//    Collection<User> users() {
//        userRepository.findAll()
//    }
//}


















