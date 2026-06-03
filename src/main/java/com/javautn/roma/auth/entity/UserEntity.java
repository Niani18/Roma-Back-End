package com.javautn.roma.auth.entity;

import com.javautn.roma.human.entity.CitizenEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16, columnDefinition = "varchar(16) default 'ACTIVE'")
    private UserState state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id", unique = true, nullable = true)
    private CitizenEntity citizen;

    protected UserEntity() {}

    public UserEntity(String username, String password, Role role,  CitizenEntity citizen) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.citizen = citizen;
        this.state = calculateState(role, citizen);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        syncStateWithCitizenLife();
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public CitizenEntity getCitizen() {
        return citizen;
    }

    public void setCitizen(CitizenEntity citizen) {
        this.citizen = citizen;
        syncStateWithCitizenLife();
    }

    public Long getCitizenId() {
        return citizen == null ? null : citizen.getId();
    }

    public boolean isActive() {
        return calculateState(role, citizen) == UserState.ACTIVE;
    }

    public boolean syncStateWithCitizenLife() {
        UserState updatedState = calculateState(role, citizen);
        if (state != updatedState) {
            state = updatedState;
            return true;
        }
        return false;
    }

    private static UserState calculateState(Role role, CitizenEntity citizen) {
        if (role == Role.ADMIN) {
            return UserState.ACTIVE;
        }
        if (citizen == null || citizen.getDeathDate() != null) {
            return UserState.INACTIVE;
        }
        return UserState.ACTIVE;
    }
}
