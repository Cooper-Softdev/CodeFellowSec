Here's a succinct README.md for your application:

# CodeFellowSec

CodeFellowship is a Spring application that allows users to create an account, log in, and manage their profile.

## Features

- **User Registration**: Users can create a new account by providing their username, password, first name, last name, date of birth, and bio.
- **User Login**: Registered users can log in to the application using their credentials.
- **Profile Management**: Logged-in users can view their profile information.
- **User Interface**: The application has a user-friendly interface with navigation links that change based on the user's login status.

## Endpoints

- `GET /login`: Displays the login page.
- `POST /login`: Handles the login form submission.
- `GET /signup`: Displays the signup page.
- `POST /signup`: Handles the signup form submission.
- `GET /`: Displays the home page.
- `GET /myprofile`: Displays the user's profile page.

## Configuration

The application is configured using the following components:

- `WebSecurityConfig`: Configures Spring Security settings and URL access permissions.
- `UserDetailsServiceImpl`: Implements the UserDetailsService to fetch user details from the database.
- `ApplicationUser`: Represents the model/entity for user accounts.
- `ApplicationUserRepo`: Provides CRUD operations for user accounts using JPA and Hibernate.

## Usage

To use the application, navigate to the signup page to create a new account. After registration, you will be automatically logged in. You can view your profile information on the profile page. To log out, click the logout link.

Please note that the application uses BCrypt for password hashing and Spring Security for user authentication and session management.