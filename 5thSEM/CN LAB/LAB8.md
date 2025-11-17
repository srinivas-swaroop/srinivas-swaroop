Perfect 👍 Here’s a clean and neat **Lab Record format** for your **Lab 8: WAN Design using Serial DCE Cables with Static Routing** — ready to write in your record or submit digitally.

---

## **Lab 8: Design and Configuration of WAN using Three Routers (Serial DCE Cables) with Static Routing**

### **Aim:**

To design and configure a Wide Area Network (WAN) using three routers connected through serial DCE cables and implement static routing to enable communication between different networks.

---

### **Objectives:**

1. To design a WAN network using **three routers**, **three switches**, and **three PCs**.
2. To configure **IP addressing** for all routers and PCs according to the addressing table.
3. To implement **static routing** on all routers.
4. To verify **end-to-end connectivity** by simulation.

---

### **Addressing Table:**

| **Device** | **Interface**    | **IP Address** | **Subnet Mask** |
| ---------- | ---------------- | -------------- | --------------- |
| PC1        | NIC              | 192.168.1.5    | 255.255.255.0   |
| PC2        | NIC              | 192.168.2.5    | 255.255.255.0   |
| PC3        | NIC              | 192.168.3.5    | 255.255.255.0   |
| Router 1   | FastEthernet 0/0 | 192.168.1.1    | 255.255.255.0   |
| Router 1   | Serial 0/0       | 192.168.4.1    | 255.255.255.0   |
| Router 1   | Serial 0/1       | 192.168.6.2    | 255.255.255.0   |
| Router 2   | FastEthernet 0/0 | 192.168.2.1    | 255.255.255.0   |
| Router 2   | Serial 0/0       | 192.168.4.2    | 255.255.255.0   |
| Router 2   | Serial 0/1       | 192.168.5.1    | 255.255.255.0   |
| Router 3   | FastEthernet 0/0 | 192.168.3.1    | 255.255.255.0   |
| Router 3   | Serial 0/0       | 192.168.5.2    | 255.255.255.0   |
| Router 3   | Serial 0/1       | 192.168.6.1    | 255.255.255.0   |

---

### **Static Routing Table:**

#### **Router 1**

| **Network** | **Subnet Mask** | **Next Hop** |
| ----------- | --------------- | ------------ |
| 192.168.2.0 | 255.255.255.0   | 192.168.4.2  |
| 192.168.3.0 | 255.255.255.0   | 192.168.6.1  |

#### **Router 2**

| **Network** | **Subnet Mask** | **Next Hop** |
| ----------- | --------------- | ------------ |
| 192.168.1.0 | 255.255.255.0   | 192.168.4.1  |
| 192.168.3.0 | 255.255.255.0   | 192.168.5.2  |

#### **Router 3**

| **Network** | **Subnet Mask** | **Next Hop** |
| ----------- | --------------- | ------------ |
| 192.168.1.0 | 255.255.255.0   | 192.168.6.2  |
| 192.168.2.0 | 255.255.255.0   | 192.168.5.1  |

---

### **Brief Procedure:**

1. **Design the topology** — connect three routers in a triangular form using **serial DCE cables**, each connected to its respective switch and PC.
2. **Assign IP addresses** to router interfaces and PCs as per the addressing table.
3. **Configure static routes** on each router using:

   ```
   Router(config)# ip route <destination-network> <subnet-mask> <next-hop-ip>
   ```
4. **Verify connectivity** using the `ping` command between PCs on different networks.
5. **Save the configuration** using `copy running-config startup-config`.

---

### **Observations:**

* All router interfaces were assigned correct IP addresses.
* Ping between PCs in different networks (e.g., PC1 → PC3) was **successful**.
* Routing tables displayed correct static entries for all remote networks.

---

### **Conclusion:**

A WAN was successfully designed and configured using **three routers with serial DCE connections**.
Static routing enabled communication between all three LANs, and end-to-end connectivity was verified through simulation.

---

Would you like me to make the same format for **Lab 9** and **Lab 10** also (so they look uniform in your record)?
