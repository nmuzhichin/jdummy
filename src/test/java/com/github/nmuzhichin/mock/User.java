package com.github.nmuzhichin.mock;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;

public class User extends Principal {

    private final Long id;

    private final FullName fullName;

    private Byte age;

    private boolean active;

    private Role primaryRole;

    private List<Role> roles;

    private EnumSet<Role> roleEnumSet;

    private Map<Integer, String> classification;

    private CharSequence description;

    private Set<Long> itemsId;

    private User boss;

    private String[] zones;

    private Instant createTime;

    private ZonedDateTime zonedDateTime;

    private Date legacyDateField;

    private Timestamp lastAccessTimestamp;

    private User(Long id, FullName fullName, List<Role> roles) {
        this.id = id;
        this.fullName = fullName;
        this.roles = roles;
    }

    public User(Long id, FullName fullName) {
        this(id, fullName, null);
    }

    public Long getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public CharSequence getDescription() {
        return description;
    }

    public void setDescription(CharSequence description) {
        this.description = description;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<Integer, String> getClassification() {
        return classification;
    }

    public void setClassification(Map<Integer, String> classification) {
        this.classification = classification;
    }

    public EnumSet<Role> getRoleEnumSet() {
        return roleEnumSet;
    }

    public void setRoleEnumSet(EnumSet<Role> roleEnumSet) {
        this.roleEnumSet = roleEnumSet;
    }

    public Set<Long> getItemsId() {
        return itemsId;
    }

    public void setItemsId(Set<Long> itemsId) {
        this.itemsId = itemsId;
    }

    public Role getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(Role primaryRole) {
        this.primaryRole = primaryRole;
    }

    public User getBoss() {
        return boss;
    }

    public void setBoss(User boss) {
        this.boss = boss;
    }

    public String[] getZones() {
        return zones;
    }

    public void setZones(String[] zones) {
        this.zones = zones;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public Date getLegacyDateField() {
        return legacyDateField;
    }

    public void setLegacyDateField(Date legacyDateField) {
        this.legacyDateField = legacyDateField;
    }

    public Timestamp getLastAccessTimestamp() {
        return lastAccessTimestamp;
    }

    public void setLastAccessTimestamp(Timestamp lastAccessTimestamp) {
        this.lastAccessTimestamp = lastAccessTimestamp;
    }
}
