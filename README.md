# 🛍️ NCM Product Search Platform (Spring MVC)

A simple yet powerful Spring MVC-based web application that allows users to search for products and see nearby shops offering those products, sorted by distance. It also features OTP-based Gmail authentication for shopkeepers to manage their shops and product listings.

---

## ✨ Features

### 🔍 For Users
- **Product Search**: Users can search for any product.
- **Nearby Shops Display**: Search results display a list of shops offering the product, sorted by distance from the user's location (nearest first).
- **Shop Details Page**:
  - Shop name at the top.
  - Product list with:
    - Images
    - Original price
    - Discounted price
  - **Get Shop Location**: A button at the bottom that opens Google Maps with the path from user's current location to the shop.

---

### 🧾 For Shopkeepers
- **Signup with OTP Verification**:
  - Shopkeepers register using their Gmail address.
  - OTP sent for verification before account creation is completed.
- **Login System**: Secure login for verified shopkeepers.
- **Product Management**:
  - Add new product details including name, price, offer, and product image.
  - Image is automatically processed and displayed in the Offer Page.
  - View list of all added products.
- **Shop Location Setup**: Add shop location for proximity-based search.
- **Logout Functionality**: Simple and secure logout option.

---

## ⚙️ Tech Stack

- **Backend**: Java, Spring MVC,JDBC
- **Frontend**: JSP,HTML,CSS,JavaScript
- **Database**: MySQL
- **Email**: JavaMail API for OTP
- **Image Handling**: File system upload or cloud (Cloudinary)
- **Location**: Google Maps API (for displaying directions)

---

## 🛠️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/ncm.git
   cd ncm
