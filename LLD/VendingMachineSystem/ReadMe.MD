# Vending Machine System - Low Level Design (LLD)

A state-driven vending machine implementation. It supports fixed slots, product loading/restocking, customer purchase flow, refund/change handling, and inventory display.

---

## 1. Problem Analysis & Requirements Breakdown

### What is a Vending Machine System?

**Definition:** A vending machine system is a software model that manages machine inventory and customer purchase flow: insert money, select slot, validate stock/price, dispense product, and return change/refund.

**Real-world analogy:** Like a mini self-service shop:


| Vending concept | Shop analogy             |
| --------------- | ------------------------ |
| Rack/slot       | Shelf position           |
| Product         | Item on shelf            |
| Inserted money  | Payment at counter       |
| Dispense        | Handing item to customer |
| Refund/change   | Returning money balance  |


### What Are We Building?

- **Operator side**: load products into fixed racks and restock existing racks.
- **Customer side**: insert money and choose a rack by number.
- **System side**: check stock and payment, then dispense or refund.

**Hidden complexities:**

- **State-based behavior** - actions allowed depend on current state.
- **Fixed physical limits** - finite rack count and per-rack max units.
- **Payment outcomes** - underpay, exact pay, overpay.
- **Consistency** - reset machine state cleanly after each transaction.

### Core Requirements


| Requirement          | Description                                 | Why it matters                 |
| -------------------- | ------------------------------------------- | ------------------------------ |
| Fixed rack layout    | Machine has racks `1..N` created at startup | Mirrors physical machine       |
| Rack max capacity    | Each rack has a max units limit             | Prevents overfilling           |
| Product management   | Load product into rack and restock quantity | Operator workflow              |
| Slot-based selection | Customer selects by rack number             | Simple and common UX           |
| Money handling       | Track inserted amount per session           | Required for purchase decision |
| Refund/change flow   | Refund on failure, change on overpay        | Correct transaction behavior   |
| State transitions    | Idle -> MoneyInserted -> Dispense -> Idle   | Clear lifecycle control        |


### High-Level Flow

```text
Operator loads/restocks racks

Customer inserts money -> state becomes MoneyInserted
Customer selects rack -> rack exists and quantity > 0?
  -> No: refund and reset to Idle
  -> Yes: compare paid vs product price
       -> paid < price: refund and reset to Idle
       -> paid >= price: dispense one unit, return change if needed, reset to Idle
```

### Design Goals

- **Clear responsibilities** - service, models, and states are separated.
- **Simple extensibility** - can add new states or admin features later.
- **Predictable behavior** - explicit transitions and reset path.
- **Interview-friendly design** - demonstrates State pattern cleanly.

**Patterns used:** Singleton (`VendingMachine`), State (`State`, `IdleState`, `MoneyInsertedState`, `DispenseState`).

---

## 2. Actors & Use Cases

### Actors (external users)

- **Customer** - inserts money and selects products.
- **Operator/Admin** - loads products and restocks racks.

**Not actors:** `Rack`, `Inventory`, `Product`, `State` implementations are internal entities.

### Use Cases


| Use case     | Actor    | Description                                | Success                          | Failure                                            |
| ------------ | -------- | ------------------------------------------ | -------------------------------- | -------------------------------------------------- |
| Load product | Operator | Put a product into a rack with quantity    | Rack updated                     | Invalid rack/product/quantity                      |
| Restock rack | Operator | Add quantity to an already configured rack | Quantity increased               | Exceeds max or invalid rack/qty                    |
| Buy product  | Customer | Insert money and select rack               | Item dispensed (+ change if any) | Empty/invalid rack or insufficient funds -> refund |


### Purchase - Step-by-step (this codebase)

1. Customer calls `insertMoney(amount)`.
2. In `IdleState`, amount is added and state moves to `MoneyInsertedState`.
3. Customer calls `selectProduct(rackNumber)`.
4. In `MoneyInsertedState`, system validates rack and quantity.
5. If invalid/empty -> `refund()` and reset to idle.
6. If `paid < price` -> `refund()` and reset to idle.
7. Else -> move to `DispenseState`, call `beginDispensing(rack)`.
8. `DispenseState.dispense(...)` decrements quantity and prints change if needed.
9. `VendingMachine.reset()` sets state to idle and clears current amount.

---

## 3. Core Entities & Responsibilities

### Package layout

```text
src/
|- enums/        ProductType
|- models/       Inventory, Rack, Product
|- services/     VendingMachine (singleton coordinator)
|- states/       State, IdleState, MoneyInsertedState, DispenseState
`- VendingMachineDemo
```

### 1) VendingMachine (coordinator)

- **State:** `Inventory inventory`, `State currentState`, `Double currentAmount`, concrete state instances.
- **Behavior:** `getInstance()`, `loadProduct()`, `reStock()`, `insertMoney()`, `selectProduct()`, `refund()`, `beginDispensing()`, `reset()`, `displayProducts()`.
- **Why singleton:** one logical machine instance for the demo.

### 2) Inventory

- **State:** `Map<Integer, Rack> racks`.
- **Behavior:** initializes fixed racks in constructor, `getRackCount()`, `getRack(rackNumber)`.
- **Responsibility:** owns rack lookup and fixed rack topology.

### 3) Rack

- **State:** `rackNumber`, `maxCapacity`, `product`, `quantity`.
- **Behavior:** `loadProduct(product, quantity)`, `reStock(quantity)`, `dispenseOne()`.
- **Responsibility:** hold one slot's inventory and enforce capacity on restock.

### 4) Product (+ ProductType)

- **Product fields:** `productId`, `name`, `price`, `productType`.
- **ProductType enum:** `CHOCOLATE`, `CHIPS`, `BEVERAGE`, `BISCUITS`, `OTHERS`.
- **Responsibility:** represent sellable item and price metadata.

### 5) State hierarchy

- **Interface:** `State` with `insertMoney(...)`, `selectProduct(...)`.
- **IdleState:** accepts first money insert, blocks selection without money.
- **MoneyInsertedState:** accepts extra money, validates rack/payment on select.
- **DispenseState:** dispenses product and reports change.

---

## 4. Relationships & Associations


| Relationship   | Example                                              | Meaning                                 |
| -------------- | ---------------------------------------------------- | --------------------------------------- |
| Composition    | `VendingMachine -> Inventory`                        | Machine owns inventory lifecycle        |
| Composition    | `VendingMachine -> Idle/MoneyInserted/DispenseState` | Machine owns concrete states            |
| Aggregation    | `Inventory -> Rack`                                  | Inventory stores racks in map           |
| Association    | `Rack -> Product`                                    | Slot points to currently loaded product |
| Implementation | `IdleState` implements `State` (same for others)     | State polymorphism                      |


---

## 5. Design Patterns Used

### 5.1 Singleton - VendingMachine

- **Problem:** avoid multiple machine instances with different state.
- **Solution:** `getInstance()` with double-checked locking and `volatile`.
- **Benefit:** one shared stateful machine in demo flow.

### 5.2 State - Customer interaction lifecycle

- **Interface:** `State`.
- **Implementations:** `IdleState`, `MoneyInsertedState`, `DispenseState`.
- **Benefit:** avoids large if/else branching in service; behavior is state-specific.

---

## 6. Thread Safety & Concurrency

### Current behavior in this code

- Singleton initialization is thread-safe (`volatile` + lock in `getInstance()`).
- Transaction methods (`insertMoney`, `selectProduct`, stock updates) are **not synchronized**.

### Implication

- This implementation is intended for **single-session / demo usage**.
- If you want multi-threaded safety, add locking around money/state/inventory mutations.

---
