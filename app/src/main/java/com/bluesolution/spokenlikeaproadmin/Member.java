package com.bluesolution.spokenlikeaproadmin;

public class Member {
    public Member() {}

    public Member(String checkerEmail, String premiumCode) {
        this.checkerEmail = checkerEmail;
        this.premiumCode = premiumCode;
    }

    public String checkerEmail;
    public String premiumCode;

    public String getCheckerEmail() {
        return checkerEmail;
    }

    public void setCheckerEmail(String checkerEmail) {
        this.checkerEmail = checkerEmail;
    }

    public String getPremiumCode() {
        return premiumCode;
    }

    public void setPremiumCode(String premiumCode) {
        this.premiumCode = premiumCode;
    }
}
