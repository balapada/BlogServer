package com.blogserver.entity;

import java.util.Objects;

public class FollowRequestBody {
    String userAccount;
    String followerAccount;

    @Override
    public String toString() {
        return "FollowRequestBody{" +
                "userAccount='" + userAccount + '\'' +
                ", followerAccount='" + followerAccount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FollowRequestBody that = (FollowRequestBody) object;
        return Objects.equals(userAccount, that.userAccount) && Objects.equals(followerAccount, that.followerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, followerAccount);
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getFollowerAccount() {
        return followerAccount;
    }

    public void setFollowerAccount(String followerAccount) {
        this.followerAccount = followerAccount;
    }
}
