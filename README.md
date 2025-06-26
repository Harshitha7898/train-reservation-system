# 🚆 Train-Reservation-System

A full-stack Java web application for easy train search and ticket booking, built using *JSP*, **Servlets**, and **MySQL**. Users can register, search for trains, book tickets, and manage their bookings via a user-friendly interface.

---

## 📖 Overview

This web-based train reservation system allows users to:

* 👤 Register and log in securely
* 🔍 Search for available trains
* 🎫 Book selected trains
* 📄 View booking history
* 🔓 Log out safely

Built using *Java Servlets*, stores data in **MySQL**, and runs on **GlassFish Server** via **NetBeans IDE**.

---

## 🖥 Pages and Descriptions

### 🏠 Home Page (index.html)

➡ Clean welcome screen with navigation to login or registration.

### 🔐 Login Page (login.html)

➡ Secure login form. Redirects new users to sign-up if they don't have an account.

### 📝 Sign-Up Page (signup.html)

➡ Users register with name, email, and password. Stored in the `users` table.

### 🔍 Train Search Page (search.html)

➡ Users enter source, destination, and date to search available trains from the `trains` table.

### 🎫 Booking Page (booked.html)

➡ Displays train details with a "Book Now" action. Saves data into the `bookings` table.

### 📄 My Bookings Page (booked.html)

➡ Shows all trains booked by the user.

---

## ⚙ Tech Stack

| Layer       | Technology              |
| ----------- | ----------------------- |
| 🎨 Frontend | HTML, CSS               |
| 🧠 Backend  | Java Servlets (Jakarta) |
| 🗃 Database | MySQL                   |
| 🔥 Server   | GlassFish               |
| 💻 IDE      | NetBeans                |

---

## 🗂 Project Structure

```plaintext
train-reservation-system/
├── build/                     # Compiled build files (can be ignored)
├── nbproject/                 # NetBeans config files
│   ├── project.xml
│   ├── project.properties
│   └── private/
├── src/                       # Java Servlets
│   ├── BookNowServlet.java
│   ├── BookedTrainsServlet.java
│   ├── DatabaseConnection.java
│   ├── LoginServlet.java
│   ├── SearchTrainServlet.java
│   └── signUpServlet.java
├── web/                       # Frontend files (HTML, CSS)
│   ├── index.html
│   ├── login.html
│   ├── signup.html
│   ├── search.html
│   ├── booked.html
│   └── style.css
├── test/                      # (Optional) Unit tests
├── build.xml                  # Apache Ant build file
└── README.md                  # Project documentation
```

---

## 🚀 How to Run the Project

### 📦 Prerequisites

* Java JDK 11 or higher
* NetBeans IDE with Java EE support
* MySQL Server
* GlassFish Server
* Git (optional)

### 🛠 Setup Instructions

1. **Open in NetBeans IDE**

   * Clone or download this repository
   * Open the folder in NetBeans

2. **Configure MySQL Database**

   * Create the database:

     ```sql
     CREATE DATABASE train_reservation;
     ```

   * Create required tables:

     ```sql
     -- Users Table
     CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       email VARCHAR(100) UNIQUE,
       password VARCHAR(100)
     );

     -- Trains Table
     CREATE TABLE trains (
       train_id INT AUTO_INCREMENT PRIMARY KEY,
       train_name VARCHAR(100),
       source VARCHAR(100),
       destination VARCHAR(100),
       departure_time TIME,
       arrival_time TIME,
       travel_date DATE
     );

     -- Bookings Table
     CREATE TABLE bookings (
       booking_id INT AUTO_INCREMENT PRIMARY KEY,
       user_id INT,
       train_id INT,
       booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (user_id) REFERENCES users(id),
       FOREIGN KEY (train_id) REFERENCES trains(train_id)
     );
     ```

   * Update DB credentials in `DatabaseConnection.java`:

     ```java
     Connection conn = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/train_reservation", "root", "your-password"
     );
     ```

3. **Build and Deploy**

   * Right-click the project → Clean and Build
   * Right-click again → Run (deploys to GlassFish)

4. **Visit the Application**

   ```plaintext
   http://localhost:8080/TrainReservationSystem
   ```

---

## 🧪 User Journey

1. 🏠 Visit homepage
2. 🔐 Login or 📝 Register
3. 🔍 Search trains
4. 🎫 Book a train
5. 📄 View your bookings
6. 🔓 Logout securely

---

## 👤 Author

👩‍💻 Harshitha Bandlamudi

---

## ✨ Feedback & Contributions

If you like this project or want to enhance it:

* ⭐ Star the repository
* 🍴 Fork and improve features
* 🛠 Submit a pull request

Let’s make train booking smarter together! 🚉💻
