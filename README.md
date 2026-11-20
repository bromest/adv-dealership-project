# Car Dealership Management System (`adv-dealership-project`)

A Java console application for managing a car dealership's vehicle inventory and processing customer sales and lease contracts. Built using object-oriented programming principles (inheritance, abstraction, polymorphism) and pipe-delimited CSV file persistence.

---

## Features

### Inventory Management
- **View All Vehicles:** Display complete dealership inventory with formatted columns.
- **Filtered Search:** Search available vehicles by price range, make/model, year range, color, mileage range, or vehicle type (`car`, `truck`, `SUV`, `van`).
- **Add / Remove Vehicles:** Dynamically add new vehicles to inventory or remove vehicles by VIN, automatically updating `inventory.csv`.

### Contract Processing (Sales & Leases)
- **Sales Contracts (`SalesContract`):**
  - Calculates 5% sales tax, a `$100.00` recording fee, and a processing fee (`$295.00` for vehicles under `$10,000`; `$495.00` for vehicles `$10,000` and over).
  - Supports optional financing with loan amortization calculations:
    - **4.25%** annual interest for **48 months** (vehicles >= `$10,000`)
    - **5.25%** annual interest for **24 months** (vehicles < `$10,000`)
- **Lease Contracts (`LeaseContract`):**
  - Enforces a maximum vehicle age restriction (vehicles over 3 years old cannot be leased).
  - Calculates a 50% expected ending value, a 7% lease fee, and 36-month financing at **4.0%** annual interest.
- **Automated Persistence:** Appends completed transactions to `contracts.csv` and removes the sold or leased vehicle from `inventory.csv`.

---

## Project Structure

```text
adv-dealership-project/
├── inventory.csv                     # Pipe-delimited dealership & vehicle inventory data
├── contracts.csv                     # Pipe-delimited log of recorded SALE and LEASE contracts
└── src/
    └── main/
        └── java/
            └── com/
                └── dealership/
                    ├── Program.java               # Application entry point
                    ├── UserInterface.java         # Console menu & user input handling
                    ├── Dealership.java            # Inventory storage & search queries
                    ├── DealershipFileManager.java # Reads/writes inventory.csv
                    ├── Vehicle.java               # Vehicle data model (BigDecimal pricing)
                    ├── Contract.java              # Abstract base class for customer contracts
                    ├── SalesContract.java         # Sales pricing, fees, and loan logic
                    ├── LeaseContract.java         # Lease valuation and payment logic
                    └── ContractDataManager.java   # Appends contracts to contracts.csv
```

---

## Data File Formats

### `inventory.csv`
Line 1 stores dealership metadata, followed by one line per vehicle:
```text
D & B Used Cars|111 Old Benbrook Rd|817-555-5555
10112|1993|Ford|Explorer|SUV|Red|525123|995.00
37846|2001|Ford|Ranger|truck|Yellow|172544|1995.00
```

### `contracts.csv`
Records are appended using a `SALE` or `LEASE` prefix:
```text
SALE|20261120|Dana Wyatt|dana@texas.com|37846|2001|Ford|Ranger|truck|Yellow|172544|1995.00|99.75|100.00|295.00|2489.75|YES|109.49
LEASE|20261120|Zachary Westly|zach@texas.com|44901|2025|Honda|Civic|car|Gray|12000|24000.00|12000.00|1680.00|13680.00|403.89
```

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/bromest/adv-dealership-project.git](https://github.com/bromest/adv-dealership-project.git)
   ```
2. **Open in IntelliJ IDEA:**
   - Open the project folder and ensure the SDK is configured (Java 17+ recommended).
   - Verify `inventory.csv` is located in the root directory of the project.
3. **Run the application:**
   - Run `Program.java` (`com.dealership.Program`) to launch the interactive console menu.
