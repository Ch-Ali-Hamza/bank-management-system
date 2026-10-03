# 🏦 Bank Management System

A robust, console-based **Bank Management System** developed in Java. Built as a **Programming Fundamentals Semester Project**, this application features role-based access control (Admin and User portals), persistent file-based storage, fund transfer capabilities, transaction logging, and real-time account management.

---

## 📋 Table of Contents
- [Features](#-features)
- [System Architecture](#-system-architecture)
- [Tech Stack & Prerequisites](#-tech-stack--prerequisites)
- [Installation & Execution](#-installation--execution)
- [Default Admin Credentials](#-default-admin-credentials)
- [Project Structure](#-project-structure)
- [Documentation & Attachments](#-documentation--attachments)
- [License](#-license)

---

## ✨ Features

### 🔑 Role-Based Access Control
The application separates administrative actions from standard banking customer operations through distinct login menus.

#### 🛠️ Admin Portal
- **Create Account**: Register new bank accounts with name validation, age verification (18–100 years), unique numerical ID checking, and initial deposit setup.
- **View All Accounts**: Display a full roster of registered bank accounts with current balances and account details.
- **Update Account**: Modify account details (name, age, balance) while maintaining account ID integrity.
- **Search Account**: Instantly retrieve account information using unique Account IDs.
- **Transfer Funds**: Atomically transfer funds between any two valid bank accounts with balance checks and receipt logging.
- **View Transaction Logs**: Review complete timestamped audit logs of all monetary transfers.

#### 👤 User Portal
- **User Authentication**: Secure login requiring account holder's registered name and Account ID.
- **View Account Details**: Inspect personal profile information (Name, Age, ID, Balance).
- **View Balance**: Quick account balance inquiry.

---

## 🏗️ System Architecture & Data Storage

The application leverages Java File I/O for persistent data management:
- **`accounts.txt`**: Master database file storing CSV-formatted account records (`Name,Age,ID,Balance`).
- **`transactions.txt`**: Audit log recording timestamped fund transfers.
- **`<UserName>.txt`**: Individual user profile data file created upon account registration.

---

## 🛠️ Tech Stack & Prerequisites

- **Language**: Java (JDK 8 or higher)
- **Paradigm**: Object-Oriented Programming (OOP) & Procedural Logic
- **Storage**: Flat File System (`java.io.*`)
- **UI**: Interactive Command Line Interface (CLI)

---

## 🚀 Installation & Execution

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/bank-management-system.git
cd bank-management-system
```

### 2. Compile the Source Code
```bash
javac bankSystem.java
```

### 3. Run the Application
```bash
java bankSystem
```

---

## 🔐 Default Admin Credentials

For testing and demonstration, use the pre-configured admin credentials:

| Field | Default Value |
| :--- | :--- |
| **Admin Name** | `Admin` |
| **Password** | `Admin1234` |

---

## 📁 Project Structure

```
.
├── bankSystem.java              # Main Java source code containing system logic
├── BANK MANAGEMENT SYSTEM.pptx  # Project Presentation Slides
├── Bank Management System.docx  # Detailed Project Report & Documentation
├── PF_SEMESTER_PROJECT.pdf      # Semester Project Specification & Requirements
├── .gitignore                   # Excludes build files, dynamic logs, & IDE configs
└── README.md                    # Project documentation
```

---

## 📄 Documentation & Attachments

This repository includes full academic project documentation:
- 📊 **Presentation**: `BANK MANAGEMENT SYSTEM.pptx`
- 📑 **Report**: `Bank Management System.docx`
- 📌 **Specification**: `PF_SEMESTER_PROJECT.pdf`

---

## 👨‍💻 Author

Developed for **Programming Fundamentals (PF) Semester Project**.
