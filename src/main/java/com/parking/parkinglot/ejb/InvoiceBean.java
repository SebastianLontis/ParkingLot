package com.parking.parkinglot.ejb;


import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Stateful
@SessionScoped
public class InvoiceBean implements Serializable {

    Set<Long> UserIds = new HashSet<>();

    public Set<Long> getUserIds() {
        return UserIds;
    }

    public void setUserIds(Set<Long> userIds) {
        UserIds = userIds;
    }
}
