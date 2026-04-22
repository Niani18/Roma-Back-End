package com.javautn.roma.human.entity;

public class Citizen extends Human {
    private String socialRole;

    @Override
    public String toString() {
        return "Citizen={ id" + super.id +
                " fullName='" + super.fullName + "'" +
                " birthDate='" + super.birthDate.toString() + "'" +
                " deathDate='" + super.deathDate.toString() + "'" +
                " socialRole=" + this.socialRole + " }";
    }

    @Override
    public boolean equals(final Object B) {
        return (B instanceof Citizen cit &&
                super.equals(B) &&
                this.socialRole.equals(cit.socialRole)
        );
    }

    public String getSocialRole() {
        return socialRole;
    }

    public void setSocialRole(String socialRole) {
        this.socialRole = socialRole;
    }
}
