# 🛍️ NCM Product Search Platform

A simple Spring MVC-based eCommerce web application where users can search for products and view nearby shops offering those products, sorted by proximity. Shopkeepers can register, add their products, and manage shop data securely with OTP verification and login functionality.

---

## ✨ Features

### 🔍 For Users
- **Search Products** by name.
- **Nearby Shops Displayed**: Sorted by distance from the user’s location (nearest first).
- **Shop Details Page**:
  - Shop name at the top.
  - Products with:
    - Image
    - Price
    - Discounted Price
  - **"Get Shop Location"** button that opens shop directions in **Google Maps**.

---

### 🧾 For Shopkeepers
- **Account Registration with OTP Verification** (Gmail-based).
- **Login Functionality** after verification.
- **Add Products**:
  - Enter name, price, offer, and upload product image.
  - Image is automatically shown in the Offer Page.
- **Manage Products**:
  - View product list.
  - Add shop location.
- **Logout** option to securely exit the session.

---

## ⚙️ Tech Stack

| Layer        | Technology Used                          |
|--------------|------------------------------------------|
| Frontend     | JSP, HTML5, CSS, JavaScript              |
| Backend      | Java,Spring MVC     |
| Database     | MySQL, JDBC                              |
| Build Tool   | Maven                                    |
| Server       | Apache Tomcat (10.x supported)           |
| Mail         | JavaMail API (for Gmail OTP verification)|

---

## 🛠️ How to Run the Project

### 1. **Clone the Repository**
```bash
git clone https://github.com/mohitkumar1043/ncm.git
cd ncm
