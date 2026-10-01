# Shop API – Comprehensive Security Design

---

## 1. Roles and Authorities

The system implements a role-based access control (RBAC) model mapping distinct user privileges across API tiers:

* **`ROLE_ANONYMOUS`**: Unauthenticated guest visitors. Permitted to register accounts, authenticate, and browse the public product catalog.
* **`ROLE_USER`**: Authenticated customers. Granted access to manage personal profile data, modify their shopping cart, and execute checkout workflows.
* **`ROLE_ADMIN`**: System administrators. Empowered with administrative privileges to manage inventory, adjust stock levels, review global system carts, and oversee user accounts.

---

## 2. Comprehensive Access Control Matrix

| Endpoint Route Pattern | HTTP Method | Allowed Roles | Functional Purpose |
| :--- | :--- | :--- | :--- |
| `/api/public/users` | `POST` | `ROLE_ANONYMOUS`, `ROLE_USER`, `ROLE_ADMIN` | Register a new user account |
| `/api/auth/login` | `POST` | `ROLE_ANONYMOUS`, `ROLE_USER`, `ROLE_ADMIN` | Authenticate credentials and issue a JWT |
| `/api/public/products/**` | `GET` | `ROLE_ANONYMOUS`, `ROLE_USER`, `ROLE_ADMIN` | Browse products, search items, and view categories |
| `/api/public/carts/{userId}/**` | `GET`, `POST`, `PUT`, `DELETE` | `ROLE_USER` (Owner), `ROLE_ADMIN` | Manage shopping cart items, update quantities, clear cart, and calculate totals |
| `/api/admin/users/**` | `GET`, `PUT`, `DELETE` | `ROLE_ADMIN` | Administrative user retrieval, updating, and account deletion |
| `/api/admin/products/**` | `POST`, `PUT`, `DELETE` | `ROLE_ADMIN` | Full product catalog lifecycle management and stock updates |
| `/api/admin/carts` | `GET` | `ROLE_ADMIN` | Audit and review all active system carts across users |

---

## 3. Authentication vs Authorization Architecture

* **Authentication (Identity Verification)**: Determines *who* the user is. Clients transmit credentials (`email` and `password`) to `/api/auth/login`. Spring Security's `AuthenticationManager` and `DaoAuthenticationProvider` validate these against the database using configured user details services and password encoders. Upon success, a digitally signed JSON Web Token (JWT) is returned.
* **Authorization (Permission Enforcement)**: Determines *what* resources an authenticated user can touch. Enforced through the Spring Security filter chain (`SecurityFilterChain`) via HTTP request matchers, paired with method-level security (`@PreAuthorize`) to guarantee object-level ownership.

---

## 4. Password Storage and Hashing Strategy

Passwords must never be stored or transmitted in plain text.

* **Algorithm**: **BCrypt** hashing algorithm via Spring Security's `BCryptPasswordEncoder` with a strength factor of 10 or higher.
* **Database State**: The `User` entity holds a hashed string representation (e.g., `$2a$10$...`) inside its password attribute.
* **Lifecycle Flow**:
  1. **Registration**: When a client sends a raw password during user creation, the service layer encodes it via `passwordEncoder.encode(rawPassword)` before persistence.
  2. **Verification**: During login, `DaoAuthenticationProvider` automatically hashes the incoming plain-text password and compares it against the stored BCrypt hash using secure constant-time comparison via `passwordEncoder.matches()`.

---

## 5. JWT Authentication Mechanism

### Detailed Lifecycle Sequence
1. **Client -> Server**: `POST /api/auth/login` with `{ "email": "user@example.com", "password": "password123" }`
2. **Server (AuthController / AuthenticationManager)**: Authenticates credentials against database (`DaoAuthenticationProvider`).
3. **Server (JwtService)**: Generates and signs a JWT (HMAC-SHA256) containing claims (`sub`, `userId`, `roles`, `exp`, `iat`).
4. **Server -> Client**: Returns HTTP `200 OK` with JSON response `{ "token": "eyJhbGciOiJIUzI1..." }`.
5. **Client -> Server**: Requests protected endpoint `GET /api/public/carts/1` with HTTP Header `Authorization: Bearer <JWT>`.
6. **Server (JwtAuthenticationFilter)**: Intercepts request, extracts token from `Authorization` header.
7. **Server (JwtService)**: Validates cryptographic signature and checks token expiration (`exp`).
8. **Server (SecurityContextHolder)**: Extracts user identity & roles from token payload and populates `SecurityContext`.
9. **Server -> Client**: Processes controller method and returns HTTP `200 OK` with requested cart resource.


---

## 6. Comprehensive Threat Mitigation Matrix

| Threat Vector | Risk Level | Technical Mitigation Implemented in Shop API |
| :--- | :--- | :--- |
| **BOLA / IDOR** (Broken Object Level Authorization — e.g., User A accessing User B's cart endpoints) | **High** | Enforce explicit user validation. In service methods or via method security (`@PreAuthorize`), check that the authenticated principal's ID matches the target `{userId}` path variable, unless the caller holds `ROLE_ADMIN`. |
| **Man-in-the-Middle (MitM) Interception** | **High** | Require HTTPS/TLS configuration in deployment environments so that tokens, credentials, and payloads are fully encrypted in transit. |
| **Cross-Site Scripting (XSS) Token Theft** | **Medium** | Avoid exposing raw tokens to vulnerable client-side storage mechanisms where possible. For web clients, store access and refresh tokens inside `HttpOnly`, `Secure`, `SameSite=Strict` cookies to block JavaScript document access. |
| **Credential Brute-Forcing** | **Medium** | Implement rate limiting (e.g., using Bucket4j or Spring Cloud Gateway filters) on the high-frequency login endpoint (`/api/auth/login`) to block automated enumeration attacks. |
| **Token Replay and Staleness** | **Medium** | Enforce short expiration times (e.g., 15 minutes) on JWT Access Tokens, paired with a secure Refresh Token rotation flow stored safely in the database. |