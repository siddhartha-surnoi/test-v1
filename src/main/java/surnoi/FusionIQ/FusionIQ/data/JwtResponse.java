
package surnoi.FusionIQ.FusionIQ.data;
public class JwtResponse {
    private String token;
    private SimplifiedUserDetails userDetails;
    // Constructors,
    public JwtResponse(String token, SimplifiedUserDetails userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    // getters, and setters

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public SimplifiedUserDetails getUserDetails() {
        return userDetails;
    }
    public void setUserDetails(SimplifiedUserDetails userDetails) {
        this.userDetails = userDetails;
    }
}