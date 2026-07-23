# Contributing to Payment Action Project

Thank you for your interest in contributing to the **Payment Action Project**! We welcome contributions from everyone, whether you are fixing bugs, improving documentation, or adding new features.

This guide will help you set up your local development environment and submit your first Pull Request.

---

## 🚀 Getting Started

### Prerequisites
Make sure you have the following installed on your machine:
*   **Java 17** (JDK 17)
*   **Maven 3.x**
*   **Docker** (optional, for containerized run/test)

### Local Environment Setup

1.  **Fork and Clone the Repository**
    ```bash
    git clone https://github.com/your-username/deployment.git
    cd deployment
    ```

2.  **Build and Run Tests**
    Run the Maven build command to verify everything works:
    ```bash
    mvn clean test
    ```

3.  **Run the Application Locally**
    Start the Spring Boot server:
    ```bash
    mvn spring-boot:run
    ```
    The application will be accessible at: `http://localhost:8080`

4.  **Run with Docker (Optional)**
    Build and run the container locally:
    ```bash
    docker build -t payment-action .
    # Runs the container on port 8080
    docker run -d --name payment-action -p 8080:8080 payment-action
    ```

---

## 🏷️ Good First Issues

If you are looking for a place to start, here are some beginner-friendly tasks marked as **good first issue**:

### 1. Support MasterCard Payments
*   **Location**: [PaymentService.java](file:///wsl.localhost/Ubuntu/home/gurudutt/deployment/src/main/java/com/example/payment/service/PaymentService.java)
*   **Description**: Currently, the application only approves cards starting with `'4'` (Visa). Extend the logic in `PaymentService` to also approve MasterCard cards (typically starting with `'5'`).
*   **Goal**: Update the checks in `processPayment()` and add unit tests to verify MasterCard approval.

### 2. Improve Controller Unit Test Coverage
*   **Location**: `src/test/java/com/example/payment`
*   **Description**: We need robust unit testing for the REST endpoint mapping in our Controller layer.
*   **Goal**: Create a unit test class for `PaymentController` utilizing `@WebMvcTest` and MockMvc to test valid/invalid payloads to `/api/payment`.

### 3. Add Frontend Form Validation Feedback
*   **Location**: [index.html](file:///wsl.localhost/Ubuntu/home/gurudutt/deployment/src/main/resources/static/index.html)
*   **Description**: Improve the frontend UI validation when the user enters an invalid card number, empty name, or empty amount.
*   **Goal**: Show inline red validation messages under the input fields instead of alert popups or standard browser validation errors.

---

## 🤝 Contribution Process

We use a standard GitHub flow for changes:

1.  **Create a Feature Branch**
    Create a branch from `main` with a descriptive name:
    ```bash
    git checkout -b feature/my-new-feature
    ```
2.  **Commit Your Changes**
    Keep your commits focused and write descriptive commit messages:
    ```bash
    git commit -m "feat: add MasterCard support to PaymentService"
    ```
3.  **Run Tests**
    Before pushing, ensure all tests pass:
    ```bash
    mvn test
    ```
4.  **Push and Open a Pull Request**
    Push your branch and open a PR against our `main` branch:
    ```bash
    git push origin feature/my-new-feature
    ```

---

## 💬 Code Review & Feedback
*   We aim to review all pull requests within 24–48 hours.
*   Please ensure code style remains consistent with the rest of the codebase.
*   Make sure to write unit tests for any new business logic.
