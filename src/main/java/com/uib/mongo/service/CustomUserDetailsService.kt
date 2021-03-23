package com.uib.mongo.service

import com.uib.mongo.repository.RoleRepository
import com.uib.mongo.repository.UserRepository
import com.uib.mongo.repository.entity.user.Role
import com.uib.mongo.repository.entity.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    fun findUserByName(email: String?): User? {
        return userRepository!!.findByName(email)
    }

    fun saveUser(user: User) {
        user.password = bCryptPasswordEncoder!!.encode(user.password)
        val userRole = roleRepository!!.findByRole("ADMIN")
        user.roles = HashSet<Role>(Arrays.asList(userRole))
        userRepository!!.save(user)
    }

    override fun loadUserByUsername(name: String): UserDetails {
        val user = userRepository!!.findByName(name)
        return if (user != null) {
            val authorities = getUserAuthority(user.roles)
            buildUserForAuthentication(user, authorities)
        } else {
            throw UsernameNotFoundException("username not found")
        }
    }

    private fun getUserAuthority(userRoles: Set<Role>?): List<GrantedAuthority> {
        val roles: MutableSet<GrantedAuthority> = HashSet()
        userRoles!!.forEach(Consumer { role: Role -> roles.add(SimpleGrantedAuthority(role.role)) })
        return ArrayList(roles)
    }

    private fun buildUserForAuthentication(user: User, authorities: List<GrantedAuthority>): UserDetails {
        return org.springframework.security.core.userdetails.User(user.name, user.password, authorities)
    }
}
