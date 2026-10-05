# Inventory Management System (Core Java)

A console program for a small electronics shop to track laptops, mobiles, accessories and appliances.

## Scenario
The shopkeeper adds products, updates prices, adds stock, sells items and prints a bill. Selling a product that is out of stock is rejected.

## Classes
| Class | Purpose |
|---|---|
| `Product` | Product data; `stock` is private and changes only via `addStock()` / `sellProduct()` |
| `Category` | Enum: `LAPTOP`, `MOBILE`, `ACCESSORY`, `HOME_APPLIANCE` |
| `InventoryService` | Business logic using `Map<Integer, Product>` for quick search |
| `Bill` | Collects sold items and calculates the total |
| `Main` | Menu-driven console program (starts with sample data) |

## Features
Add product, search by ID, update price, add stock, sell product, show all products, show products with stock below 5, calculate bill total, delete product.

## Concepts
HashMap, Enum, encapsulation, exception handling (zero stock, invalid quantity/price).

## Run
```bash
cd src
javac *.java
java Main
```
