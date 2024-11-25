package Models;

public class User {
    private int userID;           // User ID, primary key
    private String email;         // User's email
    private String fullName;      // User's full name
    private String password;      // User's password
    private double walletBalance; // User's wallet balance
    
    // Constructor
    public User(int userID, String email, String fullName, String password, double walletBalance) {
        this.userID = userID;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.walletBalance = walletBalance;
    }
    
    // Overloaded constructor for backward compatibility (if needed)
    public User(int userID, String email, String fullName, String password) {
        this(userID, email, fullName, password, 0.0); // Default wallet balance is 0.0
    }

    // Getter for UserID
    public int getUserID() {
        return userID;
    }

    // Setter for UserID
    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Getter for Email
    public String getEmail() {
        return email;
    }

    // Setter for Email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for FullName
    public String getFullName() {
        return fullName;
    }

    // Setter for FullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter for Password
    public String getPassword() {
        return password;
    }

    // Setter for Password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for WalletBalance
    public double getWalletBalance() {
        return walletBalance;
    }

    // Setter for WalletBalance
    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
}
