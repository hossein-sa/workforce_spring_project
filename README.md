# 🏠 Home Services Management System

## 📌 Project Overview
The **Home Services Management System** is a platform where **customers** can request home services, **specialists** can offer their expertise, and **admins** can manage the system. The platform includes **order management, specialist approval, reviews, payments, and authentication**.

---

## 🚀 Features

### 🔹 **For Customers**
- ✅ Register and log in to the system.
- ✅ Browse available services.
- ✅ Place an **order** for a specific **sub-service**.
- ✅ View **proposals** submitted by specialists.
- ✅ Select a specialist for the job.
- ✅ Make **payments** (online or wallet balance).
- ✅ Leave **reviews & ratings** for specialists.

### 🔹 **For Specialists**
- ✅ Register and log in to the system.
- ✅ Complete **profile verification** and wait for admin approval.
- ✅ Select **fields of expertise** (sub-services).
- ✅ View **available orders** in their expertise.
- ✅ Submit **proposals** (offer price & duration).
- ✅ Start and complete orders.
- ✅ Receive **payments** (70% of order price).
- ✅ View received **reviews** and maintain rating.

### 🔹 **For Admins**
- ✅ Manage **users (Customers, Specialists, Admins)**.
- ✅ Approve or reject **specialist registrations**.
- ✅ Manage **orders and payments**.
- ✅ View and manage **customer & specialist accounts**.
- ✅ Filter & search through user lists.
- ✅ **Suspend specialists** with a rating below 3.5.

---

## 📌 Technologies Used
- **Java 17** (Backend)
- **Spring Boot** (Framework)
- **Spring Security & JWT** (Authentication)
- **Hibernate & JPA** (ORM)
- **PostgreSQL** (Database)
- **Lombok** (Boilerplate reduction)
- **Maven** (Dependency Management)
- **Postman** (API Testing)

---

## 📌 API Endpoints

### 🔹 **Authentication**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/auth/register` | Register new users (Customer/Specialist) |
| `POST` | `/api/auth/login` | User login & JWT generation |

---

### 🔹 **Customer Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/orders` | Place an order |
| `GET` | `/api/orders/{orderId}/proposals` | View proposals for an order |
| `POST` | `/api/orders/{orderId}/select-proposal/{proposalId}` | Select a specialist's proposal |
| `POST` | `/api/orders/{orderId}/pay` | Make a payment for an order |
| `POST` | `/api/orders/{orderId}/review` | Submit a review for a specialist |

---

### 🔹 **Specialist Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/specialist/{id}/add-services` | Add expertise (sub-services) |
| `GET` | `/api/specialist/orders` | View available orders |
| `POST` | `/api/orders/{orderId}/submit-proposal` | Submit a proposal for an order |
| `PUT` | `/api/orders/{orderId}/start` | Start an order |
| `PUT` | `/api/orders/{orderId}/complete` | Mark order as completed |
| `GET` | `/api/specialist/{specialistId}/reviews` | View received reviews |

---

### 🔹 **Admin Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/api/admin/users` | Get all users (Customers & Specialists) |
| `PUT` | `/api/admin/specialist/status/{specialistId}` | Change specialist status |
| `GET` | `/api/admin/orders` | View all orders |
| `GET` | `/api/admin/payments` | View payment transactions |

---

## 📌 Setup & Run Instructions

### 🔹 **1️⃣ Clone Repository**
```sh
git clone https://github.com/your-repo/home-services.git
cd home-services
```

### 🔹 **2️⃣ Configure Database**
- Update `application.properties` in `src/main/resources/`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/home_services
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 🔹 **3️⃣ Run the Application**
```sh
mvn spring-boot:run
```

### 🔹 **4️⃣ API Testing with Postman**
- Import the Postman collection provided in `docs/Postman_Collection.json`.
- Use **JWT Authentication** for secured endpoints.

---

## 📌 Specialist Rating System
- Specialists have a **rating** based on customer reviews.
- **If their rating drops below 3.5**, they are **moved to PENDING_APPROVAL**.
- **Pending specialists cannot see orders** until **admin re-approves** them.

---

## 📌 Payment System
- Customers must have **sufficient balance** in their account.
- **70% of the order price** is transferred to the **specialist's balance**.
- Payment records are stored in the **Payment table**.

---

## 📌 Future Improvements
- ✅ Add **email notifications** for status updates.
- ✅ Implement **search & filter** for orders.
- ✅ Add **real-time chat** between customers and specialists.

---

## 💡 Contributors
- 👤 **Hossein Sadeghi** - Developer

---

## 📝 License
This project is **open-source** and available under the **MIT License**.
