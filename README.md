
# Symatique Application Store

### Internship Project - January 2024

**Symatique Software**  
*Stage de Perfectionnement, Janvier 2024*

Developed as part of an internship at Symatique Software, this mobile application showcases Symatique's software products in a digital catalog, offering a streamlined user experience for product browsing, online ordering, and invoicing. The app enhances product visibility and optimizes the sales funnel while supporting seamless customer engagement.

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Hardware Setup](#hardware-setup)
4. [Software Architecture](#software-architecture)
5. [Modules and Components](#modules-and-components)
6. [Installation](#installation)
7. [Usage](#usage)
8. [Future Enhancements](#future-enhancements)
9. [Technical Specifications](#technical-specifications)
10. [License](#license)

---

## 1. Project Overview

The **Symatique Application Store** provides a digital platform for customers to explore, order, and purchase Symatique's software products. Developed using Android Studio and Kotlin, this application simplifies the ordering process, offers a detailed invoice upon checkout, and supports printing through a Bluetooth-connected thermal printer. The goal is to create an intuitive interface that digitizes Symatique's sales operations, ensuring a smooth and efficient customer experience.

## 2. Features

- **Digital Product Catalog**: Browse Symatique's software offerings with detailed product information.
- **User-Friendly Interface**: Simple and intuitive navigation for seamless interaction.
- **Online Ordering & Checkout**: Place orders directly within the app and receive a detailed invoice.
- **Bluetooth Printer Support**: Print invoices directly via a Honeywell (Intermec PB51) Bluetooth thermal printer.
- **Error Minimization**: Reduces human errors and processing delays in order handling.
- **Future Expandability**: Designed with modularity in mind to allow easy addition of new features.

## 3. Hardware Setup

To ensure full functionality, the application is developed with the following hardware components:
- **Android 13 Phone**: Used for real-time testing and debugging.
- **Honeywell Intermec PB51 Bluetooth Thermal Printer**: Enables invoice printing directly from the app, providing detailed order information for customers.

## 4. Software Architecture

The Symatique Application Store is built on a modular architecture using Android Studio. The app structure prioritizes clear, maintainable code and organized data handling:

- **Main Modules**:
  - **UI Layer**: Defined in XML files, handles layouts and visual components.
  - **Data Layer**: Managed through models and a local database.
  - **Logic Layer**: Contains main activities and adapters for data manipulation.

### Architecture Diagram
```plaintext
├── AndroidManifest.xml
├── res/
│   ├── layout/
│   │   ├── cart.xml
│   │   ├── item_products.xml
│   │   └── popup.xml
│   ├── drawable/
│   └── values/
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── com.symatique.store/
    │   │   │   ├── activities/
    │   │   │   ├── adapters/
    │   │   │   ├── models/
    │   │   │   ├── repository/
    │   │   │   └── database/
    │   └── AndroidManifest.xml
```

## 5. Modules and Components

### Activities
- **CartActivity**: Manages the shopping cart, allowing users to review and finalize their orders.
- **HomeActivity**: The landing page showcasing the main catalog.
- **ProductsActivity**: Displays product details, enabling users to browse items interactively.

### Adapters
- **BluetoothPrinter**: Manages Bluetooth connections for printing invoices.
- **BTDeviceList**: Handles Bluetooth device discovery and pairing.
- **CartAdapter**: Binds cart data to the UI for a dynamic, interactive cart experience.
- **PopupAdapter**: Manages pop-up views for actions like product information or checkout details.
- **ProductAdapter**: Links product data to the product view within the RecyclerView.

### Models
- **CartItem**: Represents individual items within the cart.
- **Product**: Defines product attributes and methods for data manipulation.

### Local Database
- **ORM Lite** is used to create and manage the database, ensuring reliable data storage and retrieval.
- **DataBaseHelper**: Configures database operations, enabling structured storage and retrieval.
- **CartRepository**: Interfaces with `DataBaseHelper` to provide efficient access to cart data.

## 6. Installation

### Prerequisites
- Android Studio (latest version recommended)
- Android 13 device for testing

### Steps
1. Clone this repository:
    ```bash
    git clone https://github.com/yourusername/symatique-application-store.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the project on an Android device.

## 7. Usage

1. **Home Page**: Start by exploring the product catalog in the `HomeActivity`.
2. **Adding to Cart**: Click on any product to view details and add it to the cart.
3. **Cart Management**: View and manage items in the cart through `CartActivity`.
4. **Checkout**: Proceed to checkout, and the app will generate a detailed invoice.
5. **Printing Invoice**: Use the Bluetooth printing option to print the invoice directly.

## 8. Future Enhancements

- **Extended Payment Options**: Integrate online payment gateways for direct checkout.
- **Advanced Search and Filters**: Add filters for price range, software type, and popularity.
- **Customer Reviews**: Allow users to view and leave reviews for each product.
- **Dynamic Promotions**: Add discount code integration for special promotions.

## 9. Technical Specifications

- **Language**: Kotlin
- **IDE**: Android Studio
- **Database**: ORM Lite for local database management
- **Printing**: Honeywell Intermec PB51 Bluetooth Thermal Printer
- **Development Tools**: Android SDK, Gradle

